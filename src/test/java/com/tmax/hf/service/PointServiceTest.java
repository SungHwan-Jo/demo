package com.tmax.hf.service;

import com.tmax.hf.repository.UserinfoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;

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