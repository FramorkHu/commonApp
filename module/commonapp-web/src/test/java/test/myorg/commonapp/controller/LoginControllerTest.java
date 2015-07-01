package test.myorg.commonapp.controller;

import com.myorg.commonapp.core.dao.UserInfoDao;
import com.myorg.commonapp.core.po.UserInfo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by huyan on 2015/7/1.
 */
public class LoginControllerTest {

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
        System.out.println(userInfo.getUsername());

    }
}
