package com.myorg.commonapp.core.mapper.ext;

import com.myorg.commonapp.bean.po.SysRole;

import java.util.List;

/**
 * Created by huyan on 15/7/26.
 */
public interface SysRoleMapperExt {

    public List<SysRole> findSysRolesByUser(int userId);

}
