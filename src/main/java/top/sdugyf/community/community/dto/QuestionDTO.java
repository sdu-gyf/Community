package top.sdugyf.community.community.dto;


import lombok.Data;
import top.sdugyf.community.community.model.User;

@Data
public class QuestionDTO {


    private Integer id;
    private String title;
    private String tag;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer creator;
    private Integer viewCount;
    private Integer commentCount;
    private Integer likeCount;
    private String description;
    private User user;
}
