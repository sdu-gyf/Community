package top.sdugyf.community.community.provider;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import okhttp3.*;
import org.springframework.stereotype.Component;
import top.sdugyf.community.community.dto.GithubUser;
import top.sdugyf.community.community.dto.QQAccessTokenDTO;
import top.sdugyf.community.community.dto.QQUser;

import java.io.IOException;

@Component
public class QQProvider {
    public String getAccessToken(QQAccessTokenDTO qqAccessTokenDTO) {
        String appid = qqAccessTokenDTO.getApp_id();
        String appkey = qqAccessTokenDTO.getClient_secret();
        String redirectURI = qqAccessTokenDTO.getRedirect_uri();
        String code = qqAccessTokenDTO.getCode();
        OkHttpClient client =  new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://graph.qq.com/oauth2.0/token?grant_type=authorization_code&client_id="+appid
                        +"&client_secret="+appkey + "&redirect_uri=" + redirectURI + "&code=" + code)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String access_token = response.body().string();
            System.out.println("access_token:"+access_token);
            String token = access_token.split("&")[0].split("=")[1];
            System.out.println("token:"+token);
            return token;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public QQUser getUser(String accessToken) {
        OkHttpClient client =  new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://graph.qq.com/oauth2.0/me?access_token="+accessToken)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String UserInfoString = response.body().string().split(" ")[1];
            JSONObject userInfo = JSONObject.parseObject(UserInfoString);
            String openId = (String) userInfo.get("openid");
            String oauth_consumer_key = (String) userInfo.get("client_id");
            OkHttpClient okHttpClient =  new OkHttpClient();
            Request newRequest = new Request.Builder()
                    .url("https://graph.qq.com/user/get_user_info?access_token="+accessToken+"&oauth_consumer_key="+oauth_consumer_key+"&openid="+openId)
                    .build();
            try (Response newResponse = okHttpClient.newCall(newRequest).execute()) {
                System.out.println("newResponse:"+newResponse);
                String  data =  newResponse.body().string();
                QQUser qqUser = JSON.parseObject(data, QQUser.class);
                qqUser.setId(openId);
                return qqUser;
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
