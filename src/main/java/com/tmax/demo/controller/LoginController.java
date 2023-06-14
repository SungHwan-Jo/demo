package com.tmax.demo.controller;

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
    @GetMapping("/login")
    public String login(){

        logger.warn("### Login Controller ###");
        return "login";
    }

    @PostMapping("/login")
    public String loginWithEmail(LoginForm form, HttpSession session) {
        UUID uid = Optional.ofNullable(UUID.class.cast(session.getAttribute("uid")))
                .orElse(UUID.randomUUID());
        session.setAttribute("uid", uid);
        return "redirect:/main";
    }


    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/login";
    }
}
