package com.myorg.commonapp.security;

import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by huyan on 15/7/16.
 * 这个过滤器要插入到授权之前。
 * 最核心的代码就是invoke方法中的 InterceptorStatusToken token = super.beforeInvocation(fi);
 * 这一句，即在执行doFilter之前，进行权限的检查，
 * 而具体的实现已经交给accessDecisionManager了
 */
public class BaseFilterSecurityInterceptor extends AbstractSecurityInterceptor implements Filter {

    private FilterInvocationSecurityMetadataSource securityMetadataSource;

    public void setSecurityMetadataSource(FilterInvocationSecurityMetadataSource securityMetadataSource) {
        this.securityMetadataSource = securityMetadataSource;
    }

    @Override
    public Class<?> getSecureObjectClass() {
        return FilterInvocation.class;
    }

    @Override
    public SecurityMetadataSource obtainSecurityMetadataSource() {
        return this.securityMetadataSource;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        FilterInvocation filterInvocation = new FilterInvocation( servletRequest, servletResponse, filterChain );
        invoke(filterInvocation);
    }

    @Override
    public void destroy() {

    }

    public void invoke( FilterInvocation filterInvocation ) throws IOException, ServletException{

        InterceptorStatusToken token = super.beforeInvocation(filterInvocation);

        try{
            filterInvocation.getChain().doFilter(filterInvocation.getRequest(), filterInvocation.getResponse());
        }finally{
            super.afterInvocation(token, null);
        }

    }
}
