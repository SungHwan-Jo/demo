package com.tmax.hf.online.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Pattern;

@Service
public class PointService {

    private final Logger logger = LogManager.getLogger(PointService.class);
    @Value("${externalip}")
    private String externalip;

    public String getPoint(String emailaddress){
        String pattern = "^[0-9]*$";
        String message="";
        String[] result;
        String urlStr = externalip + "/external_select.jsp?emailaddress=" + emailaddress;

        try {
            URL url = new URL(urlStr);
            HttpURLConnection urlconn = (HttpURLConnection)url.openConnection();
            if (urlconn != null) {
                urlconn.setConnectTimeout(10000); //10초 기단리 후 응답 없으면 종료
                urlconn.setRequestMethod("GET");
                urlconn.setDoInput(true); //InputStream으로 서버로 부터 응답을 받겠다는 옵션

                int resCode = urlconn.getResponseCode();
                if (resCode == HttpURLConnection.HTTP_OK) {
                    logger.info("PointService, External System Connect");
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
        if(Pattern.matches(pattern, result[4])){
            return "잔여포인트: " + result[4];
        }else{
            return result[4];
        }

    }
}
