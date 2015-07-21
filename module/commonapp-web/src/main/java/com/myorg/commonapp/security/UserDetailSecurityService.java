package com.myorg.commonapp.security;

import com.myorg.commonapp.bean.po.UserInfo;
import com.myorg.commonapp.core.dao.UserInfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Created by huyan on 15/7/19.
 */
public class UserDetailSecurityService implements UserDetailsService {

    @Autowired
    UserInfoDao userInfoDao;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        UserInfo userInfo = userInfoDao.getUserInfoByUserName(userName);


        return null;
    }
}
