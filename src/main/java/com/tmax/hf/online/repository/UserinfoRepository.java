package com.tmax.hf.online.repository;

import com.tmax.hf.online.domain.Userinfo;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.sql.Timestamp;
import java.util.Optional;

@Repository
public class UserinfoRepository {
    private final EntityManager em;

    public UserinfoRepository (EntityManager em) {
        this.em = em;
    }

    public Optional<Userinfo> findByEmailaddress(String emailaddress) {
        Userinfo userinfo = em.find(Userinfo.class, emailaddress);
        return Optional.ofNullable(userinfo);
    }

    public void updateAddress(Userinfo userinfo, String zipcode, String zipaddress, String detailaddress) {
        Userinfo info = em.find(Userinfo.class, userinfo.getEmailaddress());

        info.setZipcode(zipcode);
        info.setZipaddress(zipaddress);
        info.setDetailaddress(detailaddress);
    }

    public Userinfo updateStatus(Userinfo userinfo) {
        Userinfo info = em.find(Userinfo.class, userinfo.getEmailaddress());
        Long Date = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(Date);
        info.setStatuscd("04");
        info.setUnregdate(timestamp);

        return info;

    }
}
