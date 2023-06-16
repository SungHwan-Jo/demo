package com.tmax.hf.service;

import com.tmax.hf.domain.Userinfo;
import com.tmax.hf.repository.UserinfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {

    private final UserinfoRepository userinfoRepository;

    @Autowired
    public LoginService(UserinfoRepository userinfoRepository) {
        this.userinfoRepository = userinfoRepository;
    }

    public Boolean login(String emailaddress) {
        //email이 유효한지 검사
        Optional<Userinfo> result = userinfoRepository.findByEmailaddress(emailaddress);
        if (result.isPresent()) {
            if (result.get().getStatuscd().equals("02")) {
                return true;
            }
        }
        return false;

    }
}
