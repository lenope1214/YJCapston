package org.zero.test1.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zero.test1.dao.AndroidDAO;
import org.zero.test1.vo.Users;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
public class AndroidController {
    private static final Logger log = LogManager.getLogger(AndroidController.class);

    @Autowired
    AndroidDAO dao;

    @ResponseBody
    @RequestMapping("androidSignIn")
    public Map<String, Object> android(HttpServletRequest req) {
        log.info("androidSignIn()");

        String id = req.getParameter("id");
        String pwd = req.getParameter("pwd");

        Users user = new Users();
        user.setId(id);
        user.setPassword(pwd);

        String enabled = dao.confirmUser(user);
        if(enabled == null) {
            enabled = "0";
        }

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", id);
        map.put("password", pwd);
        map.put("enabled", enabled);
        return map;
    }
}
