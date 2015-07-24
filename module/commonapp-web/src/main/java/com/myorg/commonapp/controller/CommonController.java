package com.myorg.commonapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by huyan on 2015/7/24.
 */
@Controller
@RequestMapping("/common")
public class CommonController {

    private static final String PREFIX = "common/";


    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String getCommonPage() {
        return PREFIX + "403";
    }

}
