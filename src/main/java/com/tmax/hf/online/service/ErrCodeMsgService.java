package com.tmax.hf.online.service;

import com.tmax.hf.online.repository.ErrCodeMsgRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ErrCodeMsgService {

    private final ErrCodeMsgRepository errCodeMsgRepository;

    @Autowired
    public ErrCodeMsgService(ErrCodeMsgRepository errCodeMsgRepository) {
        this.errCodeMsgRepository = errCodeMsgRepository;
    }
    public String getLoginFailMsg() {
        return errCodeMsgRepository.findByBizErrCd("U9998").get().getBizErrCdMsg();
    }

    public String getLoginTryMsg() {
        return errCodeMsgRepository.findByBizErrCd("U9997").get().getBizErrCdMsg();
    }
    public Object getUpdateAddressFailMsg() {
        return errCodeMsgRepository.findByBizErrCd("U9996").get().getBizErrCdMsg();
    }
    public String queryEventStatisticsFailMsg() {
        return errCodeMsgRepository.findByBizErrCd("U9995").get().getBizErrCdMsg();
    }
}
