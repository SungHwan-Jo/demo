package com.tmax.hf.online.domain;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "zipcode")
public class Zipcode {
    @Id
    private String zipcode;
    private String sido;
    private String sidoe;
    private String sigungu;
    private String sigungue;
    private String eupmyun;
    private String eupmyune;
    private String dorocode;
    private String doro;
    private String doroe;
    private String underground;
    private int buildingno1;
    private int buildingno2;
    private String buildingmanageno;
    private String daryang;
    private String building;
    private String dongcode;
    private String dong;
    private String ri;
    private String dongadmin;
    private String san;
    private int zibun1;
    private String zibunserial;
    private int zibun2;
    private String oldzipcode;
    private String zipcodeserial;

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getSido() {
        return sido;
    }

    public void setSido(String sido) {
        this.sido = sido;
    }

    public String getSidoe() {
        return sidoe;
    }

    public void setSidoe(String sidoe) {
        this.sidoe = sidoe;
    }

    public String getSigungu() {
        return sigungu;
    }

    public void setSigungu(String sigungu) {
        this.sigungu = sigungu;
    }

    public String getSigungue() {
        return sigungue;
    }

    public void setSigungue(String sigungue) {
        this.sigungue = sigungue;
    }

    public String getEupmyun() {
        return eupmyun;
    }

    public void setEupmyun(String eupmyun) {
        this.eupmyun = eupmyun;
    }

    public String getEupmyune() {
        return eupmyune;
    }

    public void setEupmyune(String eupmyune) {
        this.eupmyune = eupmyune;
    }

    public String getDorocode() {
        return dorocode;
    }

    public void setDorocode(String dorocode) {
        this.dorocode = dorocode;
    }

    public String getDoro() {
        return doro;
    }

    public void setDoro(String doro) {
        this.doro = doro;
    }

    public String getDoroe() {
        return doroe;
    }

    public void setDoroe(String doroe) {
        this.doroe = doroe;
    }

    public String getUnderground() {
        return underground;
    }

    public void setUnderground(String underground) {
        this.underground = underground;
    }

    public int getBuildingno1() {
        return buildingno1;
    }

    public void setBuildingno1(int buildingno1) {
        this.buildingno1 = buildingno1;
    }

    public int getBuildingno2() {
        return buildingno2;
    }

    public void setBuildingno2(int buildingno2) {
        this.buildingno2 = buildingno2;
    }

    public String getBuildingmanageno() {
        return buildingmanageno;
    }

    public void setBuildingmanageno(String buildingmanageno) {
        this.buildingmanageno = buildingmanageno;
    }

    public String getDaryang() {
        return daryang;
    }

    public void setDaryang(String daryang) {
        this.daryang = daryang;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getDongcode() {
        return dongcode;
    }

    public void setDongcode(String dongcode) {
        this.dongcode = dongcode;
    }

    public String getDong() {
        return dong;
    }

    public void setDong(String dong) {
        this.dong = dong;
    }

    public String getRi() {
        return ri;
    }

    public void setRi(String ri) {
        this.ri = ri;
    }

    public String getDongadmin() {
        return dongadmin;
    }

    public void setDongadmin(String dongadmin) {
        this.dongadmin = dongadmin;
    }

    public String getSan() {
        return san;
    }

    public void setSan(String san) {
        this.san = san;
    }

    public int getZibun1() {
        return zibun1;
    }

    public void setZibun1(int zibun1) {
        this.zibun1 = zibun1;
    }

    public String getZibunserial() {
        return zibunserial;
    }

    public void setZibunserial(String zibunserial) {
        this.zibunserial = zibunserial;
    }

    public int getZibun2() {
        return zibun2;
    }

    public void setZibun2(int zibun2) {
        this.zibun2 = zibun2;
    }

    public String getOldzipcode() {
        return oldzipcode;
    }

    public void setOldzipcode(String oldzipcode) {
        this.oldzipcode = oldzipcode;
    }

    public String getZipcodeserial() {
        return zipcodeserial;
    }

    public void setZipcodeserial(String zipcodeserial) {
        this.zipcodeserial = zipcodeserial;
    }
}
