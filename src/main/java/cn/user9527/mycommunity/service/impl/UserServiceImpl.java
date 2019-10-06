package cn.user9527.mycommunity.service.impl;

import cn.user9527.mycommunity.mapper.UserMapper;
import cn.user9527.mycommunity.model.User;
import cn.user9527.mycommunity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @date 2019/10/2 - 12:19
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void createOrUpdate(User user) {
        User userAccount = userMapper.findByIdAccountId(user.getAccountId());

        if(userAccount == null){
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);
        }else{
            userAccount.setGmtModified(user.getGmtCreate());
            userAccount.setAvatarUrl(user.getAvatarUrl());
            userAccount.setName(user.getName());
            userAccount.setToken(user.getToken());
            userMapper.update(userAccount);
        }
    }
}
