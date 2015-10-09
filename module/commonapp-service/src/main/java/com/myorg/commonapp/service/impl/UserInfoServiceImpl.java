package com.myorg.commonapp.service.impl;

import com.myorg.commonapp.bean.po.UserInfo;
import com.myorg.commonapp.bean.po.UserInfoExample;
import com.myorg.commonapp.core.dao.UserInfoDao;
import com.myorg.commonapp.core.mapper.UserInfoMapper;
import com.myorg.commonapp.service.UserInfoService;
import com.myorg.commonapp.utils.ParseMD5Utils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by huyan on 15/7/16.
 */
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoDao userInfoDao;


    @Override
    public UserInfo findUserInfoByName(String userName) {
        return userInfoDao.findUserInfoByName(userName);
    }

    @Override
    public List<UserInfo> findUserInfos(Map<String, Object> parm) {
        List<UserInfo> userInfos = new ArrayList<UserInfo>();
        userInfos.addAll(userInfoDao.findUserInfos(parm));
        return userInfos;
    }

    @Override
    public int saveUserInfo(UserInfo userInfo) {

        return userInfoDao.saveUserInfo(userInfo);

    }

    @Override
    public int delUserInfo(int id) {

        return userInfoDao.delUserInfo(id);
    }

}
