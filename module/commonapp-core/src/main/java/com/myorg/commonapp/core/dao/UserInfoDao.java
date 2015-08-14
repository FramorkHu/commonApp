package com.myorg.commonapp.core.dao;

import com.myorg.commonapp.bean.po.UserInfo;

/**
 * Created by huyan on 15/7/16.
 */
public interface UserInfoDao {

    public UserInfo findUserInfo(String userName, String password);

    public UserInfo findUserInfoByName(String userName);

    public int saveUserInfo(UserInfo userInfo) throws Exception;
}
