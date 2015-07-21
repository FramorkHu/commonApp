package com.myorg.commonapp.core.mapper.ext;

import java.util.List;

/**
 * Created by huyan on 15/7/16.
 */
public interface AuthoritiesResourcesMapperExt {

    public List<String> getResourceByAuthority(int authorityId);
}
