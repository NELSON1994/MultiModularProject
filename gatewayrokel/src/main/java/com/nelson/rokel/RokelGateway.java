package com.nelson.rokel;

import org.jpos.iso.ISOUtil;
import org.jpos.q2.Q2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RokelGateway {
    private static void startJPosServer(){
        JPosServer.getInstance();
    }
    public static void main(String[] args) {
        SpringApplication.run(RokelGateway.class,args);

        startJPosServer();
        System.out.println("===**** Gateway Started ***===========");
    }
}
class JPosServer{
    private static volatile JPosServer instance = null;
    static void getInstance(){
        if (instance == null) {
            synchronized (JPosServer.class){
                if (instance == null) {
                    instance = new JPosServer();
                }
            }
        }
    }
    private JPosServer(){
        try {
            new Q2("config/jpos").start();
            ISOUtil.sleep(2000);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
