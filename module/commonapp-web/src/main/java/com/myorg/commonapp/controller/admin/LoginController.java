package com.myorg.commonapp.controller.admin;

import com.myorg.commonapp.controller.base.AbstractController;
import com.myorg.commonapp.service.UserInfoService;
import com.myorg.commonapp.utils.ParseMD5Utils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping(value = {"/login", "relogin"}, method = GET)
    public String loginPage(){
        return ADMIN_PREFIX + "login";
    }

    @RequestMapping(value = {"/login", "relogin"}, method = POST)
    public String login(Model model, String userName, String password,
                        HttpServletRequest request){

        String encodePassword = ParseMD5Utils.parseStrToMd5L32(password);
        Subject currentUser =
                SecurityUtils.getSubject();

        try {

            currentUser.login(new UsernamePasswordToken(userName, encodePassword));

            return redirect("admin/index");

        } catch (UnknownAccountException e){
            LOGGER.error("username wasn't in the system,",e);
            model.addAttribute(ERROR_MSG, "用户名或密码错误，请重新输入");
            return ADMIN_PREFIX+"login";
        } catch (IncorrectCredentialsException e){
            LOGGER.error("password didn't match,",e);
            model.addAttribute(ERROR_MSG, "用户名或密码错误，请重新输入");
            return ADMIN_PREFIX+"login";
        } catch (Exception e){
            LOGGER.error("login is error,",e);
            model.addAttribute(ERROR_MSG, "系统错误，请联系管理员");
            return ADMIN_PREFIX+"login";
        }


    }



}
