package com.tmax.hf.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "event_code_msg")
public class EventCodeMsg {
    @Id
    private String eventcd;
    private String eventcd_msg;
    private Timestamp eventstartdate;
    private Timestamp eventenddate;

    public String getEventcd() {
        return eventcd;
    }

    public void setEventcd(String eventcd) {
        this.eventcd = eventcd;
    }

    public String getEventcd_msg() {
        return eventcd_msg;
    }

    public void setEventcd_msg(String eventcd_msg) {
        this.eventcd_msg = eventcd_msg;
    }

    public Timestamp getEventstartdate() {
        return eventstartdate;
    }

    public void setEventstartdate(Timestamp eventstartdate) {
        this.eventstartdate = eventstartdate;
    }

    public Timestamp getEventenddate() {
        return eventenddate;
    }

    public void setEventenddate(Timestamp eventenddate) {
        this.eventenddate = eventenddate;
    }
}
