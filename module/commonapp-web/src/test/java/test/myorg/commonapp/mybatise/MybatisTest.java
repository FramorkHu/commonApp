package test.myorg.commonapp.mybatise;

import com.myorg.commonapp.bean.po.SysResource;
import com.myorg.commonapp.bean.po.UserInfo;
import com.myorg.commonapp.core.dao.UserInfoDao;
import com.myorg.commonapp.core.dao.impl.UserInfoDaoImpl;
import org.apache.ibatis.binding.MapperProxy;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.apache.ibatis.session.Configuration;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        UserInfoDao userInfoDao = (UserInfoDaoImpl) context.getBean("userInfoDao");
        UserInfo userInfo =
                userInfoDao.findUserInfoByName("admin");
        //MapperProxy

        System.out.println(userInfo.getUserName());
    }
}
