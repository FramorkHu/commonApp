package com.myorg.commonapp.service;

import com.myorg.commonapp.bean.po.SysResource;

import java.util.List;

/**
 * Created by huyan on 2015/7/30.
 */
public interface SysResourceService {

    public List<SysResource> getSysResourceByUser();
}
