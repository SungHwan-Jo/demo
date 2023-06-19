package com.tmax.hf.online.service;

import com.tmax.hf.online.domain.Userinfo;
import com.tmax.hf.online.repository.UserinfoRepository;
import com.tmax.hf.online.service.LoginService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class LoginServiceTest {
    @Autowired
    LoginService lgoinService;
    @Autowired
    UserinfoRepository userinfoRepository;

    @Test
    public void login() {
        //email이 유효한지 검사
        //이메일 주소가 주어졌을 때
        Userinfo userinfo = new Userinfo();
        userinfo.setEmailaddress("20000@tmaxsoft.co.kr");

        //loginSevice에서 login 값 유효한지 check
        Boolean status = lgoinService.login(userinfo.getEmailaddress());

        assertThat(status.booleanValue()).isEqualTo(true);

    }

}