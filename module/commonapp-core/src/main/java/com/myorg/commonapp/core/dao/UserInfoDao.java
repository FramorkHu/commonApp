package com.myorg.commonapp.core.dao;

import com.myorg.commonapp.bean.po.UserInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by huyan on 15/7/16.
 */
public interface UserInfoDao {

    public UserInfo findUserInfo(String userName, String password);

    public List<UserInfo> findUserInfos(Map<String, Object> parm);

    public UserInfo findUserInfoByName(String userName);

    public int saveUserInfo(UserInfo userInfo);

    public int delUserInfo(int id);
}
