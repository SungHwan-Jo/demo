package com.tmax.hf.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "err_code_msg")
public class ErrCodeMsg {

    @Id
    @Column(name = "biz_err_cd")
    private String bizErrCd;
    @Column(name = "biz_err_cd_msg")
    private String bizErrCdMsg;

    public String getBizErrCd() {
        return bizErrCd;
    }

    public void setBizErrCd(String bizErrCd) {
        this.bizErrCd = bizErrCd;
    }

    public String getBizErrCdMsg() {
        return bizErrCdMsg;
    }

    public void setBizErrCdMsg(String bizErrCdMsg) {
        this.bizErrCdMsg = bizErrCdMsg;
    }
}
