package com.myorg.commonapp.security;

import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.util.List;

/**
 * Created by huyan on 15/7/26.
 */
public class ResourceFilter extends AuthorizationFilter {


    private List<String> ignoreList;

    public void setIgnoreList(List<String> ignoreList) {
        this.ignoreList = ignoreList;
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest,
                                      ServletResponse servletResponse, Object o) throws Exception {

        String path = getPathWithinApplication(servletRequest);

        if( !CollectionUtils.isEmpty(ignoreList) && ignoreList.contains(path)){
            return true;
        }

        return false;
    }
}
