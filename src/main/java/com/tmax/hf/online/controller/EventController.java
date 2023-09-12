package com.tmax.hf.online.controller;

import com.tmax.hf.online.domain.Event;
import com.tmax.hf.online.service.ErrCodeMsgService;
import com.tmax.hf.online.service.EventService;
import com.tmax.hf.online.service.LoginService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class EventController {

    private final Logger logger = LogManager.getLogger(LoginController.class);
    private final LoginService loginService;
    private final EventService eventService;
    private final ErrCodeMsgService errCodeMsgService;

    /**
     *
     * @param loginService
     * @param eventService
     * @param errCodeMsgService
     * eventController
     */
    @Autowired
    public EventController(LoginService loginService, EventService eventService, ErrCodeMsgService errCodeMsgService) {
        this.loginService = loginService;
        this.eventService = eventService;
        this.errCodeMsgService = errCodeMsgService;
    }

    /**
     *
     * @param session
     * @param model
     * @return
     * evenet 통계 조회
     */
    @GetMapping("/oltp/v1/event")
    public String event(HttpSession session, Model model){
        //login 검사
        if (session.getAttribute("isLogin") == null){
            logger.debug("EventController, Login Failed emailaddress [" + session.getAttribute("emailaddress").toString() +"]");
            model.addAttribute("message", errCodeMsgService.getLoginTryMsg());
            model.addAttribute("searchUrl","/oltp/v1/login");
            return "alert";
        }
        return "event";
    }

    @PostMapping("/oltp/v1/event")
    public String queryEventStatistics(Model model, EventForm eventForm){
        List<Event> result = eventService.queryEventStatistics(eventForm);
        if(result != null) {
            logger.info("EventController, Get Event Statistics Success");
            model.addAttribute("resultList",result);
            return "event";
        } else {
            model.addAttribute("message", errCodeMsgService.queryEventStatisticsFailMsg());
            model.addAttribute("searchUrl","/oltp/v1/event");
            logger.debug("EventController, Get Event Statistics Fail [" + eventForm.getEventCode() + "]");
            return "alert";
        }
    }
    @GetMapping("/oltp/v1/register")
    public void register(HttpSession session, Model model){
        //login 검사
        if (session.getAttribute("isLogin") == null){
            logger.debug("EventController, Login Failed emailaddress [" + session.getAttribute("emailaddress").toString() +"]");
            model.addAttribute("message", errCodeMsgService.getLoginTryMsg());
            model.addAttribute("searchUrl","/oltp/v1/login");
        }
        eventService.regEvent();
    }
}
