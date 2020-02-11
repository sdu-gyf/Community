package top.sdugyf.community.community.dto;


import lombok.Data;

@Data
public class QQAccessTokenDTO {
    private String app_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
}
