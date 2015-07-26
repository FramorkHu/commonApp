package com.myorg.commonapp.core.mapper.ext;

import com.myorg.commonapp.bean.po.Roles;

import java.util.List;

/**
 * Created by huyan on 15/7/26.
 */
public interface RolesMapperExt {

    public List<Roles> findRolesByUser(int userId);



}
