package com.myorg.commonapp.core.dao;

import com.myorg.commonapp.core.po.UserInfo;

import java.util.List;

/**
 * Created by huyan on 2015/7/1.
 */
public interface UserInfoDao {

    public UserInfo getUserInfo();

    public List<Object> getData();
}
