package com.myorg.commonapp.controller.base;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by huyan on 2015/6/9.
 */
@Controller
@RequestMapping("/admin")
public class LoginController extends AbstractController{

    private static final String PREFIX = "/";

    @RequestMapping("/")
    public String loginPage(Model model){
        return PREFIX + "login";
    }

    @RequestMapping("/login")
    public String login(){

        return "";
    }
}
