package com.myorg.commonapp.service.impl;

import com.myorg.commonapp.bean.po.UserInfo;
import com.myorg.commonapp.bean.po.UserInfoExample;
import com.myorg.commonapp.core.dao.UserInfoDao;
import com.myorg.commonapp.core.mapper.UserInfoMapper;
import com.myorg.commonapp.service.UserInfoService;
import com.myorg.commonapp.utils.ParseMD5Utils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by huyan on 15/7/16.
 */
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoDao userInfoDao;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public UserInfo findUserInfo(String userName, String password) {

        String encodePassword = ParseMD5Utils.parseStrToMd5L32(password);

        return userInfoDao.findUserInfo(userName, encodePassword);
    }

    @Override
    public UserInfo findUserInfoByName(String userName) {
        return userInfoDao.findUserInfoByName(userName);
    }

    @Override
    public List<UserInfo> findAllUserInfo() {
        UserInfoExample.Criteria criteria = new UserInfoExample().createCriteria();

        return null;
    }

    @Override
    public int saveUserInfo(UserInfo userInfo) {
        try {
            return userInfoDao.saveUserInfo(userInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
