package com.myorg.commonapp.core.dao.impl;

import com.myorg.commonapp.bean.po.UserInfo;
import com.myorg.commonapp.bean.po.UserInfoExample;
import com.myorg.commonapp.core.dao.UserInfoDao;
import com.myorg.commonapp.core.mapper.UserInfoMapper;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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
    public List<UserInfo> findUserInfos(Map<String, Object> parm) {
        UserInfoExample example = new UserInfoExample();
        parseCriteria(parm,example);
        return mapper.selectByExample(example);
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

    @Override
    public int saveUserInfo(UserInfo userInfo){
        return mapper.insert(userInfo);

    }

    @Override
    public int delUserInfo(int id) {
        return mapper.deleteByPrimaryKey(id);
    }


    private void parseCriteria(Map<String, Object> parm,
                               UserInfoExample example){

        UserInfoExample.Criteria criteria = example.createCriteria();

        if (parm.get("userName")!=null){
            criteria.andUserNameEqualTo(String.valueOf(parm.get("userName")));
        }
        if (parm.get("password")!=null){
            criteria.andPasswordEqualTo(String.valueOf(parm.get("password")));
        }

    }
}
