package com.myorg.commonapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by huyan on 15/7/5.
 */
@Controller
@RequestMapping("/main")
public class MainController {

    @RequestMapping(value = "/common", method = RequestMethod.GET)
    public String getCommonPage() {
        return "commonpage";
    }

    /**
     * 跳转到adminpage页面
     *
     * @return
     */
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String getAadminPage() {
        return "adminpage";

    }
}
