package com.myorg.commonapp.bean.pojo;

import com.myorg.commonapp.bean.po.Authorities;

import java.util.List;

/**
 * Created by huyan on 15/7/16.
 */
public class UserInfoDetail {

    private String userName;
    private String password;
    private String roleId;
    private String roleName;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
