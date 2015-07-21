package com.myorg.commonapp.core.dao.impl;

import com.myorg.commonapp.bean.po.Authorities;
import com.myorg.commonapp.bean.po.AuthoritiesExample;
import com.myorg.commonapp.core.dao.AuthoritiesDao;
import com.myorg.commonapp.core.mapper.AuthoritiesMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by huyan on 15/7/16.
 */
public class AuthoritiesDaoImpl implements AuthoritiesDao {

    @Autowired
    private AuthoritiesMapper mapper;

    @Override
    public List<Authorities> getAllAuthorities() {
        AuthoritiesExample example = new AuthoritiesExample();

        return mapper.selectByExample(example);
    }
}
