package top.sdugyf.community.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import top.sdugyf.community.community.dto.GithubAccessTokenDTO;
import top.sdugyf.community.community.dto.GithubUser;
import top.sdugyf.community.community.model.User;
import top.sdugyf.community.community.provider.GithubProvider;
import top.sdugyf.community.community.service.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class GithubAuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    @Autowired
    private UserService userService;


    @Value("${github.client_id}")
    private String clientId;

    @Value("${github.redirect.uri}")
    private String redirectUri;

    @Value("${github.client_secret}")
    private String clientSecret;

    @GetMapping("/callback")
    public String callback(@RequestParam(name="code") String code,
                           @RequestParam(name="state") String state,
                           HttpServletResponse response) {
        GithubAccessTokenDTO githubAccessTokenDTO = new GithubAccessTokenDTO();
        githubAccessTokenDTO.setClient_id(clientId);
        githubAccessTokenDTO.setClient_secret(clientSecret);
        githubAccessTokenDTO.setCode(code);
        githubAccessTokenDTO.setRedirect_uri(redirectUri);
        githubAccessTokenDTO.setState(state);
        String accessToken = githubProvider.getAccessToken(githubAccessTokenDTO);
        GithubUser githubUser = githubProvider.getUser(accessToken);
//        System.out.println(user.getLogin());
        if(githubUser!=null && githubUser.getId() != null){
            // 登录成功 写cookie和session
            User user = new User();
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setName(githubUser.getLogin());
            user.setAvatarUrl(githubUser.getAvatar_url());
            user.setAccountId(String.valueOf(githubUser.getId()));
            userService.CreateOrUpdate(user);
            response.addCookie(new Cookie("token", token));
            return "redirect:/";
        }else{
            // 登录失败，重新登陆
            return "redirect:/";
        }

    }
    @GetMapping("/logout")
    public String logout(HttpServletRequest request,
                         HttpServletResponse response) {
        request.getSession().removeAttribute("user");
        Cookie cookie = new Cookie("token",null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/";
    }
}
