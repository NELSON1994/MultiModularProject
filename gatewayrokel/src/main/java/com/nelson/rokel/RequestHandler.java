package com.nelson.rokel;

import org.jpos.iso.*;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Random;

/*
@Author: Nelson
@Date: OCT-2020
 */

public class RequestHandler implements ISORequestListener {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(RequestHandler.class.getName());

    @Override
    public boolean process(ISOSource isoSource, ISOMsg isoMsg) {
        try {
            switch (isoMsg.getMTI()) {
                case "0100":
                    logger.info("**** Request ***********");
                    logger.info(ISOUtil.hexdump(isoMsg.pack()));
                    isoMsg.set(39, "00");
                    break;
                case "0420":
                    logger.info("**** Request ***********");
                    logger.info(ISOUtil.hexdump(isoMsg.pack()));
                    isoMsg.set(39, "00");
                    break;
                case "1420":
                    logger.info("**** Request ***********");
                    logger.info(ISOUtil.hexdump(isoMsg.pack()));
                    isoMsg.set(39, "00");
                    break;
                case "1200":
                    logger.info("**** Request ***********");
                    logger.info(ISOUtil.hexdump(isoMsg.pack()));
                    if (isoMsg.getString(103).equalsIgnoreCase("0192366622001")){
                        isoMsg.set(39, "00");
                        isoMsg.set(123, "JEFF MUTERU KINGI");
                    }
                    else{
                        isoMsg.set(39, "06");
                    }
                    logger.info("**** Response ***********");
                    logger.info(ISOUtil.hexdump(isoMsg.pack()));
                    break;
                case "0200":
                    switch (isoMsg.getString(3)) {
                        case "310000":
                            isoMsg.set(39, "00");
                            String amount=field4();
                            isoMsg.set(4, amount);
                            String fld54="0101404C" + amount;
                            isoMsg.set(54,fld54);
                            String fld37=fullFild37();
                            isoMsg.set(37,fld37);
                            isoMsg.set(72, "Balance Inquiry to Flex");
                            break;
                        case "010000":// CASH WITHDRAWAL WAY 4
                            isoMsg.set(39, "00");
                            break;
                        case "011000":// CASH WITHDRAWAL FLEX
                            isoMsg.set(39, "00");
                            break;
                        case "210000":// CASH DEPOSIT FLEX
                            isoMsg.set(39, "00");
                            break;
                        case "420000": // FUND TRANSFER FLEX
                            isoMsg.set(39, "00");
                            break;
                        case "280000": //  CASH DEPOSIT WAY 4
                            isoMsg.set(39, "00");
                            break;
                        default:
                            break;
                    }

                    break;
                case "0800":
                    isoMsg.set(39, "00");
                    break;
                default:
                    isoMsg.set(39, "06");
                    break;

            }
            isoMsg.setResponseMTI();
            logger.info("**** Response ***********");
            logger.info(ISOUtil.hexdump(isoMsg.pack()));

            isoSource.send(isoMsg);
        } catch (ISOException | IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    private String generateAuthFild37() {
        return String.format(String.valueOf(new Random().nextInt(999999)), "%06d");
    }

    private String generateAuthFild37B() {
        return String.format(String.valueOf(new Random().nextInt(999999)), "%06d");
    }

    private String generateFld4() {
        return String.format(String.valueOf(new Random().nextInt(999999)), "%06d");
    }

    private String fullFild37() {
        String auth = generateAuthFild37() + generateAuthFild37B();
        return auth;
    }
    private String field4(){
        String amount="0000"+ generateFld4()+"00";
        return amount;
    }

}
