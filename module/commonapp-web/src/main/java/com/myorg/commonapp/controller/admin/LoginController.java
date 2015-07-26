package com.myorg.commonapp.controller.admin;

import com.myorg.commonapp.bean.po.UserInfo;
import com.myorg.commonapp.controller.base.AbstractController;
import com.myorg.commonapp.service.UserInfoService;
import com.myorg.commonapp.utils.ParseMD5Utils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by huyan on 2015/6/9.
 */
@Controller
@RequestMapping("/admin")
public class LoginController extends AbstractController {

    private static final String PREFIX = "admin/";

    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping(value = {"/login", "relogin"}, method = GET)
    public String loginPage(){
        return PREFIX + "login";
    }

    @RequestMapping(value = {"/login", "relogin"}, method = POST)
    public String login(Model model, String userName, String password,
                        HttpServletRequest request){

        /*UserInfo userInfo =
                userInfoService.findUserInfo(userName, password);*/

        try {
            String encodePassword = ParseMD5Utils.parseStrToMd5L32(password);
            SecurityUtils.getSubject().login(new UsernamePasswordToken(userName,encodePassword));
            //HttpSession session = request.getSession();
            //session.setAttribute("adminUser", userInfo);
            return redirect("admin/index");
        } catch (Exception e){
            model.addAttribute(ERROR_MSG, "用户名或密码错误，请重新输入");
            return PREFIX+"login";
        }


    }



}
