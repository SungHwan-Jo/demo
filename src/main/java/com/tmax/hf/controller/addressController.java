package com.tmax.hf.controller;

import com.tmax.hf.service.LoginService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class addressController {
    // Log4j Logger setting
    private final Logger logger = LogManager.getLogger(MainController.class);

    private final LoginService loginService;
    @Autowired
    public addressController(LoginService loginService) {
        this.loginService = loginService;
    }



    @GetMapping("/address")
    public String loadUserAddress(HttpSession session, Model model) {
        if (session.getAttribute("isLogin") == null){
            model.addAttribute("message", loginService.getLoginTryMsg());
            model.addAttribute("searchUrl","/login");
            return "alert";
        }
        logger.info("### Address Controller Start ### " + session.getAttribute("emailaddress").toString());
        model.addAttribute("userAddress", loginService.findOne(session.getAttribute("emailaddress").toString()).get());

        return "address";
    }

    @PostMapping("/address")
    public String updateUserAddress(HttpSession session, Model model, AddressForm addressForm) {
        //address service 호출
        Boolean result = loginService.updateAddress(addressForm, session.getAttribute("emailaddress").toString());
        if(result) {
            logger.info("Login Controller : Update Address Success [" + addressForm.getZipcode() + "]");
            return "redirect:/address";
        } else {
            model.addAttribute("message", loginService.getUpdateAddressFailMsg());
            model.addAttribute("searchUrl","/address");
            logger.info("Login Controller : Update Address Fail [" + addressForm.getZipcode() + "]");
            return "alert";
        }

    }
}
