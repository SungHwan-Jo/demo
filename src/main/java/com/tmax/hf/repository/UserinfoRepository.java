package com.tmax.hf.repository;

import com.tmax.hf.domain.Userinfo;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
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
}
