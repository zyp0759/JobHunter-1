package com.cszjo.jobhunter.controller;

import com.cszjo.jobhunter.model.response.LoginResponse;
import com.cszjo.jobhunter.model.response.ResponseStatus;
import com.cszjo.jobhunter.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by Han on 2017/3/5.
 */
@Controller
public class UserController {

    private final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public String toLogin() {

        LOGGER.info("get request 4 login");
        return "login";
    }

    @RequestMapping(value = "/doLogin", method = RequestMethod.POST)
    @ResponseBody
    public LoginResponse doLogin(@RequestParam("userName") String userName, @RequestParam("password") String password, HttpSession httpSession) {

        LOGGER.info("get a login request, username = {}, password = {}", userName, password);
        LoginResponse loginResponse = userService.login(userName, password);
        if(loginResponse.getStatus() == ResponseStatus.SUCCESS) {
            httpSession.setAttribute("userInfo", loginResponse.getUsers());
        }
        return loginResponse;
    }
}
