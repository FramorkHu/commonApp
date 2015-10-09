package com.myorg.commonapp.controller.admin;

import com.myorg.commonapp.bean.po.UserInfo;
import com.myorg.commonapp.controller.base.AbstractController;
import com.myorg.commonapp.controller.base.OperateStateConstant;
import com.myorg.commonapp.service.UserInfoService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by huyan on 2015/7/30.
 */
@Controller
@RequestMapping("/admin/user")
public class UserInfoController extends AbstractController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserInfoController.class);

    @Autowired
    private UserInfoService userInfoService;

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
    public String addUser(Model model,UserInfo userInfo){

        if (checkIsUser(userInfo)
                && userInfoService.findUserInfoByName(userInfo.getUserName()) != null){
            model.addAttribute(TIP, OperateStateConstant.SAME_USER_ERROR);
        }

        int result =
                userInfoService.saveUserInfo(userInfo);
        if (result>0){
            model.addAttribute(TIP, OperateStateConstant.ADD_SUCCESS);
        } else {
            model.addAttribute(TIP, OperateStateConstant.ADD_ERROR);
        }

        return ADMIN_PREFIX+"sysUserPage";
    }


    @RequestMapping("/deleteUser")
    public String deleteUser(Model model){
        System.out.println("deleteUser ok");
        model.addAttribute("deleteUser","test");
        return ADMIN_PREFIX+"sysUserPage";
    }

    @RequestMapping("/findUser")
    public void findUser(HttpServletRequest request,
                         HttpServletResponse response, String value){

        List<UserInfo> userInfos = new ArrayList<UserInfo>();

        UserInfo userInfo1 = new UserInfo();
        userInfo1.setUserName("saaa");
        userInfo1.setIsSuperAdmin(1);
        userInfo1.setId(1);
        userInfo1.setPassword("pass");
        userInfo1.setEnabled(1);

        UserInfo userInfo2 = new UserInfo();
        userInfo2.setUserName("saaa");
        userInfo2.setIsSuperAdmin(1);
        userInfo2.setId(1);
        userInfo2.setPassword("pass");
        userInfo2.setEnabled(1);

        userInfos.add(userInfo1);
        userInfos.add(userInfo2);

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("draw",1);
        result.put("recordsTotal",2);
        result.put("recordsFiltered",2);
        result.put("data",userInfos);


        writeJson(response, result);
    }

    private boolean checkIsUser(UserInfo userInfo){

        return userInfo.getUserName()!=null &&
                userInfo.getPassword() != null;
    }
}
