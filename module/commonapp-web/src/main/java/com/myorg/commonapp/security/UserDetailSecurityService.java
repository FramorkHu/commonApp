package com.myorg.commonapp.security;

import com.myorg.commonapp.bean.po.Authorities;
import com.myorg.commonapp.bean.po.UserInfo;
import com.myorg.commonapp.core.dao.UserInfoDao;
import com.myorg.commonapp.core.mapper.ext.AuthoritiesMapperExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huyan on 15/7/19.
 * 查询出用户所具有的权限等信息并进行封装得到UserDetails
 */
public class UserDetailSecurityService implements UserDetailsService {

    @Autowired
    UserInfoDao userInfoDao;

    @Autowired
    AuthoritiesMapperExt authoritiesMapperExt;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        UserInfo userInfo = userInfoDao.getUserInfoByUserName(userName);
        List<Authorities> authoritiesList =
                authoritiesMapperExt.getAuthoritiesByUser(userInfo.getId());

        List<GrantedAuthority> gAuthoritys = new ArrayList<GrantedAuthority>();

        GrantedAuthority grantedAuthority;
        for (Authorities authorities : authoritiesList){
            grantedAuthority = new SimpleGrantedAuthority(authorities.getAuthoritiesName());
            gAuthoritys.add(grantedAuthority);
        }

        return new CustomUserDetails(userInfo.getUserName(), userInfo.getPassword(),
                true, true, true, true, gAuthoritys);
    }
}
