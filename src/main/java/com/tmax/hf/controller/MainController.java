package com.tmax.hf.controller;

import com.tmax.hf.service.ErrCodeMsgService;
import com.tmax.hf.service.LoginService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;


@Controller
public class MainController {

    // Log4j Logger setting
    private final Logger logger = LogManager.getLogger(MainController.class);

    private final LoginService loginService;
    private final ErrCodeMsgService errCodeMsgService;
    @Autowired
    public MainController(LoginService loginService, ErrCodeMsgService errCodeMsgService) {
        this.loginService = loginService;
        this.errCodeMsgService = errCodeMsgService;
    }


    @GetMapping("/main")
    public String main(HttpSession session, Model model){
        if (session.getAttribute("isLogin") == null){
            model.addAttribute("message", errCodeMsgService.getLoginTryMsg());
            model.addAttribute("searchUrl","/login");
            return "alert";
        }
        logger.info("### Main Controller Start ###");
        return "main";
    }
}
