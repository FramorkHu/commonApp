package com.myorg.commonapp.security;

import org.apache.shiro.web.filter.authz.AuthorizationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Created by huyan on 15/7/26.
 */
public class ResourceFilter extends AuthorizationFilter {


    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest,
                                      ServletResponse servletResponse, Object o) throws Exception {

        String path = getPathWithinApplication(servletRequest);


        return true;
    }
}
