package com.tmax.hf.service;

import com.tmax.hf.controller.PointController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Pattern;

@Service
public class PointService {

    private final Logger logger = LogManager.getLogger(PointService.class);
    public String getPoint(String emailaddress){
        String pattern = "^[0-9]*$";
        String message="";
        String[] result;
        String urlStr = "http://192.168.53.25:8081/external_select.jsp?emailaddress=" + emailaddress;
//        String urlStr = "http://192.168.171.98:8088/test.jsp?emailaddress=" + emailaddress;

        try {
            URL url = new URL(urlStr);
            HttpURLConnection urlconn = (HttpURLConnection)url.openConnection();
            if (urlconn != null) {
                urlconn.setConnectTimeout(10000); //10초 기단리 후 응답 없으면 종료
                urlconn.setRequestMethod("GET");
                urlconn.setDoInput(true); //InputStream으로 서버로 부터 응답을 받겠다는 옵션

                int resCode = urlconn.getResponseCode();
                if (resCode == HttpURLConnection.HTTP_OK) {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(urlconn.getInputStream()));
                    String line;
                    while(true){
                        line = reader.readLine();
                        if(line == null){
                            break;
                        }
                        message = message+line+",";
                    }
                    reader.close();
                }
                urlconn.disconnect();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        result = message.split(",");
        if(Pattern.matches(pattern, result[2])){
            return "잔여포인트: " + result[2];
        }else{
            return result[2];
        }

    }
}
