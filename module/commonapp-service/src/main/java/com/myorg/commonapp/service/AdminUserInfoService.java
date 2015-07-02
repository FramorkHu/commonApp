package com.myorg.commonapp.service;

import com.myorg.commonapp.bean.po.AdminUserInfo;

/**
 * Created by huyan on 2015/6/30.
 */
public interface AdminUserInfoService {

    public AdminUserInfo getAdminUserInfo(String userName, String passwd);
}
