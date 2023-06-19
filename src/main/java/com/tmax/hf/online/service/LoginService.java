package com.tmax.hf.online.service;

import com.tmax.hf.online.controller.AddressForm;
import com.tmax.hf.online.domain.Userinfo;
import com.tmax.hf.online.domain.Zipcode;
import com.tmax.hf.online.repository.ErrCodeMsgRepository;
import com.tmax.hf.online.repository.UserinfoRepository;
import com.tmax.hf.online.repository.ZipcodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class LoginService {

    private final UserinfoRepository userinfoRepository;
    private final ErrCodeMsgRepository errCodeMsgRepository;
    private final ZipcodeRepository zipcodeRepository;

    @Autowired
    public LoginService(UserinfoRepository userinfoRepository, ErrCodeMsgRepository errCodeMsgRepository, ZipcodeRepository zipcodeRepository) {
        this.userinfoRepository = userinfoRepository;
        this.errCodeMsgRepository = errCodeMsgRepository;
        this.zipcodeRepository = zipcodeRepository;
    }

    public Boolean login(String emailaddress) {
        //email이 유효한지 검사
        Optional<Userinfo> result = userinfoRepository.findByEmailaddress(emailaddress);
        if (result.isPresent()) {
            if (result.get().getStatuscd().equals("02"))
                return true;
        }
        return false;

    }

    public Optional<Userinfo> findOne(String emailaddress) {
        return userinfoRepository.findByEmailaddress(emailaddress);
    }
    public Boolean updateAddress(AddressForm form, String emailaddress) {
        Boolean result = checkZipcode(form.getZipcode()); //zipcode 검증
        if (result == false){

            return false;
        }
        Userinfo userinfo = findOne(emailaddress).get();
        userinfoRepository.updateAddress(userinfo, form.getZipcode(), form.getZipaddress(), form.getDetailaddress());
        return true;
    }

    public Boolean checkZipcode(String zipcode) {
        //zipcode 유효한지 검사
        List<Zipcode> result = zipcodeRepository.findByZipcode(zipcode);
        if (result.size() != 0) {
            return true;
        }
        return false;
    }

}