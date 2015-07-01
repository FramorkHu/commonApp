package com.myorg.commonapp.core.dao.impl;

import com.myorg.commonapp.core.dao.UserInfoDao;
import com.myorg.commonapp.core.mapper.UserInfoMapper;
import com.myorg.commonapp.core.po.UserInfo;
import com.myorg.commonapp.core.po.UserInfoExample;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by huyan on 2015/7/1.
 */
public class UserInfoDaoImpl extends SqlSessionDaoSupport implements UserInfoDao {

    Logger logger = LoggerFactory.getLogger(UserInfoDaoImpl.class);

    @Autowired
    UserInfoMapper userInfoMapper;

    @Override
    public UserInfo getUserInfo() {
        logger.info("test asda asdawd ad");
        return userInfoMapper.selectByExample(new UserInfoExample()).get(0);
    }

    public List<Object> getData(){
        List<Object> result = getSqlSession().selectList("com.myorg.commonapp.core.dao.impl.UserInfoDaoImpl.getData");
        return result;
    }
}
