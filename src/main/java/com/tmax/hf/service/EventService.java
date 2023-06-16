package com.tmax.hf.service;

import com.tmax.hf.controller.EventForm;
import com.tmax.hf.domain.Event;
import com.tmax.hf.domain.EventCodeMsg;
import com.tmax.hf.domain.Zipcode;
import com.tmax.hf.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    private final ErrCodeMsgRepository errCodeMsgRepository;
    private final EventCodeMsgRepository eventCodeMsgRepository;
    private final EventRepository eventRepository;

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
}