package com.tmax.demo.controller;

import com.tmax.demo.service.LoginService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.Optional;
import java.util.UUID;

@Controller
public class LoginController {
    // Log4j Logger setting
    private final Logger logger = LogManager.getLogger(LoginController.class);
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/")
    public String root(){
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login(){

        logger.warn("### Login Controller ###");
        return "login";
    }

    @PostMapping("/login")
    public String loginWithEmail(LoginForm form, HttpSession session) {
        //login service 호출
        Boolean result = loginService.login(form.getEmailaddress());

        //로그인 성공: session에 추가 /main 접속
        //      실패: /login redirect
        if(result) {
            session.setAttribute("emailaddress", form.getEmailaddress());
            return "redirect:/main";
        } else {
            return "redirect:/login";
        }

    }


    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/login";
    }
}
