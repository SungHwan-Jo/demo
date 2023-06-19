package com.tmax.hf.online.repository;

import com.tmax.hf.online.domain.Zipcode;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

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
