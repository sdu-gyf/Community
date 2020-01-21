package top.sdugyf.community.community.mapper;


import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import top.sdugyf.community.community.model.Question;

@Mapper
@Component(value = "questionMapper")
public interface QuestionMapper {
    @Insert("insert into (title, description, gmt_create, gmt_modified, creator,tag) values(#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{tag})")
    public void create(Question question);
}
