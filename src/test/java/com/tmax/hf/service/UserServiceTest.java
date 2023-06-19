package com.tmax.hf.service;

import com.tmax.hf.domain.Userinfo;
import com.tmax.hf.repository.UserinfoRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Optional;
import java.util.regex.Pattern;


import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional //TEST시 DB에 데이터를 저장한 뒤 Commit을 하지 않는 것
class UserServiceTest {
    @Autowired
    UserinfoRepository userinfoRepository;

    @Autowired
    UserService userService;

    /**
     * 회원 탈퇴 TEST
     */
    @Test
    public void userBye() {
        Userinfo userinfo = this.findone("20000@tmaxsoft.co.kr").get();
        userService.userBye(userinfo);
        Userinfo userinfo1 = this.findone("20000@tmaxsoft.co.kr").get();
        assertThat(userinfo1.getStatuscd()).isEqualTo("04");

    }

    /**
     * 회원 조회 TEST
     */
    @Test
    public Optional<Userinfo> findone(String emailaddress) {
        return userinfoRepository.findByEmailaddress(emailaddress);
    }
}