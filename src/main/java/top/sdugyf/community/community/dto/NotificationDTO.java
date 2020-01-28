package top.sdugyf.community.community.dto;


import lombok.Data;
import top.sdugyf.community.community.model.User;

@Data
public class NotificationDTO {
    private Long id;
    private Long gmtCreate;
    private Integer status;
    private String notifierName;
    private Long notifier;
    private String outerTitle;
    private Long outerid;
    private String typeName;
    private Integer type;
}
