package com.myorg.commonapp.core.dao.impl;

import com.myorg.commonapp.bean.po.UserInfo;
import com.myorg.commonapp.bean.po.UserInfoExample;
import com.myorg.commonapp.core.dao.UserInfoDao;
import com.myorg.commonapp.core.mapper.UserInfoMapper;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by huyan on 2015/7/1.
 */
public class UserInfoDaoImpl implements UserInfoDao {

    @Autowired
    UserInfoMapper mapper;

    @Override
    public UserInfo findUserInfo(String userName, String password) {

        UserInfoExample example = new UserInfoExample();
        UserInfoExample.Criteria criteria = example.createCriteria();
        criteria.andUserNameEqualTo(userName);
        criteria.andPasswordEqualTo(password);

        List<UserInfo> userInfos =
                mapper.selectByExample(example);
        if (CollectionUtils.isEmpty(userInfos)){
            return null;
        }
        return userInfos.get(0);

    }

    @Override
    public UserInfo findUserInfoByName(String userName) {
        UserInfoExample example = new UserInfoExample();
        UserInfoExample.Criteria criteria = example.createCriteria();

        criteria.andUserNameEqualTo(userName);
        List<UserInfo> userInfos =
                mapper.selectByExample(example);
        if (CollectionUtils.isEmpty(userInfos)){
            return null;
        }
        return userInfos.get(0);
    }
}
