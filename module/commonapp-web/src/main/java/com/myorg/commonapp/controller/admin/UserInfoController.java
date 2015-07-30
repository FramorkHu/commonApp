package com.myorg.commonapp.controller.admin;

import com.myorg.commonapp.controller.base.AbstractController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by huyan on 2015/7/30.
 */
@Controller
@RequestMapping("/admin/user")
public class UserInfoController extends AbstractController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserInfoController.class);


    @RequiresPermissions("user:viewUsers")
    @RequestMapping("/viewUsers")
    public String userPageView(){
        return ADMIN_PREFIX+"sysUserPage";
    }

    @RequestMapping("/editUser")
    public String editSysUser(Model model){

        System.out.println("editUser ok");
        model.addAttribute("editUser","test");
        return ADMIN_PREFIX+"sysUserPage";
    }

    @RequestMapping("/addUser")
    public String addUser(Model model){
        System.out.println("addUser ok");
        model.addAttribute("addUser","test");
        return ADMIN_PREFIX+"sysUserPage";
    }


    @RequestMapping("/deleteUser")
    public String deleteUser(Model model){
        System.out.println("deleteUser ok");
        model.addAttribute("deleteUser","test");
        return ADMIN_PREFIX+"sysUserPage";
    }

}
