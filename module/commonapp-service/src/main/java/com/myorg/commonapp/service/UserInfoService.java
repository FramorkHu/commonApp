package com.myorg.commonapp.service;

import com.myorg.commonapp.bean.po.UserInfo;

import java.util.List;

/**
 * Created by huyan on 15/7/16.
 */
public interface UserInfoService {

    public UserInfo findUserInfo(String userName, String password);

    public UserInfo findUserInfoByName(String userName);

    public List<UserInfo> findAllUserInfo();

    public int saveUserInfo(UserInfo userInfo);
}
