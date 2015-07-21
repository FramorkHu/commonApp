package com.myorg.commonapp.core.mapper.ext;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by huyan on 15/7/16.
 */
public interface AuthoritiesResourcesMapperExt {

    public List<String> getResourceByAuthority(@Param(value = "authorityId")int authorityId);
}
