package com.nelson.mainclass;

import org.jpos.iso.ISOUtil;
import org.jpos.q2.Q2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.nelson.*"})
@EntityScan(basePackages = {"com.nelson.*"})
@ComponentScan(basePackages = {"com.nelson.*"})
public class ModularApplication {
    private static void startJPosServer(){
        JPosServer.getInstance();
    }
    public static void main(String[] args) {
        SpringApplication.run(ModularApplication.class, args);
        //start jposs
        startJPosServer();

        System.out.println("*******GATEWAY STARTED SUCCESSFULLY********");
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
