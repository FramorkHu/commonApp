package test.myorg.commonapp.controller;

import com.myorg.commonapp.core.dao.UserInfoDao;
import com.myorg.commonapp.core.po.UserInfo;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

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
        UserInfoDao userInfoDao =
                (UserInfoDao)context.getBean("userInfoDao");
        UserInfo userInfo =
                userInfoDao.getUserInfo();
        List<Object> datas = userInfoDao.getData();
        LOGGER.info(userInfo.getUsername());
        LOGGER.error("error");

    }
}
