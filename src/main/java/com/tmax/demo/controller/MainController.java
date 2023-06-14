package com.tmax.demo.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;


@Controller
public class MainController {

    // Log4j Logger setting
    private final Logger logger = LogManager.getLogger(MainController.class);

    @GetMapping("/main")
    public String main(HttpSession session, Model model){
        if (session.getAttribute("isLogin") == null){
            model.addAttribute("message", "로그인 후 다시 진행해주세요");
            model.addAttribute("searchUrl","/login");
            return "alert";
        }
        logger.warn("### Main Controller Start ###");
        return "main";
    }
}
