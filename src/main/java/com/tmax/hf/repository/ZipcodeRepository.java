package com.tmax.hf.repository;

import com.tmax.hf.domain.Userinfo;
import com.tmax.hf.domain.Zipcode;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public class ZipcodeRepository {
    private final EntityManager em;

    public ZipcodeRepository(EntityManager em) {
        this.em = em;
    }

    public List<Zipcode> findByZipcode(String zipCode) {
        return em.createQuery("select z from Zipcode z where z.zipcode = '" + zipCode + "'", Zipcode.class)
                .getResultList();
    }
}
