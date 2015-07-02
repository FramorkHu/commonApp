package test.myorg.commonapp.controller;

import com.myorg.commonapp.core.dao.AdminUserInfoDao;
import com.myorg.commonapp.bean.po.AdminUserInfo;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by huyan on 2015/7/1.
 */
public class LoginControllerTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginControllerTest.class);
    private ApplicationContext context;
    @Before
    public void init(){
        context = new ClassPathXmlApplicationContext("classpath:spring/*.xml");
    }
    @Test
    public void testUserInfo(){
        AdminUserInfoDao adminUserInfoDao =
                (AdminUserInfoDao)context.getBean("userInfoDao");
        AdminUserInfo userInfo1 =
                adminUserInfoDao.getAdminUserInfoByName("asd");
        AdminUserInfo userInfo =
                adminUserInfoDao.getAdminUserInfo("admin","admin@123");
        LOGGER.info("yes");
        LOGGER.error("error");
    }
}
