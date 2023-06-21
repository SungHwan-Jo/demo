package com.tmax.hf.online.controller;

import com.tmax.hf.online.service.ErrCodeMsgService;
import com.tmax.hf.online.service.PointService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class PointController {

    // Log4j Logger setting
    private final Logger logger = LogManager.getLogger(PointController.class);
    private final PointService pointService;
    private final ErrCodeMsgService errCodeMsgService;

    @Autowired
    public PointController(PointService pointService, ErrCodeMsgService errCodeMsgService) {
        this.pointService = pointService;
        this.errCodeMsgService = errCodeMsgService;
    }

    /**
     *
     * @param session
     * @param model
     * @return
     * point 조회 처리 컨트롤러
     */
    @GetMapping("/oltp/v1/point")
    public String getPoint(HttpSession session, Model model) {
        //login 검사
        if (session.getAttribute("isLogin") == null){
            logger.debug("PointController, Login Failed emailaddress [" + session.getAttribute("emailaddress").toString() +"]");
            model.addAttribute("message", errCodeMsgService.getLoginTryMsg());
            model.addAttribute("searchUrl","/oltp/v1/login");
            return "alert";
        }
        String emailaddress = session.getAttribute("emailaddress").toString();
        String message;
        message = pointService.getPoint(emailaddress);
        model.addAttribute("message", message);
        logger.debug("PointController, " + message);

        return "point";
    }
}
