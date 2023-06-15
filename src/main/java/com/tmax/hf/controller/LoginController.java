package com.tmax.hf.controller;

import com.tmax.hf.service.LoginService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    // Log4j Logger setting
    private final Logger logger = LogManager.getLogger(LoginController.class);

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

        logger.info("### Login Controller ###");
        return "login";
    }

    @PostMapping("/login")
    public String loginWithEmail(LoginForm form, HttpSession session, Model model) {
        //login service 호출
        Boolean result = loginService.login(form.getEmailaddress());

        //로그인 성공: session에 추가 /main 접속
        //      실패: /login redirect
        if(result) {
            session.setAttribute("emailaddress", form.getEmailaddress());
            session.setAttribute("isLogin", "yes");
            logger.info("Login Controller : Login Success [" + form.getEmailaddress() + "]");
            return "redirect:/main";
        } else {
            model.addAttribute("message", loginService.getLoginFailMsg());
            model.addAttribute("searchUrl","/login");
            logger.info("Login Controller : Login Fail [" + form.getEmailaddress() + "]");
            return "alert";
        }

    }


    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        logger.info("### Main Controller Start ###");
        return "redirect:/login";
    }
}
