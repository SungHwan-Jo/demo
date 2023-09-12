package com.tmax.hf.online.service;

import com.tmax.hf.online.controller.EventForm;
import com.tmax.hf.online.domain.Event;
import com.tmax.hf.online.domain.EventCodeMsg;
import com.tmax.hf.online.repository.ErrCodeMsgRepository;
import com.tmax.hf.online.repository.EventCodeMsgRepository;
import com.tmax.hf.online.repository.EventRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    private final ErrCodeMsgRepository errCodeMsgRepository;
    private final EventCodeMsgRepository eventCodeMsgRepository;
    private final EventRepository eventRepository;
    @Value("${batchip}")
    private String batchip;
    private final Logger logger = LogManager.getLogger(EventService.class);

    @Autowired
    public EventService(ErrCodeMsgRepository errCodeMsgRepository, EventCodeMsgRepository eventCodeMsgRepository, EventRepository eventRepository) {
        this.errCodeMsgRepository = errCodeMsgRepository;
        this.eventCodeMsgRepository = eventCodeMsgRepository;
        this.eventRepository = eventRepository;
    }

    public List<Event> queryEventStatistics(EventForm eventForm) {
        Boolean result = checkEventcode(eventForm.getEventCode());
        if (result == false){
            return null;
        }
        //event 통계 쿼리 실행
        return eventRepository.getEventList(eventForm.getEventCode());
    }

    public Boolean checkEventcode(String eventcode) {
        //event code 유효한지 검사
        Optional<EventCodeMsg> result = eventCodeMsgRepository.findByEventcd(eventcode);
        if (result.isPresent()) {
            return true;
        }
        return false;
    }



    public void regEvent(){
        String urlStr = batchip + "/api/v1/kafka";

        for(int i=10000; i < 40000; i++){
            try {
                URL url = new URL(urlStr);
                HttpURLConnection urlconn = (HttpURLConnection)url.openConnection();
                Date day = new Date(System.currentTimeMillis());
                if (urlconn != null) {
                    urlconn.setConnectTimeout(10000); //10초 기단리 후 응답 없으면 종료
                    urlconn.setRequestMethod("GET");
                    urlconn.setRequestProperty("emailaddress", String.valueOf(i)+"@tmaxsoft.co.kr");
                    urlconn.setRequestProperty("eventcd", "evt01");
                    urlconn.setRequestProperty("infoYN", "Y");
                    urlconn.setRequestProperty("giftcd", "gift01");
                    urlconn.setRequestProperty("regdt", String.valueOf(day.getTime()));
                    urlconn.setDoInput(true); //InputStream으로 서버로 부터 응답을 받겠다는 옵션

                    int resCode = urlconn.getResponseCode();
                    if (resCode == HttpURLConnection.HTTP_OK) {
                        logger.info("EventService, Event Register Connect");
                    }
                    urlconn.disconnect();

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }



    }
}
