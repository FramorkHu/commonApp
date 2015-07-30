package com.myorg.commonapp.controller.admin;

import com.myorg.commonapp.controller.base.AbstractController;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by huyan on 2015/7/2.
 */
@Controller
@RequestMapping("/admin")
public class IndexController extends AbstractController {

    @Autowired


    @RequestMapping("/index")
    public String indexPage(){
        Subject currentUser =
                SecurityUtils.getSubject();
        Session session = currentUser.getSession();

        System.out.println(currentUser.getPrincipal() + "" + session.getStartTimestamp().toString()+" "+currentUser.isAuthenticated());
        return ADMIN_PREFIX + "index";
    }

    public void getMenuList(Model model){



    }


}
