package com.myorg.commonapp.core.mapper.ext;

import com.myorg.commonapp.bean.po.Authorities;

import java.util.List;

/**
 * Created by huyan on 15/7/21.
 */
public interface AuthoritiesMapperExt {

    public List<Authorities> getAuthoritiesByUser(int userId);
}
