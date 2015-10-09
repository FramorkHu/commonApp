package com.myorg.commonapp.security;

import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.web.filter.AccessControlFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by huyan on 15/7/26.
 */
public class ResourceFilter extends AccessControlFilter {


    /**
     * 判断请求是否通过
     * @param servletRequest
     * @param servletResponse
     * @param o
     * @return
     * @throws Exception
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest,
                                      ServletResponse servletResponse, Object o) throws Exception {

        String path = getPathWithinApplication(servletRequest);


        return false;
    }

    /**
     * 请求被拒绝的处理办法
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException{

        /*if (isLoginRequest(request, response)) {
            return true;
        }else {
            saveRequestAndRedirectToLogin(request, response);
            return false;
        }*/


        saveRequestAndRedirectToLogin(request, response);
        return true;
    }


    protected void redirectToLogin(ServletRequest request, ServletResponse response) throws IOException{

    }

}
