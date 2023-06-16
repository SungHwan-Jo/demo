package com.tmax.hf.repository;

import com.tmax.hf.domain.ErrCodeMsg;
import com.tmax.hf.domain.EventCodeMsg;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Optional;

@Repository
public class EventCodeMsgRepository {
    private final EntityManager em;

    public EventCodeMsgRepository (EntityManager em) {
        this.em = em;
    }

    public Optional<EventCodeMsg> findByEventcd(String eventcd) {
        EventCodeMsg eventCodeMsg = em.find(EventCodeMsg.class, eventcd);
        return Optional.ofNullable(eventCodeMsg);
    }
}
