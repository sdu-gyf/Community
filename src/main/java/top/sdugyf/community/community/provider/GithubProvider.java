package top.sdugyf.community.community.provider;


import okhttp3.*;
import org.springframework.stereotype.Component;
import top.sdugyf.community.community.dto.AccessTokenDTO;
import top.sdugyf.community.community.dto.GithubUser;
import com.alibaba.fastjson.*;

import java.io.IOException;
import java.lang.reflect.Type;

@Component
public class GithubProvider {
    public String getAccessToken(AccessTokenDTO accessTokenDTO){
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(JSON.toJSONString(accessTokenDTO), mediaType);
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            String access_token = response.body().string();
            String token = access_token.split("&")[0].split("=")[1];
//            System.out.println(token);
            return token;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public GithubUser getUser(String accessToken){
        OkHttpClient client =  new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token="+accessToken)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String  data =  response.body().string();
//            System.out.println(data);
            GithubUser githubUser = JSON.parseObject(data, GithubUser.class);
            return githubUser;
        } catch (IOException e) {
        }
        return null;
    }
}

