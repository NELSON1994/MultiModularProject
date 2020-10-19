package com.nelson.rokel.service;

import com.nelson.rokel.packager.FlexCubePackager;
import com.nelson.rokel.packager.TransportPackager;
import com.nelson.rokel.serviceInterface.ConnectionInterface;
import com.nelson.rokel.wrappers.CheckConnectionWrapper;
import org.jpos.iso.*;
import org.jpos.iso.channel.PostChannel;
import org.jpos.util.LogSource;
import org.jpos.util.Logger;
import org.jpos.util.SimpleLogListener;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;

/*
@Author: Nelson
@Date: OCT-2020
 */

@Service("connectionInterface")
public class Connection implements ConnectionInterface {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(Connection.class.getName());
    @Value("${switch-port}")
    String port;

    @Value("${switch-ip}")
    String ip;

    @Value("${flex-port}")
    String flexport;

    @Value("${flex-ip}")
    String flexip;

    @Override
    public CheckConnectionWrapper checkConnectionDetailsToWay4() {
        CheckConnectionWrapper checkConnectionWrapper = new CheckConnectionWrapper();
        checkConnectionWrapper.setIp(ip);
        checkConnectionWrapper.setPort(Integer.parseInt(port));
        logger.info("*** WAY4 IP :{}", checkConnectionWrapper.getIp());
        logger.info("*** WAY4 PORT :{}", checkConnectionWrapper.getPort());
        return checkConnectionWrapper;

    }

    @Override
    public CheckConnectionWrapper checkConnectionDetailsToFlex() {
        CheckConnectionWrapper checkConnectionWrapper = new CheckConnectionWrapper();
        checkConnectionWrapper.setIp(flexip);
        checkConnectionWrapper.setPort(Integer.parseInt(flexport));
        logger.info("*** FLEX IP : {}", checkConnectionWrapper.getIp());
        logger.info("*** FLEX PORT :{}", checkConnectionWrapper.getPort());
        return checkConnectionWrapper;

    }

    @Override
    public ISOMsg sendISOMessageToWay4(CheckConnectionWrapper checkConnectionWrapper, ISOMsg receivedFromPos) throws ISOException {
        ISOMsg isoMsg1 = new ISOMsg();
        ISOMsg isoMsg2 = new ISOMsg();
        String now = ISODate.formatDate(new Date(), "hhmmss");
        isoMsg1.unset(12);
        isoMsg1.set(12, now);
        isoMsg1 = receivedFromPos;
        ISOPackager way4Packager = new TransportPackager();
        //ISOChannel postChannel = new PostChannel(checkConnectionWrapper.getIp(), checkConnectionWrapper.getPort(), way4Packager);
        BaseChannel postChannel = new PostChannel(checkConnectionWrapper.getIp(), checkConnectionWrapper.getPort(), way4Packager);
        logger.info("* TRANSACTION TO WAY 4 *");
        try {
            logger.info("** Initializing Connection To Way4 *");
            postChannel.connect();

            if (postChannel.isConnected()) {
                logger.info("** Connection Successful *");
                Logger logger1 = new Logger();
                logger1.addListener(new SimpleLogListener(System.out));
                ((LogSource) postChannel).setLogger(logger1, "connected-channel");
                //postChannel.setPackager(way4Packager);
                isoMsg1.setPackager(way4Packager);
                Date startSending = new Date();
                logger.info("~ Sending START Time~ : {}", startSending);
                logger.info("* Sending Request for Transaction **");
                postChannel.send(isoMsg1);
                logger.info("** Sending Complete **");
                Date endSending = new Date();
                logger.info("~ Sending COMPLETE Time : {}", endSending);
                logger.info("** Waiting for Response ");
                Date startReceiving = new Date();
                logger.info("~ Receiving START Time : {}", startReceiving);
                isoMsg2 = postChannel.receive();
                Date endReceiving = new Date();
                logger.info("~ Receiving COMPLETE Time : {}", endReceiving);
                logger.info("****Checking for Response");
                if (!isoMsg2.getMTI().isEmpty() && isoMsg2.hasField(39)) {
                    logger.info("*** Response Received **");
                    logger.info("~~~Response Data~~~~\n  >>>>> MTI : {}\n >>>>> Response Code : {}", isoMsg2.getMTI(), isoMsg2.getString(39));
                } else {
                    logger.info("** No Response Received *");
                    // will do Reversal for the transaction for Transaction
                    logger.info("******Automatic Reversal Request to WAY 4 To be Done****");

                    isoMsg2 = receivedFromPos;
                    isoMsg2.setResponseMTI();
                    isoMsg2.set(39, "99");
                    isoMsg2.set(72, "No Response");
                    logger.info("****Response Code : {}", isoMsg2.getString(39));
                    logger.info("****Description : {}", isoMsg2.getString(72));

                }
                logger.info("** Disconnecting Connection *");
                postChannel.disconnect();
                logger.info("** Disconnection Done *");
                isoMsg2.dump(System.out, "<RESPONSE FROM WAY 4>");

            } else {
                logger.error("** Connection Failed *");
                isoMsg2 = receivedFromPos;
                isoMsg2.setResponseMTI();
                isoMsg2.unset(72);
                isoMsg2.set(39, "99");
                isoMsg2.set(72, "Connection To Way4 Failed");
                logger.info("** Response Code ** : {} ", isoMsg2.getString(39));
                logger.info("** Description ** : {} ", isoMsg2.getString(72));
            }
        } catch (Exception e) {
            //e.printStackTrace();
            logger.error("** Error Occurred **");
            logger.info("** Error Experienced ** : {} ", e.getMessage());
            isoMsg2 = receivedFromPos;
            isoMsg2.set(39, "97");
            isoMsg2.set(72, e.getMessage());
            isoMsg2.setResponseMTI();

            logger.info("** Response Code ** : {}", isoMsg2.getString(39));
        }

        return isoMsg2;
    }

    @Override
    public ISOMsg sendISOMessageToFlex(CheckConnectionWrapper checkConnectionWrapper, ISOMsg receivedFromPos) throws ISOException {
        ISOMsg isoMsg1 = new ISOMsg();
        ISOMsg isoMsg2 = new ISOMsg();
        ISOMsg response = new ISOMsg();
        ISOMsg isoMsg3 = new ISOMsg();
        ;
        String now = ISODate.formatDate(new Date(), "hhmmss");
//        isoMsg1.unset(12);
//        isoMsg1.set(12, now);
        isoMsg1 = receivedFromPos;

        ISOPackager flexPackager = new FlexCubePackager();
        Logger loggerr = new Logger();
        //ISOChannel postChannel = new PostChannel(checkConnectionWrapper.getIp(), checkConnectionWrapper.getPort(), flexPackager);
        BaseChannel postChannel = new PostChannel(checkConnectionWrapper.getIp(), checkConnectionWrapper.getPort(), flexPackager);
        logger.info("** TRANSACTION TO FLEX CUBE");
        try {
            logger.info("** Trying to Connect *");
            postChannel.setTimeout(10000);// 10 seconds
            postChannel.connect();
            if (postChannel.isConnected()) {
                logger.info("** CONNECTION SUCCESSFUL");
                loggerr.addListener(new SimpleLogListener(System.out));
                ((LogSource) postChannel).setLogger(loggerr, "flex-channel");
               // postChannel.setPackager(flexPackager);
                isoMsg1.setPackager(flexPackager);
                logger.info("** Preparing To Send Transaction");
                postChannel.send(isoMsg1);
                logger.info("** Sending Complete");
                logger.info("** Waiting for Response *");
                postChannel.setTimeout(40000);// 40 seconds
                isoMsg2 = postChannel.receive();
                logger.info("** Response TimeOut : {}", postChannel.getTimeout());
                logger.info("** Checking for Response *");
                if (isoMsg2.hasMTI() && isoMsg2.hasField(39)) {
                    logger.info("** Response Received *");
                    logger.info("~~Response Data~\n" +
                            "   MTI : {}\n" +
                            "   Response Code: {}", isoMsg2.getMTI(), isoMsg2.getString(39));
                } else {
                    logger.info("** NO RESPONSE RECEIVED");
                    logger.info("** Transaction MTI : {}", receivedFromPos.getMTI());
                    logger.info("** Transaction Pro.Code : {}", receivedFromPos.getString(3));
                    logger.info("* Triggering Reversal Request *");
                    int retries = 0;
                    boolean reversed = false;
                    while (!reversed && retries < 3) {
                        isoMsg3 = isoMsg2;
                        isoMsg3.setMTI("0420");
                        logger.info("*Reversal MTI : {}", isoMsg3.getMTI());
                        logger.info("*Reversal Pro. Code : {}", isoMsg3.getString(3));
                        //postChannel.setPackager(flexPackager);
                        isoMsg3.setPackager(flexPackager);
                        logger.info("*Sending Reversal Transaction");
                        postChannel.send(isoMsg3);
                        logger.info("*Sending Complete*");
                        logger.info("*Waiting  For Response");
                        postChannel.setTimeout(50000);// 50 sec timeout
                        response = postChannel.receive();
                        logger.info("*Response Validation");
                        if (!response.getMTI().isEmpty() && response.hasField(39)) {
                            logger.info("* Reversal Response Received");
                            logger.info("* Reversal Response Code : {}", response.getString(39));
                            logger.info("Retries count : {}", retries);
                            if (response.getString(39).equalsIgnoreCase("00")) {
                                logger.info("* Reversal Successfull");

                                reversed = true;
                            } else {
                                logger.info("* Reversal Failed*");
                                reversed = false;
                            }
                        } else {
                            logger.info("* No Reversal Response Received");
                            logger.info(" Retries count : {}", retries);
                            logger.info("** Server Date : {}", new Date());
                            reversed = false;
                        }
                        retries++;
                    }

                    isoMsg2 = receivedFromPos;
                    isoMsg2.setResponseMTI();
                    //  isoMsg2.setRetransmissionMTI(); // to check how it is working
                    isoMsg2.set(39, "99");
                    logger.info("** Response Code** : {}", isoMsg2.getString(39));
                }
                logger.info("** Disconnection in-Progress");
                logger.info("** Server Date *** : {}", new Date());
                postChannel.disconnect();
                logger.info("** Disconnection Complete");
            } else {
                logger.info("** Connection Failed");
                logger.info("** Sending Connection Failure Response");
                logger.info("** Server Date *** : {}", new Date());
                isoMsg2 = receivedFromPos;
                isoMsg2.set(39, "97");
                isoMsg2.unset(72);
                isoMsg2.set(72, "Connection Failed");
                logger.info("*** Response Code : {}", isoMsg2.getString(39));
            }
        } catch (IOException e) {
            logger.error("****** Error Occurred");
            logger.info("** Error Experienced : {}", e.getMessage());
            logger.info("** Server Date *** : {}", new Date());
            isoMsg2 = receivedFromPos;
            isoMsg2.setResponseMTI();
            isoMsg2.set(39, "97");
            isoMsg2.unset(72);
            isoMsg2.set(72, e.getMessage());
            logger.info("*** Response Code : {}", isoMsg2.getString(39));
        }

        return isoMsg2;
    }

    public ISOMsg sendingReversalForNoResponseTransactions(ISOMsg isoMsg2,BaseChannel postChannel,ISOPackager packager) throws ISOException, IOException {
        ISOMsg isoMsg3=new ISOMsg();
        ISOMsg response=new ISOMsg();
        int retries = 0;
        boolean reversed = false;
        while (!reversed && retries < 3) {
            isoMsg3 = isoMsg2;
            isoMsg3.setMTI("0420");
            logger.info("*Reversal MTI : {}", isoMsg3.getMTI());
            logger.info("*Reversal Pro. Code : {}", isoMsg3.getString(3));
            postChannel.setPackager(packager);
            logger.info("*Sending Reversal Transaction *");
            postChannel.send(isoMsg3);
            logger.info("*Sending Complete*");
            logger.info("*Waiting  For Response*");
            response = postChannel.receive();
            postChannel.setTimeout(50000);// 50 sec timeout
            logger.info("*Response Validation*");
            if (!response.getMTI().isEmpty() && response.hasField(39)) {
                logger.info("* Reversal Response Received*");
                logger.info("** Server Date : {}", new Date());
                logger.info("* Reversal Response Code : {}", response.getString(39));
                logger.info("Retries count : {}", retries);
                if (response.getString(39).equalsIgnoreCase("00")) {
                    logger.info("* Reversal Successfull*");

                    reversed = true;
                } else {
                    logger.info("** Server Date : {}", new Date());
                    logger.info("* Reversal Failed*");
                    reversed = false;
                }
            } else {
                logger.info("* No Reversal Response Received*");
                logger.info(" Retries count : {}", retries);
                logger.info("** Server Date : {}", new Date());
                reversed = false;
            }
            retries++;
        }

        return response;
    }


    public void testConnection(CheckConnectionWrapper checkConnectionWrapper, ISOMsg fromPos) {
        Logger logger = new Logger();
        logger.addListener(new SimpleLogListener(System.out));
        // ServerChannel channel = new XMLChannel(new XMLPackager());
        ServerChannel channel = new PostChannel(checkConnectionWrapper.getIp(), checkConnectionWrapper.getPort(), new FlexCubePackager());
        try {
            System.out.println("~~~~~~~~Initializing Connection on FlexCube on Port~~~~~~ :::" + checkConnectionWrapper.getPort());
            channel.connect();
            System.out.println("~~~~~~~~Trying to Connect to FLEXCUBE on Port~~~~~~:::" + checkConnectionWrapper.getPort());
            if (channel.isConnected()) {
                System.out.println("~~~~~~~~Connected to FLEXCUBE Port~~~~~~:::" + checkConnectionWrapper.getPort());
                ISOMsg isotosend = new ISOMsg();
                isotosend.setMTI(fromPos.getMTI());
                if (isotosend.getMTI().equalsIgnoreCase("")) {
                    isotosend.setMTI("0800");
                }
                isotosend.set(3, "000000");
                isotosend.set(70, "301");
                channel.setPackager(new FlexCubePackager());
                channel.send(isotosend);
                ISOMsg response = channel.receive();
                response.dump(System.out, "~~~~~Response From FlexCube~~~~~~");

            } else {

                System.out.println("~~~~~~~~Trying to Reconnect to FLEXCUBE Port~~~~~~");
                channel.reconnect();
            }
        } catch (IOException | ISOException e) {
            e.printStackTrace();
        }
        ((LogSource) channel).setLogger(logger, "~~channel~~~~");
        ISOServer server = new ISOServer(checkConnectionWrapper.getPort(), channel, null);
        server.setLogger(logger, "server");
        new Thread(server).start();
    }

}
