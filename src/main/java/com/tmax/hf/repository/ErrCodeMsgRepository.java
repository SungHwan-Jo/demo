package com.tmax.hf.repository;


import com.tmax.hf.domain.ErrCodeMsg;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Optional;

@Repository
public class ErrCodeMsgRepository {
    private final EntityManager em;

    public ErrCodeMsgRepository (EntityManager em) {
        this.em = em;
    }

    public Optional<ErrCodeMsg> findByBizErrCd(String bizErrCd) {
        ErrCodeMsg errCodeMsg = em.find(ErrCodeMsg.class, bizErrCd);
        return Optional.ofNullable(errCodeMsg);
    }

}
