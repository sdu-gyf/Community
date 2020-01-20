package top.sdugyf.community.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import top.sdugyf.community.community.dto.AccessTokenDTO;
import top.sdugyf.community.community.dto.GithubUser;
import top.sdugyf.community.community.mapper.UserMapper;
import top.sdugyf.community.community.model.User;
import top.sdugyf.community.community.provider.GithubProvider;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    @Autowired
    private UserMapper userMapper;


    @Value("${github.client_id}")
    private String clientId;

    @Value("${github.redirect.uri}")
    private String redirectUri;

    @Value("${github.client_secret}")
    private String clientSecret;

    @GetMapping("/callback")
    public String callback(@RequestParam(name="code") String code,
                           @RequestParam(name="state") String state,
                           HttpServletRequest request) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(redirectUri);
        accessTokenDTO.setState(state);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser githubUser = githubProvider.getUser(accessToken);
//        System.out.println(user.getLogin());
        if(githubUser!=null){
            // 登录成功 写cookie和seesion\
            User user = new User();
            user.setToken(UUID.randomUUID().toString());
            user.setName(githubUser.getLogin());
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setGmtCreat(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreat());
            userMapper.insert(user);
            request.getSession().setAttribute("user",githubUser);
            return "redirect:/";
        }else{
            // 登录失败，重新登陆
            return "redirect:/";
        }

    }
}
