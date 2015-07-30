package com.myorg.commonapp.controller.base;

import com.myorg.commonapp.utils.JsonUtil;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by huyan on 2015/6/4.
 */
public class AbstractController {

    public static JsonUtil jsonUtil = JsonUtil.buildNonDefaultBinder();
    public static final String ADMIN_PREFIX = "admin/";
    public static final String LIST_PAGE = "list_page";
    public static final String LIST = "list";
    public static final String ADD = "add";
    public static final String SAVE = "save";
    public static final String EDIT = "edit";
    public static final String UPDATE = "update";
    public static final String DELETE = "delete";
    public static final String MSG = "msg";
    public static final String ERROR_MSG = "errorMsg";
    public static final String PAGER = "pager";
    public static final String TIP = "tip";
    public static final Integer DEFAULT_PAGE_SIZE = 20;

    public static final String ROWS = "rows";
    public static final String PAGE = "page";
    public static final String TOTAL = "total";
    public static final String FOOTER = "footer";

    public static final String SEARCH_RESULT = "search_result";

    public static String redirect(String path) {
        return "redirect:/" + path;
    }

    public static String forward(String path) {
        return "forward:/" + path;
    }

    public static void write(ServletResponse response, String text) {
        try {
            response.getWriter().write(text);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void writeJson(HttpServletResponse response, String text) {
        response.setContentType("application/json");
        write(response, text);
    }

    public static void writeJson(HttpServletResponse response, Object object) {
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "0");
        response.setContentType("application/json");

        response.setCharacterEncoding("UTF-8");

        String jsonStr =  jsonUtil.toJson(object);
        write(response,jsonStr);
    }

}
