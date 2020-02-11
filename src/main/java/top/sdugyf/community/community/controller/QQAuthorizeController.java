package top.sdugyf.community.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import top.sdugyf.community.community.dto.QQAccessTokenDTO;
import top.sdugyf.community.community.dto.QQUser;
import top.sdugyf.community.community.model.User;
import top.sdugyf.community.community.provider.QQProvider;
import top.sdugyf.community.community.service.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.UUID;

@Controller
public class QQAuthorizeController {

    @Autowired
    private QQProvider qqProvider;

    @Autowired
    private UserService userService;

    @Value("${qq.app_id}")
    private String appId;

    @Value("${qq.app_key}")
    private String appKey;

    @Value("${qq.redirect.uri}")
    private String redirectUri;

    @GetMapping("/qqlogin")
    public String qqlogin(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String rUrl = "https://graph.qq.com/oauth2.0/authorize?response_type=code&client_id="+appId+"&redirect_uri="+redirectUri+"&state=" + 2;
        return "redirect:"+rUrl;
    }

    @GetMapping("/qqcallback")
    public String qqCallback(@RequestParam(name="code") String code,
                             @RequestParam(name="state") String state,
                             HttpServletResponse response) {
        QQAccessTokenDTO qqAccessTokenDTO = new QQAccessTokenDTO();
        qqAccessTokenDTO.setApp_id(appId);
        qqAccessTokenDTO.setClient_secret(appKey);
        qqAccessTokenDTO.setCode(code);
        qqAccessTokenDTO.setRedirect_uri(redirectUri);
        String accessToken = qqProvider.getAccessToken(qqAccessTokenDTO);
        QQUser qqUser = qqProvider.getUser(accessToken);
        if(qqUser!=null && qqUser.getId()!=null) {
            User user = new User();
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setName(qqUser.getNickname());
            user.setAvatarUrl(qqUser.getFigureurl_qq_1());
            user.setAccountId(String.valueOf(qqUser.getId()));
            userService.CreateOrUpdate(user);
            response.addCookie(new Cookie("token",token));
            return "redirect:/";
        }
        return null;
    }
}
