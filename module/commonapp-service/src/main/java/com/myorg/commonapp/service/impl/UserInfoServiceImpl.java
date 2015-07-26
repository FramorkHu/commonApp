package com.myorg.commonapp.service.impl;

import com.myorg.commonapp.bean.po.UserInfo;
import com.myorg.commonapp.bean.pojo.UserInfoDetail;
import com.myorg.commonapp.core.dao.UserInfoDao;
import com.myorg.commonapp.service.UserInfoService;
import com.myorg.commonapp.utils.ParseMD5Utils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by huyan on 15/7/16.
 */
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoDao userInfoDao;


    @Override
    public UserInfo findUserInfo(String userName, String password) {

        String encodePassword = ParseMD5Utils.parseStrToMd5L32(password);

        return userInfoDao.findUserInfo(userName, encodePassword);
    }

    @Override
    public UserInfo findUserInfoByName(String userName) {
        return userInfoDao.findUserInfoByName(userName);
    }
}
