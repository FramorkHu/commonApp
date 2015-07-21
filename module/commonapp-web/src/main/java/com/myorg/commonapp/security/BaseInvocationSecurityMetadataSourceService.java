package com.myorg.commonapp.security;

import com.myorg.commonapp.bean.po.Authorities;
import com.myorg.commonapp.core.dao.AuthoritiesDao;
import com.myorg.commonapp.core.mapper.ext.AuthoritiesResourcesMapperExt;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.UrlUtils;

import java.util.*;

/**
 * Created by huyan on 15/7/16.
 * 最核心的地方，就是提供某个资源对应的权限定义，取得所有角色（auth）的对应资源数据
 */
public class BaseInvocationSecurityMetadataSourceService implements FilterInvocationSecurityMetadataSource {

    @Autowired
    AuthoritiesResourcesMapperExt authoritiesResourcesMapperExt;

    @Autowired
    AuthoritiesDao authoritiesDao;


    private static Map<String, Collection<ConfigAttribute>> resourceMap = new HashMap<String, Collection<ConfigAttribute>>();

    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        //参数是被请求的URL
        String url = ((FilterInvocation)o).getRequestUrl();

        //url 处理操作

        return resourceMap.get(url);
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }

    private void loadResourceDefine(){
        List<Authorities> authoritiesList =
                authoritiesDao.getAllAuthorities();

        ConfigAttribute ca;
        for (Authorities authorities : authoritiesList){
            int authorityId = authorities.getId();
            String authorityName = authorities.getAuthoritiesName();

            //添加资源配置
            ca = new SecurityConfig(authorityName);

            List<String> resourceUrlList = authoritiesResourcesMapperExt.getResourceByAuthority(authorityId);

            for (String resourceUrl : resourceUrlList){
                //判断资源文件和权限的对应关系
                if (resourceMap.containsKey(resourceUrl)){
                    Collection<ConfigAttribute> value = resourceMap.get(resourceUrl);
                    value.add(ca);
                } else {
                    Collection<ConfigAttribute> atts = new ArrayList<ConfigAttribute>();
                    atts.add(ca);
                    resourceMap.put(resourceUrl, atts);
                }

            }

        }

    }

}
