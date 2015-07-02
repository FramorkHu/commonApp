package com.myorg.commonapp.service.impl;

import com.myorg.commonapp.core.dao.AdminUserInfoDao;
import com.myorg.commonapp.bean.po.AdminUserInfo;
import com.myorg.commonapp.service.AdminUserInfoService;
import com.myorg.commonapp.utils.ParseMD5Utils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by huyan on 2015/7/2.
 */
public class AdminUserInfoServiceImpl implements AdminUserInfoService {

    @Autowired
    private AdminUserInfoDao adminUserInfoDao;

    @Override
    public AdminUserInfo getAdminUserInfo(String userName, String passwd) {
        String md5Passwd = ParseMD5Utils.parseStrToMd5L32(passwd);

        return adminUserInfoDao.getAdminUserInfo(userName,md5Passwd);
    }
}
