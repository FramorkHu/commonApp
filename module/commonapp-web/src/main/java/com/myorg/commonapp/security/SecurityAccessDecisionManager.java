package com.myorg.commonapp.security;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Iterator;

/**
 * Created by huyan on 15/7/19.
 * 当请求访问时，判断用户是否具有访问所需的所有权限
 */
public class SecurityAccessDecisionManager implements AccessDecisionManager {


    /**
     * 这个方法中需要和ConfigAttribute进行对比验证
     * Object是个url,这个过滤器会根据这个URL去查找权限配置,决定是否通过
     *
     * @param authentication
     * @param o
     * @param configAttributes
     * @throws AccessDeniedException
     * @throws InsufficientAuthenticationException
     */
    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {

        if (CollectionUtils.isEmpty(configAttributes)){
            return;
        }
        Iterator<ConfigAttribute> caIterator = configAttributes.iterator();

        ConfigAttribute ca;
        while ( caIterator.hasNext() ){
            ca = caIterator.next();
            String needRole = ca.getAttribute();
            for (GrantedAuthority ga : authentication.getAuthorities()){
                if (needRole.equals(ga.getAuthority())){
                    return;
                }
            }
        }

        throw new AccessDeniedException("no right");

    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
