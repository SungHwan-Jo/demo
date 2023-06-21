package com.tmax.hf.online.controller;

import com.tmax.hf.online.service.ErrCodeMsgService;
import com.tmax.hf.online.service.LoginService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class AddressController {
    // Log4j Logger setting
    private final Logger logger = LogManager.getLogger(MainController.class);

    private final LoginService loginService;
    private final ErrCodeMsgService errCodeMsgService;
    @Autowired
    public AddressController(LoginService loginService, ErrCodeMsgService errCodeMsgService) {
        this.loginService = loginService;
        this.errCodeMsgService = errCodeMsgService;
    }

    /**
     * oltp
     * 주소변경
     */
    @GetMapping("/oltp/v1/address")
    public String loadUserAddress(HttpSession session, Model model) {
        //login 검사
        if (session.getAttribute("isLogin") == null){
            logger.debug("AddressController, Login Failed emailaddress [" + session.getAttribute("emailaddress").toString() +"]");
            model.addAttribute("message", errCodeMsgService.getLoginTryMsg());
            model.addAttribute("searchUrl","/oltp/v1/login");
            return "alert";
        }
        model.addAttribute("userAddress", loginService.findOne(session.getAttribute("emailaddress").toString()).get());

        return "address";
    }

    @PostMapping("/oltp/v1/address")
    public String updateUserAddress(HttpSession session, Model model, AddressForm addressForm) {
        //address service 호출
        Boolean result = loginService.updateAddress(addressForm, session.getAttribute("emailaddress").toString());
        if(result) {
            logger.info("AddressController, Update Address Success");
            return "redirect:/oltp/v1/address";
        } else {
            model.addAttribute("message", errCodeMsgService.getUpdateAddressFailMsg());
            model.addAttribute("searchUrl","/oltp/v1/address");
            logger.debug("AddressController, Update Address Fail [" + addressForm.getZipcode() + "]");
            return "alert";
        }

    }
}
