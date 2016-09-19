package test.myorg.commonapp.mybatise;

import com.myorg.commonapp.bean.po.SysResource;
import com.myorg.commonapp.bean.po.UserInfo;
import com.myorg.commonapp.controller.MainController;
import com.myorg.commonapp.core.dao.UserInfoDao;
import com.myorg.commonapp.core.dao.impl.UserInfoDaoImpl;
import com.myorg.commonapp.mybatis.datasource.ServiceLocator;
import com.myorg.commonapp.service.UserInfoService;
import com.myorg.commonapp.service.impl.UserInfoServiceImpl;
import org.apache.ibatis.binding.MapperProxy;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;
import org.junit.Before;
import org.apache.ibatis.session.Configuration;
import org.junit.Test;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.config.TxNamespaceHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by huyan on 15/8/11.
 */
public class MybatisTest {
    private ApplicationContext context;

    @Before
    public void init(){
        context = new ClassPathXmlApplicationContext("classpath:spring/*.xml");
    }

    @Test
    public void test(){
        //SysResource sysResource;
        while (true){
            SysResource sysResource = new SysResource();
        }
    }

    @Test
    public void textSelect(){
        SqlSessionFactory factory = (SqlSessionFactory)context.getBean("sqlSessionFactory");
        SqlSession session = factory.openSession();
        Configuration configuration = factory.getConfiguration();

        Map<String,Object> params = new HashMap<String, Object>();
        params.put("userId",1);
        List<SysResource> sysResources =
                session.selectList("com.myorg.commonapp.core.mapper.ext.SysResourceMapperExt.findSysResourcesByUserId", params);

        List<SysResource> sysResources1 =
                session.selectList("com.myorg.commonapp.core.mapper.ext.SysResourceMapperExt.findSysResourcesByUserId", params);

        sysResources.size();

    }

    @Test
    public void innerMapperScannerConfigurer(){

        final UserInfoDao userInfoDao = (UserInfoDaoImpl) context.getBean("userInfoDao");
        ExecutorService service = Executors.newFixedThreadPool(20);

        service.submit(new Runnable() {
            @Override
            public void run() {
                UserInfo userInfo =
                        userInfoDao.findUserInfoByName("admin");

                System.out.println(Thread.currentThread().getName()+ " result:"+userInfo.getUserName());
            }
        });


        try {
            TimeUnit.SECONDS.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        service.shutdown();

        UserInfo userInfo =
                userInfoDao.findUserInfoByName("admin");

        System.out.println(Thread.currentThread().getName()+ " result:"+userInfo.getUserName());
    }

    @Test
    public void testSave(){
        UserInfoService userInfoService = (UserInfoServiceImpl) context.getBean("userInfoService");

        UserInfo userInfo = new UserInfo();
        userInfo.setUserName("test");
        userInfo.setPassword("test");

        userInfoService.saveUserInfo(userInfo);
    }

    @Test
    public void testSwitchSource(){

        UserInfoService userInfoService = (UserInfoServiceImpl) context.getBean("userInfoService");
        UserInfo userInfo = userInfoService.findUserInfoByName("admin");
        System.out.println(userInfo.getPassword());
    }

    @Test
    public void getContext() throws Exception {

        BeanFactory beanFactory = ServiceLocator.getBeanFactory();

        DefaultSqlSessionFactory sessionFactory =
                (DefaultSqlSessionFactory)beanFactory.getBean("sqlSessionFactory");

        SqlSession session = sessionFactory.openSession();
        Map<String,Object> params = new HashMap<String, Object>();
        params.put("userId",1);
        List<SysResource> sysResources1 =
                session.selectList("com.myorg.commonapp.core.mapper.ext.SysResourceMapperExt.findSysResourcesByUserId", params);

        System.out.println(sysResources1.size());
    }

    @Test
    public void testAnnotation(){
        Class controllerClass =MainController.class;
        Method[] methods = controllerClass.getDeclaredMethods();
        for (Method method : methods){

            Annotation annotation = method.getAnnotation(RequestMapping.class);
            Class annocationClass = annotation.annotationType();
            Annotation[] annotations = annocationClass.getAnnotations();
            int a = annotations.length;


        }
    }
}
