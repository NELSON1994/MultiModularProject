package com.nelson.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Service
//@Component
public class GetValueService {
    @Value("${nel.value}")
    String value;

    public String fetchValue(String val) {
        String resp = null;
        System.out.println("****Passed Path Variable******" + val);
        if (val.equalsIgnoreCase("nelson")) {
            System.out.println("Welcome :" + val);
            System.out.println("****Environment Variable is :" + value);
            resp = "Welcome in";
        } else {
            System.out.println("You are not allowed please.Thank you :" + val);
            System.out.println("****Environment Variable is :" + value);
            resp = "Go HOME";
        }
        System.out.println("****Fetched Value*****" + value);
        return resp;
    }

}
