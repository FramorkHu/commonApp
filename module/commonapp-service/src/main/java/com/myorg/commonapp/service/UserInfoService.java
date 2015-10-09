package com.myorg.commonapp.service;

import com.myorg.commonapp.bean.po.UserInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by huyan on 15/7/16.
 */
public interface UserInfoService {

    public UserInfo findUserInfoByName(String userName);

    public List<UserInfo> findUserInfos(Map<String, Object> parm);

    public int saveUserInfo(UserInfo userInfo);

    public int delUserInfo(int id);
}
