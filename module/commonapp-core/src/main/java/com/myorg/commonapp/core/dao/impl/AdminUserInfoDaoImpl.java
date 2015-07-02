package com.myorg.commonapp.core.dao.impl;

import com.myorg.commonapp.core.dao.AdminUserInfoDao;
import com.myorg.commonapp.core.mapper.AdminUserInfoMapper;
import com.myorg.commonapp.core.mapper.ext.AdminUserInfoMapperExt;
import com.myorg.commonapp.bean.po.AdminUserInfo;
import com.myorg.commonapp.bean.po.AdminUserInfoExample;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by huyan on 2015/7/1.
 */
public class AdminUserInfoDaoImpl implements AdminUserInfoDao {

    @Autowired
    AdminUserInfoMapper adminUserInfoMapper;

    @Autowired
    AdminUserInfoMapperExt adminUserInfoMapperExt;

    @Override
    public AdminUserInfo getAdminUserInfo(String name, String passwd) {
        AdminUserInfoExample example = new AdminUserInfoExample();
        if (name == null || passwd == null){
            return null;
        }
        AdminUserInfoExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(name);
        criteria.andPasswordEqualTo(passwd);

        List<AdminUserInfo> userInfos = adminUserInfoMapper.selectByExample(example);
        if ( !CollectionUtils.isEmpty(userInfos) ){
            return userInfos.get(0);
        }
        return null;
    }

    public AdminUserInfo getAdminUserInfoByName(String name){
        return adminUserInfoMapperExt.getAdminUserInfo(name);
    }

}
