package com.tmax.hf.service;

import com.tmax.hf.domain.Userinfo;
import com.tmax.hf.repository.UserinfoRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
@Transactional
public class UserService {
    private final UserinfoRepository userinfoRepository;

    public UserService(UserinfoRepository userinfoRepository) {
        this.userinfoRepository = userinfoRepository;
    }

    /**
     * 회원 탈퇴
     */
    public String userBye(Userinfo userinfo) {
        //userinfo update
        Userinfo info = userinfoRepository.updateStatus(userinfo);
        String message="";
        String[] result;
        //external system 연동
        String urlStr = "http://192.168.53.25:8081/external_update.jsp?emailaddress=" + info.getEmailaddress()
                + "&&unregdt=" + info.getUnregdate().getTime() + "&&statuscd=" + info.getStatuscd();

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
        return result[4];
    }

    /**
     * 회원 조회
     */
    public Optional<Userinfo> findone(String emailaddress) {
        return userinfoRepository.findByEmailaddress(emailaddress);
    }
}
