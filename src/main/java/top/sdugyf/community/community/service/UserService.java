package top.sdugyf.community.community.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.sdugyf.community.community.mapper.UserMapper;
import top.sdugyf.community.community.model.User;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;


    public void CreateOrUpdate(User user) {

        User dbuser = userMapper.findByAccountId(user.getAccountId());
        if(dbuser==null) {
            userMapper.insert(user);
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
        } else {
            dbuser.setGmtModified(System.currentTimeMillis());
            dbuser.setAvatarUrl(user.getAvatarUrl());
            dbuser.setName(user.getName());
            dbuser.setToken(user.getToken());
            userMapper.update(dbuser);
        }
    }
}
