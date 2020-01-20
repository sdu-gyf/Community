package top.sdugyf.community.community.mapper;


import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import top.sdugyf.community.community.model.User;

@Mapper
@Component(value = "userMapper")
public interface  UserMapper {
    @Insert("insert into user (name, account_id, token, gmt_create, gmt_modified) values (#{name},#{accountId},#{token},#{gmtCreat},#{gmtModified})")
    void  insert(User user);
}
