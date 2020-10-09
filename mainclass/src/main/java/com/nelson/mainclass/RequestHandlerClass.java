package com.nelson.mainclass;

import org.jpos.iso.*;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @Aurhor: NELSON
 * @Date: OCT:2020
 */

public class RequestHandlerClass implements ISORequestListener {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(RequestHandlerClass.class.getName());

    @Override
    public boolean process(ISOSource isoSource, ISOMsg isoMsg) {
        try{
            switch (isoMsg.getMTI()) {
                case "0100":
                    logger.info("**** Request ***********");
                    logger.info(ISOUtil.hexdump(isoMsg.pack()));
                    isoMsg.set(39,"00");
                    break;
                case "1200":
                    logger.info("**** Request ***********");
                    logger.info(ISOUtil.hexdump(isoMsg.pack()));
                    isoMsg.set(39,"00");
                    isoMsg.set(123,"JEFF MUTERU KINGI");
                    break;
                case "0200":
                    switch (isoMsg.getString(3)) {
                        case "310000":// TO FLEX ,,bALANCE INQUIRY
                            isoMsg.set(39,"09");
                            isoMsg.set(4,"0000000008000");
                            isoMsg.set(72,"Balance Inquiry to Flex");
                            logger.info("**** AMOUNT : {}",isoMsg.getString(4));
                            break;
                        case "010000":// CASH WITHDRAWAL WAY 4
                            isoMsg.set(39,"00");
                            break;
                        case "011000":// CASH WITHDRAWAL FLEX
                            isoMsg.set(39,"00");
                            break;
                        case "210000":// CASH DEPOSIT FLEX
                            isoMsg.set(39,"00");

                            break;
                        case "420000": // FUND TRANSFER FLEX
                            isoMsg.set(39,"00");
                            break;
                        case "280000": //  CASH DEPOSIT WAY 4
                            isoMsg.set(39,"00");
                            break;
                        default:
                            break;
                    }

                    break;
                case "0400":
                    isoMsg.set(39,"00");
                    break;
                case "0800":
                    isoMsg.set(39,"00");
                    break;
                default:
                    isoMsg.set(39,"06");
                    break;

            }
            isoMsg.setResponseMTI();
            logger.info("**** Response ***********");
            logger.info(ISOUtil.hexdump(isoMsg.pack()));


            isoSource.send(isoMsg);
        }

        catch(ISOException | IOException e){
            e.printStackTrace();
        }
      return true;
    }


    private String generateFLD37(){
        String auth=null;

        return auth;
    }
}
