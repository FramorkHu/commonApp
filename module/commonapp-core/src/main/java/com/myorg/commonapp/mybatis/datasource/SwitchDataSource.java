package com.myorg.commonapp.mybatis.datasource;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;

/**
 * Created by huyan on 2015/8/20.
 */
public class SwitchDataSource {

    private static final Logger LOGGER = LoggerFactory.getLogger(SwitchDataSource.class);

    private static final BeanFactory BEAN_FACTORY = ServiceLocator.getBeanFactory();

    public Object doSwitchOperate(ProceedingJoinPoint joinPoint) throws Throwable {


        ComboPooledDataSource dataSource = (ComboPooledDataSource)BEAN_FACTORY.getBean("dataSource");
        ComboPooledDataSource masterDataSource = (ComboPooledDataSource)BEAN_FACTORY.getBean("dataSource");
        ComboPooledDataSource slaveDataSource = (ComboPooledDataSource)BEAN_FACTORY.getBean("slaveDataSource");



        BeanUtils.copyProperties(dataSource,slaveDataSource);

        Object object =
                joinPoint.proceed();
        LOGGER.info("do method ["+joinPoint.getSignature()+"] to switch dataSource");
        BeanUtils.copyProperties(dataSource,masterDataSource);

        return object;

    }

}
