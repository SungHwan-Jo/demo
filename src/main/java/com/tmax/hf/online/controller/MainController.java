package com.tmax.hf.online.controller;

import com.tmax.hf.online.service.ErrCodeMsgService;
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
    private final ErrCodeMsgService errCodeMsgService;

    public MainController(ErrCodeMsgService errCodeMsgService) {
        this.errCodeMsgService = errCodeMsgService;
    }

    @GetMapping("/oltp/v1/main")
    public String main(Model model, HttpSession session){
        logger.warn("### Main Controller Start ###");
        //login 검사
        if (session.getAttribute("isLogin") == null){
            model.addAttribute("message", errCodeMsgService.getLoginTryMsg());
            model.addAttribute("searchUrl","/oltp/v1/login");
            return "alert";
        }

        model.addAttribute("appName", "DEMO WEB Application" );
        model.addAttribute("java", System.getProperty("java.specification.version"));
        model.addAttribute("fileEncoding", System.getProperty("file.encoding"));
        model.addAttribute("sessionId", session.getId());
        model.addAttribute("createTime", new java.util.Date(session.getCreationTime()).toString());
        model.addAttribute("sessionTimeout", session.getMaxInactiveInterval() / 60);
        model.addAttribute("isNew", session.isNew());
        return "main";
    }
}
