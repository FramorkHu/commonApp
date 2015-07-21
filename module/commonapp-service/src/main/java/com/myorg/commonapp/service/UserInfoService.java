package com.myorg.commonapp.service;

import com.myorg.commonapp.bean.po.UserInfo;

/**
 * Created by huyan on 15/7/16.
 */
public interface UserInfoService {

    public UserInfo getUserInfo(String userName, String password);
}
