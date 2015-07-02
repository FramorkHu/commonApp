package com.myorg.commonapp.core.dao;

import com.myorg.commonapp.bean.po.AdminUserInfo;


/**
 * Created by huyan on 2015/7/1.
 */
public interface AdminUserInfoDao {

    public AdminUserInfo getAdminUserInfo(String name, String passwd);

    public AdminUserInfo getAdminUserInfoByName(String name);

}
