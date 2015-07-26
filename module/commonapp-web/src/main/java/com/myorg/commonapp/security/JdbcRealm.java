package com.myorg.commonapp.security;

import com.myorg.commonapp.bean.po.Authorities;
import com.myorg.commonapp.bean.po.Roles;
import com.myorg.commonapp.bean.po.UserInfo;
import com.myorg.commonapp.core.mapper.ext.AuthoritiesMapperExt;
import com.myorg.commonapp.core.mapper.ext.RolesMapperExt;
import com.myorg.commonapp.service.UserInfoService;
import com.myorg.commonapp.utils.ListParseUtils;
import org.apache.commons.collections.CollectionUtils;
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
    RolesMapperExt rolesMapperExt;

    @Autowired
    AuthoritiesMapperExt authoritiesMapperExt;

    /**
     * 权限认证
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
            List<Roles> rolesList =
                    rolesMapperExt.findRolesByUser(userInfo.getId());
            List<Integer> roleIdList = new ArrayList<Integer>();

            for (Roles roles : rolesList){
                roleNames.add(roles.getRoleName());
                roleIdList.add(roles.getId());
            }
            permissions = getPermissions(roleIdList);

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

    public Set<String> getPermissions(List<Integer> roleIdList){
        Set<String> permissions = new LinkedHashSet<String>();
        if (CollectionUtils.isEmpty(roleIdList)){
            return permissions;
        }

        List<Authorities> authoritiesList =
                authoritiesMapperExt.findAuthoritiesByRoleId(roleIdList);

        for (Authorities authorities : authoritiesList){
            permissions.add(authorities.getAuthoritiesName());
        }

        return permissions;

    }
}
