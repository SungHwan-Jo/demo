package com.tmax.demo.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;


@Controller
public class MainController {

    // Log4j Logger setting
    private final Logger logger = LogManager.getLogger(MainController.class);

    @GetMapping("/main")
    public String main(HttpSession session){
        if (session.getAttribute("emailaddress") == null){
            return "redirect:/login";
        }
        logger.warn("### Main Controller Start ###");
        return "main";
    }
}
