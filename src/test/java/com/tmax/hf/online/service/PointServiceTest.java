package com.tmax.hf.online.service;

import com.tmax.hf.online.service.PointService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PointServiceTest {

    @Autowired
    PointService pointService;

    @Test
    public void getPoint() {
        String emailaddress = "20000@tmaxsoft.co.kr";
        pointService.getPoint(emailaddress);
    }

}