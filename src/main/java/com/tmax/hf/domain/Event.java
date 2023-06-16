package com.tmax.hf.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
public class Event {

    @Id
    @Column(name = "o_emailaddress")
    private String emailAddress;
    @Column(name = "o_regdt")
    private Timestamp regdt;
    @Column(name = "o_totalpoint")
    private int point;

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Timestamp getRegdt() {
        return regdt;
    }

    public void setRegdt(Timestamp regdt) {
        this.regdt = regdt;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }
}
