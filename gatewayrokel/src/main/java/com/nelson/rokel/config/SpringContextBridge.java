package com.nelson.rokel.config;

//import com.gatewayDemo2.GatewayOwnSettingProject.servicesInterfaces.ConnectionInterface;
//import com.gatewayDemo2.GatewayOwnSettingProject.servicesInterfaces.TransactionsInterface;
//import com.gatewayDemo2.GatewayOwnSettingProject.servicesInterfaces.UFSInterface;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/*
* @Aurthor:Nelson
 */

@Component
public class SpringContextBridge implements SpringContextBridgeTemplate, ApplicationContextAware {
    private static ApplicationContext applicationContext;


//    @Autowired
//    private ConnectionInterface connectionInterface;
//
//    @Autowired
//    private TransactionsInterface transactionsInterface;
//
//    @Autowired
//    private UFSInterface ufsInterface;
//
//    @Override
//    public ConnectionInterface getConnectionInterface() {
//        return connectionInterface;
//    }
//
//    @Override
//    public TransactionsInterface getTransactionsInterface() {
//        return transactionsInterface;
//    }
//
//    @Override
//    public UFSInterface getUFSInterface() {
//        return ufsInterface;
//    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextBridge.applicationContext = applicationContext;
    }

    public static SpringContextBridgeTemplate services() {
        return applicationContext.getBean(SpringContextBridgeTemplate.class);
    }
}
