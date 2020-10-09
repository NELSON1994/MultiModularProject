package com.nelson.rokel.serviceInterface;

import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;

/*
 * @Aurthor:Nelson
 */
public interface TransactionsInterface {
    String switchReversalResponseCode(String reversalResponseCode);

    String switchResponseCodeFromWay4(String responseCodeFromWay4);

    String switchResponseCodeFromFLEX(String responseCodeFromFlex);

    ISOMsg processMTI1100(ISOMsg request) throws ISOException;

    ISOMsg processMTI100(ISOMsg request) throws ISOException;

    ISOMsg processMTI200(ISOMsg request) throws ISOException;

    ISOMsg processMTI0120(ISOMsg request) throws ISOException;

    ISOMsg processMTI400(ISOMsg request) throws ISOException;

    ISOMsg processMTI800(ISOMsg request) throws ISOException;

    ISOMsg defaultResponce(ISOMsg request) throws ISOException;

    ISOMsg processMTI800ForTest(ISOMsg request);

    ISOMsg accountValidationToFlex(ISOMsg msg);

    ISOMsg balanceInquiryToCoreBanking(ISOMsg msg);

    ISOMsg withdrawalToCoreBanking(ISOMsg msg);

    ISOMsg depositToCoreBanking(ISOMsg msg);

    ISOMsg fundTransferToCoreBanking(ISOMsg msg);

    ISOMsg balanceInquiryToWay4(ISOMsg msg);

    ISOMsg cashDepositToWay4(ISOMsg msg);

    ISOMsg cashWithdrawalToWay4(ISOMsg msg);

    String logISOMsg(ISOMsg msg);
}
