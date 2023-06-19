package com.tmax.hf.online.controller;

import com.tmax.hf.online.domain.Userinfo;
import com.tmax.hf.online.service.ErrCodeMsgService;
import com.tmax.hf.online.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class UserController {
    private final UserService userService;
    private final ErrCodeMsgService errCodeMsgService;

    public UserController(UserService userService, ErrCodeMsgService errCodeMsgService) {
        this.userService = userService;
        this.errCodeMsgService = errCodeMsgService;
    }

    /**
     *
     * @param model
     * @param session
     * @return
     * 회원탈퇴 컨트롤러
     */
    @GetMapping("/oltp/v1/bye")
    public String memberBye(Model model, HttpSession session){
        //login 검사
        if (session.getAttribute("isLogin") == null){
            model.addAttribute("message", errCodeMsgService.getLoginTryMsg());
            model.addAttribute("searchUrl","/oltp/v1/login");
            return "alert";
        }
        String message="";
        Optional<Userinfo> userinfo = userService.findone(session.getAttribute("emailaddress").toString());
        message = userService.userBye(userinfo.get());
        model.addAttribute("message", message);

        return "bye";

    }
}
