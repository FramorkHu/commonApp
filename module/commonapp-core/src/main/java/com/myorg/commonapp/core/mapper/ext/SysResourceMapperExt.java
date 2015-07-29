package com.myorg.commonapp.core.mapper.ext;

import com.myorg.commonapp.bean.po.SysResource;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by huyan on 15/7/21.
 */
public interface SysResourceMapperExt {

    public List<SysResource> findSysResourcesByUserId(int userId);

    public List<SysResource> findSysResourcesByRoleId(@Param("roleIdList")List<Integer> roleIdList);
}
