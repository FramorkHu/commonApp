package com.myorg.commonapp.mybatis.datasource;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

/**
 * Created by huyan on 2015/8/20.
 */
public class ServiceLocator implements BeanFactoryAware {


    private static BeanFactory beanFactory = null;

    private static ServiceLocator servlocator = null;

    public void setBeanFactory(BeanFactory factory) throws BeansException {
        this.beanFactory = factory;
    }

    public static BeanFactory getBeanFactory() {
        return beanFactory;
    }



    public static Object getService(String servName) {
        return beanFactory.getBean(servName);
    }


}
