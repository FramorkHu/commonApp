package com.myorg.commonapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by huyan on 2015/6/9.
 */
@Controller
@RequestMapping("/etds")
public class LoginController {

    private static final String PREFIX = "/";

    @RequestMapping("/")
    public String login(Model model){

        model.addAttribute("aa","aa");
        return PREFIX + "login";
    }
}
