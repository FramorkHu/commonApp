package com.myorg.commonapp.shiro.realm;

import com.myorg.commonapp.bean.po.SysResource;
import com.myorg.commonapp.bean.po.SysRole;
import com.myorg.commonapp.bean.po.UserInfo;
import com.myorg.commonapp.core.mapper.ext.SysResourceMapperExt;
import com.myorg.commonapp.core.mapper.ext.SysRoleMapperExt;
import com.myorg.commonapp.service.UserInfoService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * Created by huyan on 15/7/26.
 */
public class JdbcRealm extends AuthorizingRealm {

    private static final Logger LOGGER = LoggerFactory.getLogger(JdbcRealm.class);

    @Autowired
    UserInfoService userInfoService;

    @Autowired
    SysRoleMapperExt sysRoleMapperExt;

    @Autowired
    SysResourceMapperExt sysResourceMapperExt;

    /**
     * 得到当事人权限
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //得到当事人的用户名
        String username = (String) getAvailablePrincipal(principalCollection);
        UserInfo userInfo = userInfoService.findUserInfoByName(username);

        Set<String> roleNames = new HashSet<String>();
        Set<String> permissions = new HashSet<String>();

        if ( userInfo!=null ){
            List<SysRole> rolesList =
                    sysRoleMapperExt.findSysRolesByUser(userInfo.getId());

            for (SysRole roles : rolesList){
                roleNames.add(roles.getRoleName());
            }
            permissions = getPermissions(userInfo.getId());

        }
        //权限信息对象info,用来存放查出的用户的所有的角色（role）及权限（permission）
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roleNames);
        info.setStringPermissions(permissions);

        return info;
    }

    /**
     * 获取认证信息
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        SimpleAuthenticationInfo info ;
        //用来存储用户提交的登录信息
        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;

        String userName = token.getUsername();
        if (userName==null || "".equals(userName)){
            throw new AccountException("NUll username are not allowed is this realm. ");
        }

        UserInfo userInfo = userInfoService.findUserInfoByName(userName);
        if ( userInfo!=null ){
            //若存在，将此用户存放到登录认证info中
            info = new SimpleAuthenticationInfo(userInfo.getUserName(), userInfo.getPassword(), getName());
        } else {
            throw new AuthenticationException("No account found for user ["+userName+"]");
        }
        return info;
    }

    public Set<String> getPermissions(Integer userId){
        Set<String> permissions = new LinkedHashSet<String>();

        List<SysResource> resourceList =
                sysResourceMapperExt.findSysResourcesByUserId(userId);

        for (SysResource resource : resourceList){
            permissions.add(resource.getResourcePermission());
        }

        return permissions;

    }
}
