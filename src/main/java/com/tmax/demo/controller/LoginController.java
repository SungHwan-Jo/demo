package com.tmax.demo.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    // Log4j Logger setting
    private final Logger logger = LogManager.getLogger(LoginController.class);
    @GetMapping("/login")
    public String login(){

        logger.warn("### Login Controller ###");
        return "login";
    }
}
