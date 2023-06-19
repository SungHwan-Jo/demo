package com.tmax.hf.online.controller;

import com.tmax.hf.online.service.ErrCodeMsgService;
import com.tmax.hf.online.service.LoginService;
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
    private final ErrCodeMsgService errCodeMsgService;

    /**
     *
     * @param loginService
     * @param errCodeMsgService
     * LoginController
     */
    @Autowired
    public LoginController(LoginService loginService, ErrCodeMsgService errCodeMsgService) {
        this.loginService = loginService;
        this.errCodeMsgService = errCodeMsgService;
    }
    @GetMapping("/")
    public String root() {
        return "redirect:/oltp/v1/login";
    }

    @GetMapping("/oltp/v1/login")
    public String login(){

        logger.info("### Login Controller ###");
        return "login";
    }

    @PostMapping("/oltp/v1/login")
    public String loginWithEmail(LoginForm form, HttpSession session, Model model) {
        //login service 호출
        Boolean result = loginService.login(form.getEmailaddress());

        //로그인 성공: session에 추가 /main 접속
        //      실패: /login redirect
        if(result) {
            session.setAttribute("emailaddress", form.getEmailaddress());
            session.setAttribute("isLogin", "yes");
            logger.info("Login Controller : Login Success [" + form.getEmailaddress() + "]");
            return "redirect:/oltp/v1/main";
        } else {
            model.addAttribute("message", errCodeMsgService.getLoginFailMsg());
            model.addAttribute("searchUrl","/oltp/v1/login");
            logger.info("Login Controller : Login Fail [" + form.getEmailaddress() + "]");
            return "alert";
        }

    }


    @GetMapping("/oltp/v1/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/oltp/v1/login";
    }

}
