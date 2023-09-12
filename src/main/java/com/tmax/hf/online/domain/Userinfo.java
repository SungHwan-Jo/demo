package com.tmax.hf.online.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "userinfo")
public class Userinfo {

    @Id
    private String emailaddress;
    private String zipcode;
    private String zipaddress;
    private String detailaddress;
    private Timestamp regdate;
    private Timestamp unregdate;
    private String statuscd;

    public String getEmailaddress() {
        return emailaddress;
    }

    public void setEmailaddress(String emailaddress) {
        this.emailaddress = emailaddress;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getZipaddress() {
        return zipaddress;
    }

    public void setZipaddress(String zipaddress) {
        this.zipaddress = zipaddress;
    }

    public String getDetailaddress() {
        return detailaddress;
    }

    public void setDetailaddress(String detailaddress) {
        this.detailaddress = detailaddress;
    }

    public Timestamp getRegdate() {
        return regdate;
    }

    public void setRegdate(Timestamp regdate) {
        this.regdate = regdate;
    }

    public Timestamp getUnregdate() {
        return unregdate;
    }

    public void setUnregdate(Timestamp unregdate) {
        this.unregdate = unregdate;
    }

    public String getStatuscd() {
        return statuscd;
    }

    public void setStatuscd(String statuscd) {
        this.statuscd = statuscd;
    }
}
