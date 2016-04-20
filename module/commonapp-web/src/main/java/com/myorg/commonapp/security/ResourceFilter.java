package com.myorg.commonapp.security;

import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by huyan on 15/7/26.
 */
public class ResourceFilter extends AccessControlFilter {


    //忽略指定url
    private List<String> ignoreList;

    //忽略指定前缀
    private List<String> ignoreHeadList;

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

        if (ignoreHeadList != null) {
            for (String s : ignoreHeadList) {
                if (path.startsWith(s)) {
                    return true;
                }
            }
        }

        if (ignoreList != null && ignoreList.contains(path)) {
            return true;
        }

        Subject subject = getSubject(servletRequest, servletResponse);

        String permit = path.substring(1).replace("/",":");

        boolean isAllow = subject.isPermitted(permit);

        return isAllow;

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

    public void setIgnoreList(List<String> ignoreList) {
        this.ignoreList = ignoreList;
    }
}
