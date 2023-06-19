package com.tmax.hf.controller;

import com.tmax.hf.service.PointService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.net.HttpURLConnection;
import java.net.URL;

@Controller
public class PointController {

    // Log4j Logger setting
    private final Logger logger = LogManager.getLogger(PointController.class);
    private final PointService pointService;

    @Autowired
    public PointController(PointService pointService) {
        this.pointService = pointService;
    }

    @GetMapping("/point")
    public String getPoint(HttpSession session, Model model) {
        String emailaddress = session.getAttribute("emailaddress").toString();
        String message;
        message = pointService.getPoint(emailaddress);
        model.addAttribute("message", message);
        logger.info("############### point message: " + message + "###################");

        return "point";
    }
}
