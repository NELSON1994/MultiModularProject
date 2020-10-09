package com.nelson.rokel.service;


import com.nelson.rokel.packager.FlexCubePackager;
import com.nelson.rokel.packager.TransportPackager;
import com.nelson.rokel.serviceInterface.ConnectionInterface;
import com.nelson.rokel.serviceInterface.TransactionsInterface;
import com.nelson.rokel.serviceInterface.UFSInterface;
import com.nelson.rokel.wrappers.CheckConnectionWrapper;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISOPackager;
import org.jpos.iso.ISOUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
 * @Aurthor:Nelson.Otieno
 * @Date: Sep-2020
 */

@Service("transactioninterface")
public class Transactions implements TransactionsInterface {
    private static final Logger logger = LoggerFactory.getLogger(Transactions.class.getName());
    private static final Logger logger1 = LoggerFactory.getLogger(Transactions.class);
    @Autowired
    private ConnectionInterface connectionInterface;

    @Autowired
    private UFSInterface ufsInterface;

    @Override
    public String switchReversalResponseCode(String reversalResponseCode) {
        StringBuilder responsee = new StringBuilder();
        String meaning = null;
        String description = null;
        switch (reversalResponseCode) {
            case "06":
                meaning = "Error";
                description = "Indicates that the reason for the reversal was due to an error in a previous message. ";
                break;
            case "17":
                meaning = "Customer cancellation";
                description = "The cardholder aborted the transaction after an authorization request had been sent. ";
                break;
            case "32":
                meaning = "Completed partially ";
                description = "Used in reversal messages to indicate that a transaction was completed, but for an amount less than that given in the original 0100 request";
                break;
            case "68":
                meaning = "Response received too late ";
                description = "Used to indicate the reason for sending a reversal – an authorisation response was not returned within the time limit set by the acquirer.";
                break;
            case "96":
                meaning = "System malfunction";
                description = "The message could not be successfully processed due to a system malfunction";
                break;
            default:
                break;

        }
        responsee.append(reversalResponseCode).append("@").append(meaning).append("@").append(description);
        return responsee.toString();

    }

    @Override
    public String switchResponseCodeFromWay4(String responseCodeFromWay4) {
        StringBuilder response = new StringBuilder();
        final String group1 = "Approve";
        final String group2 = "Decline";
        final String group3 = "Pickup";
        final String group4 = "Invalid";
        String responseCodeDescription = null;
        String responseGroup = null;
        switch (responseCodeFromWay4) {
            case "00":
                responseCodeDescription = "Approved";
                responseGroup = group1;
                break;
            case "41":
                responseCodeDescription = "Lost card pick up";
                responseGroup = group3;
                break;
            case "43":
                responseCodeDescription = "Stolen card pick up";
                responseGroup = group3;
                break;
            case "04":
                responseCodeDescription = "Pick up card";
                responseGroup = group3;
                break;
            case "07":
                responseCodeDescription = "Pick up card, special condition";
                responseGroup = group3;
                break;
            case "01":
                responseCodeDescription = "Refer to Card Issuer";
                responseGroup = group2;
                break;
            case "02":
                responseCodeDescription = "Refer to Card Issuer, special condition";
                responseGroup = group2;
                break;
            case "03":
                responseCodeDescription = "Invalid Merchant";
                responseGroup = group2;
                break;
            case "05":
                responseCodeDescription = "Do not honour";
                responseGroup = group2;
                break;
            case "06":
                responseCodeDescription = "Error";
                responseGroup = group2;
                break;
            case "09":
                responseCodeDescription = "Request in progress";
                responseGroup = group2;
                break;
            case "12":
                responseCodeDescription = "Invalid Transaction";
                responseGroup = group2;
                break;
            case "13":
                responseCodeDescription = "Invalid Amount";
                responseGroup = group2;
                break;
            case "14":
                responseCodeDescription = "Invalid card number";
                responseGroup = group2;
                break;
            case "19":
                responseCodeDescription = "Re-enter transaction";
                responseGroup = group2;
                break;
            case "21":
                responseCodeDescription = "No action taken";
                responseGroup = group2;
                break;
            case "30":
                responseCodeDescription = "Format Error";
                responseGroup = group2;
                break;
            case "51":
                responseCodeDescription = "Not sufficient funds";
                responseGroup = group2;
                break;
            case "52":
                responseCodeDescription = "No checking account";
                responseGroup = group2;
                break;
            case "53":
                responseCodeDescription = "No saving account";
                responseGroup = group2;
                break;
            case "54":
                responseCodeDescription = "Expired Card";
                responseGroup = group2;
                break;
            case "55":
                responseCodeDescription = "PIN incorrect";
                responseGroup = group2;
                break;
            case "57":
                responseCodeDescription = "Transaction not allowed for cardholder";
                responseGroup = group2;
                break;
            case "58":
                responseCodeDescription = "Transaction not allowed for merchant";
                responseGroup = group2;
                break;
            case "61":
                responseCodeDescription = "Exceeds withdrawal amount limit";
                responseGroup = group2;
                break;
            case "62":
                responseCodeDescription = "Restricted card ";
                responseGroup = group2;
                break;
            case "63":
                responseCodeDescription = "Security violation";
                responseGroup = group2;
                break;
            case "65":
                responseCodeDescription = "Activity count limit exceeded";
                responseGroup = group2;
                break;
            case "75":
                responseCodeDescription = "PIN tries exceeded";
                responseGroup = group2;
                break;
            case "77":
                responseCodeDescription = "Inconsistent with original";
                responseGroup = group2;
                break;
            case "78":
                responseCodeDescription = "No account";
                responseGroup = group2;
                break;
            case "84":
                responseCodeDescription = "Pre-authorisation time too great";
                responseGroup = group2;
                break;
            case "86":
                responseCodeDescription = "Cannot verify PIN";
                responseGroup = group2;
                break;
            case "91":
                responseCodeDescription = "Issuer unavailable";
                responseGroup = group2;
                break;
            case "92":
                responseCodeDescription = "Invalid receiving institution id";
                responseGroup = group2;
                break;
            case "93":
                responseCodeDescription = "Transaction violates law";
                responseGroup = group2;
                break;
            case "94":
                responseCodeDescription = "Duplicate transaction";
                responseGroup = group2;
                break;
            case "96":
                responseCodeDescription = "System malfunctio";
                responseGroup = group2;
                break;
            default:
                responseCodeDescription = "Unknown Response Code From Way 4";
                responseGroup = group4;
                break;
        }
        response.append(responseCodeFromWay4).append("@").append(responseCodeDescription).append("@").append(responseGroup);
        return response.toString();
    }

    @Override
    public String switchResponseCodeFromFLEX(String responseCodeFromFlex) {
        StringBuilder responseDetails = new StringBuilder();
        String responseGroup = null;
        String responsecodeMeaning = null;
        switch (responseCodeFromFlex) {
            case "0000":
            case "000":
            case "00":
                responsecodeMeaning = "Transaction Success";
                responseGroup = "Approved";
                break;
            case "0003":
            case "003":
                responsecodeMeaning = "Approved-VIP";
                responseGroup = "Approved";
                break;
            case "4000":
            case "400":
                responsecodeMeaning = "Reversal accepted";
                responseGroup = "Approved";
                break;
            case "9114":
            case "914":
                responsecodeMeaning = "Not able to trace original";
                responseGroup = "Rejected";
                break;
            case "9125":
            case "940":
                responsecodeMeaning = "Database Error";
                responseGroup = "Rejected";
                break;
            case "1000":
            case "100":
            case "05":
                responsecodeMeaning = "Do not honor the Transaction";
                responseGroup = "Rejected";
                break;
            case "1019":
            case "12":
                responsecodeMeaning = "Invalid Transaction-Do not honor";
                responseGroup = "Rejected";
                break;
            case "119":
                responsecodeMeaning = "Invalid Transaction-Do not honor OR Transaction not allowed for customer";
                responseGroup = "Rejected";
                break;
            case "1010":
            case "110":
            case "13":
                responsecodeMeaning = "Invalid Amount Requested";
                responseGroup = "Rejected";
                break;
            case "1050":
            case "15":
                responsecodeMeaning = "No Such Issuer";
                responseGroup = "Rejected";
                break;
            case "9102":
            case "944":
            case "30":
                responsecodeMeaning = "Invalid ISO message format sent";
                responseGroup = "Rejected";
                break;
            case "1049":
            case "31":
                responsecodeMeaning = "Invalid Branch sent";
                responseGroup = "Rejected";
                break;
            case "183":
                responsecodeMeaning = "Invalid Branch sent OR No Such Issuer";
                responseGroup = "Rejected";
                break;
            case "1015":
            case "115":
            case "40":
                responsecodeMeaning = "Invalid Function Requested";
                responseGroup = "Rejected";
                break;
            case "1016":
            case "116":
            case "51":
                responsecodeMeaning = "Insufficient Funds";
                responseGroup = "Rejected";
                break;
            case "1036":
            case "52":
                responsecodeMeaning = "Account validation failed";
                responseGroup = "Rejected";
                break;
            case "187":
                responsecodeMeaning = "Invalid Acct number OR Account validation failed";
                responseGroup = "Rejected";
                break;
            case "1011":
            case "53":
                responsecodeMeaning = "Invalid Acct number";
                responseGroup = "Rejected";
                break;
            case "10":
                responsecodeMeaning = "Approved for partial amount";
                responseGroup = "Approved";
                break;
            case "1035":
            case "57":
                responsecodeMeaning = "Transaction not allowed for customer";
                responseGroup = "Rejected";
                break;
            case "9113":
            case "913":
            case "94":
                responsecodeMeaning = "Duplicate Message sent";
                responseGroup = "Rejected";
                break;
            case "9109":
            case "909":
            case "96":
                responsecodeMeaning = "Generic error";
                responseGroup = "Rejected";
                break;
            default:
                responsecodeMeaning = "Unknown";
                responseGroup = "Unknown";
                break;
        }
        responseDetails.append(responseCodeFromFlex).append("@").append(responsecodeMeaning).append("@").append(responseGroup);
        return responseDetails.toString();
    }


    @Override
    public ISOMsg processMTI1100(ISOMsg request) throws ISOException {
        ISOMsg response = new ISOMsg();
        logger.info("~~~MTI ~~~~~~~~ : {}", request.getMTI());
        logger.info("=== Transaction Start ==");
        String responsedq = logISOMsg(request);
        logger.info("\n** <REQUEST> ***\n {} \n ** </REQUEST> *** ", responsedq);
        switch (request.getString(3).strip()) {
            case "001000":
                logger.info("~~ PROC CODE : {}", request.getString(3));
                logger.info("*** LOG IN ***");
                response = request;
                break;
            case "001111":
                logger.info("~~ PROC CODE : {}", request.getString(3));
                logger.info("*** CHANGE PIN ***");
                response = request;
                break;
            case "001120":
                logger.info("~~ PROC CODE : {}", request.getString(3));
                logger.info("*** LOAD USERNAMES ***");
                response = request;
                break;
            case "001114":
                logger.info("~~ PROC CODE : {}", request.getString(3));
                logger.info("*** FIRST TIME LOG IN ***");
                response = request;
                break;
            case "000020":
                logger.info("~~ PROC CODE : {}", request.getString(3));
                logger.info("*** CREATE USER ***");
                response = request;
                break;
            case "011113":
                logger.info("~~ PROC CODE : {}", request.getString(3));
                logger.info("*** DELETE USER ***");
                response = request;
                break;
            case "011115":
                logger.info("~~ PROC CODE : {}", request.getString(3));
                logger.info("*** TERMINAL RESET ***");
                response = request;
                break;
            case "000120":
                logger.info("~~ PROC CODE : {}", request.getString(3));
                logger.info("*** DISABLE USER ***");
                response = request;
                break;
            case "000121":
                logger.info("~~ PROC CODE : {}", request.getString(3));
                logger.info("*** ENABLE USER ***");
                response = request;
                break;
            default:
                logger.info("~~~Unknown Transaction~");
                response = request;
                response.set(39, "06");
                response.setResponseMTI();
                response.set(72, "Unknown Transaction,Wrong Processing code");
                break;
        }
        logger.info("=== Transaction ENDS ==");
        return response;
    }


    @Override
    public ISOMsg processMTI100(ISOMsg request) throws ISOException {
        ISOMsg response = new ISOMsg();
        ISOPackager packager= new FlexCubePackager();
        ISOPackager packager1=new TransportPackager();
        logger.info("~~~MTI ~~~~~~~~ : {}", request.getMTI());
        logger.info("=== Transaction Start ==");
        String responsedq = logISOMsg(request);
        logger.info("\n** <REQUEST> ***\n {} \n ** </REQUEST> *** ", responsedq);
        switch (request.getString(3).strip()) {
            case "200000":// account validation to flex
                logger.info("~~~~ACCOUNT VALIDATION");
                logger.info("~~~ Processing Code  : {}", request.getString(3).strip());
                CheckConnectionWrapper wrapper = connectionInterface.checkConnectionDetailsToFlex();
                logger.info("~~~FLEX IP and PORT :{}|{}", wrapper.getIp(), wrapper.getPort());
                ISOMsg toflex = accountValidationToFlex(request);
                toflex.setPackager(packager);
                logger.info("== Packed ISO REQUEST=====\n {}\n=====================", ISOUtil.hexdump(toflex.pack()));
                response = connectionInterface.sendISOMessageToWay4(wrapper, toflex);
                response.setPackager(packager);
                logger.info("== Packed ISO RESPONSE=====\n {}\n=====================", ISOUtil.hexdump(response.pack()));
                logger.info("~~~FLEX Response MTI : {}", response.getMTI());
                logger.info("~~~FLEX Processing Code : {}", response.getString(3));
                logger.info("~~~FLEX Response  Code: {}", response.getString(39));
                String response6 = switchResponseCodeFromFLEX(response.getString(39).strip());
                String[] details6 = response6.split("@");
                response.set(60,details6[1]);
                logger.info("~~~FLEX Response Details~~~~ \n ** Resp Code : {}\n **Resp Description : {}\n **Resp Group : {}\n~~~~~~end of response details~~~~~~", details6[0], details6[1], details6[2]);

                break;
            case "300000": //magStripe Bal Inq Request==way 4===>balance TO default account
            case "301000"://balance TO saving account
            case "302000"://balance TO checking account
            case "303000"://balance TO credit card account
            case "304000"://balance TO universal account
                logger.info("~~~~Balance Inquiry To WAY 4~~");
                logger.info("~~~ Processing Code  : {}", request.getString(3).strip());
                CheckConnectionWrapper wrapper1 = connectionInterface.checkConnectionDetailsToWay4();
                logger.info("~~~WAY4 IP and PORT :{}|{}", wrapper1.getIp(), wrapper1.getPort());
                ISOMsg mes = balanceInquiryToWay4(request);
                mes.setPackager(packager1);
                logger.info("== Packed ISO REQUEST=====\n {}\n=====================", ISOUtil.hexdump(mes.pack()));
                response = connectionInterface.sendISOMessageToWay4(wrapper1, mes);
                response.setPackager(packager1);
                logger.info("== Packed ISO RESPONSE=====\n {}\n=====================", ISOUtil.hexdump(response.pack()));
                logger.info("~~~WAY 4 Response MTI : {}", response.getMTI());
                logger.info("~~~WAY 4 Processing Code : {}", response.getString(3));
                logger.info("~~~WAY 4 Response  Code: {}", response.getString(39));
                String responseData = switchResponseCodeFromWay4(response.getString(39).strip());
                String[] details = responseData.split("@");
                response.unset(60);
                response.set(60,details[1]);
                logger.info("~~~WAY 4 Response Details~~~~ \n ** Resp Code : {}\n **Resp Description : {}\n **Resp Group : {}\n~~~~~~end of response details~~~~~~", details[0], details[1], details[2]);

                break;
            default:
                logger.info("~~~Unknown Transaction~");
                response = request;
                response.setResponseMTI();
                response.set(39, "06");
                response.set(60,"Unknown Transaction");
                response.set(72, "Unknown Transaction,Wrong Processing code");
                break;
        }
        return response;
    }


    @Override
    public ISOMsg processMTI200(ISOMsg request) throws ISOException {
        ISOPackager packager= new FlexCubePackager();
        ISOPackager packager1=new TransportPackager();
        logger.info("~~~ FINANCIAL TRANSACTION");
        logger.info("~~~ MTI : {}", request.getMTI());
        ISOMsg isoMsg = new ISOMsg();
        ISOMsg response = new ISOMsg();
        String req = logISOMsg(request);
        logger.info("\n** <REQUEST> ***\n {} \n ** </REQUEST> *** ", req);
        switch (request.getString(3).strip()) {
            case "310000":
                logger.info("~~~ BALANCE INQUIRY To Flex~~");
                logger.info("~~~ Processing Code  : {}", request.getString(3).strip());
                CheckConnectionWrapper checkConnectionWrapper = connectionInterface.checkConnectionDetailsToFlex();
                logger.info("~~~ FLEX IP and PORT :{}|{}", checkConnectionWrapper.getIp(), checkConnectionWrapper.getPort());
                isoMsg = balanceInquiryToCoreBanking(request);
                isoMsg.setPackager(packager);
                logger.info("== Packed ISO REQUEST=====\n {}\n=====================", ISOUtil.hexdump(isoMsg.pack()));
                response = connectionInterface.sendISOMessageToFlex(checkConnectionWrapper, isoMsg);
                response.setPackager(packager);
                logger.info("== Packed ISO REQUEST=====\n {}\n=====================", ISOUtil.hexdump(response.pack()));
                logger.info("~~~ Flex Response MTI : {}", response.getMTI());
                logger.info("~~~ Flex Processing Code : {}", response.getString(3));
                logger.info("~~~ Flex Response  Code: {}", response.getString(39));
                String responsedqz = logISOMsg(response);
                logger.info("\n** <RESPONSE> ***\n {} \n ** </RESPONSE> *** ", responsedqz);
                String responseData = switchResponseCodeFromFLEX(response.getString(39).strip());
                String[] details = responseData.split("@");
                response.unset(60);
                response.set(60,details[1]);
                logger.info("~~~ Flex Response Details~~~~ \n ** Resp Code : {}\n **Resp Description : {}\n **Resp Group : {}\n~~~~~~end of response details~~~~~~", details[0], details[1], details[2]);
                break;
            case "010000":
                logger.info("~~~ CASH WITHDRAWAL To WAY 4~~");
                logger.info("~~~ Processing Code  : {}", request.getString(3).strip());
                CheckConnectionWrapper checkConnectionWrapper1 = connectionInterface.checkConnectionDetailsToWay4();
                logger.info("~~~ WAY 4 IP and PORT :{}|{}", checkConnectionWrapper1.getIp(), checkConnectionWrapper1.getPort());
                isoMsg = cashWithdrawalToWay4(request);
                isoMsg.setPackager(packager1);
                logger.info("== Packed ISO REQUEST=====\n {}\n=====================", ISOUtil.hexdump(isoMsg.pack()));
                response = connectionInterface.sendISOMessageToWay4(checkConnectionWrapper1, isoMsg);
                response.setPackager(packager1);
                logger.info("== Packed ISO REQUEST=====\n {}\n=====================", ISOUtil.hexdump(response.pack()));
                logger.info("~~~ WAY 4 Response MTI : {}", response.getMTI());
                logger.info("~~~ WAY 4 Processing Code : {}", response.getString(3));
                logger.info("~~~ WAY 4 Response  Code: {}", response.getString(39));
                String responsedqw = logISOMsg(response);
                logger.info("\n** <RESPONSE> ***\n {} \n ** </RESPONSE> *** ", responsedqw);
                String responseData1 = switchResponseCodeFromWay4(isoMsg.getString(39).strip());
                String[] details1 = responseData1.split("@");
                response.unset(60);
                response.set(60,details1[1]);
                logger.info("~~~ WAY 4 Response Details~~~~ \n ** Resp Code : {}\n **Resp Description : {}\n **Resp Group : {}\n~~~~~~end of response details~~~~~~", details1[0], details1[1], details1[2]);
                break;
            case "011000":
                logger.info("~~~ CASH WITHDRAWAL To Flex~~");
                logger.info("~~~ Processing Code  : {}", request.getString(3).strip());
                CheckConnectionWrapper wrapper = connectionInterface.checkConnectionDetailsToFlex();
                logger.info("~~~ FLEX IP and PORT :{}|{}", wrapper.getIp(), wrapper.getPort());
                isoMsg = withdrawalToCoreBanking(request);
                isoMsg.setPackager(packager);
                logger.info("== Packed ISO REQUEST=====\n {}\n=====================", ISOUtil.hexdump(isoMsg.pack()));
                response = connectionInterface.sendISOMessageToFlex(wrapper, isoMsg);
                response.setPackager(packager);
                logger.info("== Packed ISO REQUEST=====\n {}\n=====================", ISOUtil.hexdump(response.pack()));
                logger.info("~~~ FLEX Response MTI : {}", response.getMTI());
                logger.info("~~~ FLEX Processing Code : {}", response.getString(3));
                logger.info("~~~ FLEX Response  Code: {}", response.getString(39));
                String responsedq = logISOMsg(response);
                logger.info("\n** <RESPONSE> ***\n {} \n ** </RESPONSE> *** ", responsedq);
                String responseData2 = switchResponseCodeFromFLEX(response.getString(39).strip());
                String[] details2 = responseData2.split("@");
                response.unset(60);
                response.set(60,details2[1]);
                logger.info("~~~ Flex Response Details~~~~ \n ** Resp Code : {}\n **Resp Description : {}\n **Resp Group : {}\n~~~~~~end of response details~~~~~~", details2[0], details2[1], details2[2]);
                break;
            case "210000":
                logger.info("~~~ CASH DEPOSIT To Flex~~");
                logger.info("~~~ Processing Code  : {}", request.getString(3).strip());
                CheckConnectionWrapper checkConnectionWrapper2 = connectionInterface.checkConnectionDetailsToFlex();
                logger.info("~~~ FLEX IP and PORT :{}|{}", checkConnectionWrapper2.getIp(), checkConnectionWrapper2.getPort());
                isoMsg = depositToCoreBanking(request);
                isoMsg.setPackager(packager);
                logger.info("== Packed ISO REQUEST=====\n {}\n=====================", ISOUtil.hexdump(isoMsg.pack()));
                response = connectionInterface.sendISOMessageToFlex(checkConnectionWrapper2, isoMsg);
                response.setPackager(packager);
                logger.info("== Packed ISO RESPONSE=====\n {}\n=====================", ISOUtil.hexdump(response.pack()));
                logger.info("~~~ FLEX Response MTI : {}", response.getMTI());
                logger.info("~~~ FLEX Processing Code : {}", response.getString(3));
                logger.info("~~~ FLEX Response  Code: {}", response.getString(39));
                String responsedas = logISOMsg(response);
                logger.info("\n** <RESPONSE> ***\n {} \n ** </RESPONSE> *** ", responsedas);
                String responseData3 = switchResponseCodeFromFLEX(response.getString(39).strip());
                String[] details3 = responseData3.split("@");
                response.unset(60);
                response.set(60,details3[1]);
                logger.info("~~~ Flex Response Details~~~~ \n ** Resp Code : {}\n **Resp Description : {}\n **Resp Group : {}\n~~~~~~end of response details~~~~~~", details3[0], details3[1], details3[2]);

                break;
            case "420000":
                logger.info("~~~ FUND TRANSFER To Flex~~");
                logger.info("~~~ Processing Code  : {}", request.getString(3).strip());
                CheckConnectionWrapper checkConnectionWrapper3 = connectionInterface.checkConnectionDetailsToFlex();
                logger.info("~~~ FLEX IP and PORT :{}|{}", checkConnectionWrapper3.getIp(), checkConnectionWrapper3.getPort());
                isoMsg = fundTransferToCoreBanking(request);
                isoMsg.setPackager(packager);
                logger.info("== Packed ISO REQUEST=====\n {}\n=====================", ISOUtil.hexdump(isoMsg.pack()));
                response = connectionInterface.sendISOMessageToFlex(checkConnectionWrapper3, isoMsg);
                response.setPackager(packager);
                logger.info("== Packed ISO RESPONSE=====\n {}\n=====================", ISOUtil.hexdump(response.pack()));
                logger.info("~~~ FLEX Response MTI : {}", response.getMTI());
                logger.info("~~~ FLEX Processing Code : {}", response.getString(3));
                logger.info("~~~ FLEX Response  Code: {}", response.getString(39));
                String responsedd = logISOMsg(response);
                logger.info("\n** <RESPONSE> ***\n {} \n ** </RESPONSE> *** ", responsedd);
                String responseData4 = switchResponseCodeFromFLEX(response.getString(39).strip());
                String[] details4 = responseData4.split("@");
                response.unset(60);
                response.set(60,details4[1]);
                logger.info("~~~ Flex Response Details~~~~ \n ** Resp Code : {}\n **Resp Description : {}\n **Resp Group : {}\n~~~~~~end of response details~~~~~~", details4[0], details4[1], details4[2]);

                break;
            case "280000":
                logger.info("~~~ CASH DEPOSIT To WAY 4~~");
                logger.info("~~~ Processing Code  : {}", request.getString(3).strip());
                CheckConnectionWrapper checkConnectionWrapper4 = connectionInterface.checkConnectionDetailsToWay4();
                logger.info("~~~ FLEX IP and PORT :{}|{}", checkConnectionWrapper4.getIp(), checkConnectionWrapper4.getPort());
                isoMsg = cashDepositToWay4(request);
                isoMsg.setPackager(packager1);
                logger.info("== Packed ISO REQUEST=====\n {}\n=====================", ISOUtil.hexdump(isoMsg.pack()));
                response = connectionInterface.sendISOMessageToWay4(checkConnectionWrapper4, isoMsg);
                response.setPackager(packager1);
                logger.info("== Packed ISO RESPONSE=====\n {}\n=====================", ISOUtil.hexdump(response.pack()));
                logger.info("~~~ WAY 4 Response MTI : {}", response.getMTI());
                logger.info("~~~ WAY 4 Processing Code : {}", response.getString(3));
                logger.info("~~~ WAY 4 Response  Code: {}", response.getString(39));
                String responsed = logISOMsg(response);
                logger.info("\n** <RESPONSE> ***\n {} \n ** </RESPONSE> *** ", responsed);
                String responseData5 = switchResponseCodeFromWay4(response.getString(39).strip());
                String[] details5 = responseData5.split("@");
                response.unset(60);
                response.set(60,details5[1]);
                logger.info("~~~ WAY 4 Response Details~~~~ \n ** Resp Code : {}\n **Resp Description : {}\n **Resp Group : {}\n~~~~~~end of response details~~~~~~", details5[0], details5[1], details5[2]);

                break;
            default:
                logger.info("~~~ Unknown Financial Transaction~~");
                isoMsg = request;
                isoMsg.set(39, "06");
                isoMsg.setResponseMTI();
                response.unset(60);
                response.set(60,"Unknown Transaction");
                response.set(72, "Unknown Financial Transaction");
                String responsea = logISOMsg(isoMsg);
                logger.info("\n** <RESPONSE> ***\n {} \n ** </RESPONSE> *** ", responsea);
                break;
        }

        return isoMsg;
    }

    @Override
    public ISOMsg processMTI0120(ISOMsg request) throws ISOException {
        ISOPackager packager= new FlexCubePackager();
        ISOPackager packager1=new TransportPackager();
        String response = logISOMsg(request);
        ISOMsg res = new ISOMsg();
        logger.info("\n** <REQUEST> ***\n {} \n ** </REQUEST> *** ", response);
        request.setPackager(packager1);
        logger.info("== Packed ISO REQUEST=====\n {}\n=====================", ISOUtil.hexdump(request.pack()));
        CheckConnectionWrapper wrapper = connectionInterface.checkConnectionDetailsToWay4();
        try {
            ISOMsg isoMsg = connectionInterface.sendISOMessageToWay4(wrapper, request);
            isoMsg.setPackager(packager1);
            logger.info("== Packed ISO RESPONSE=====\n {}\n=====================", ISOUtil.hexdump(isoMsg.pack()));
            String responseDetails = switchResponseCodeFromWay4(isoMsg.getString(39));
            String[] responseData = responseDetails.split("@");
            System.out.println("~~Response Details~~~~~~~~~~~~\n Response Code :" + responseData[0] + "\n Response Description :" + responseData[1] + "\n Response Group :" + responseData[2] + "\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            String response1 = logISOMsg(isoMsg);
            logger.info("\n** <RESPONSE> ***\n {} \n ** </RESPONSE> *** ", response1);
            res = isoMsg;
        } catch (ISOException e) {
            e.printStackTrace();
            res = request;
            res.set(39, "06");
            res.set(72, e.getMessage());
            res.setResponseMTI();
            String responsep = logISOMsg(res);
            logger.info("\n** <RESPONSE> ***\n {} \n ** </RESPONSE> *** ", responsep);
        }

        return res;
    }

    @Override
    public ISOMsg processMTI400(ISOMsg request) throws ISOException {
        ISOPackager packager= new FlexCubePackager();
        ISOPackager packager1=new TransportPackager();
        String responsee = logISOMsg(request);
        logger.info("\n** <RESPONSE> ***\n {} \n ** </RESPONSE> *** ", responsee);
        ISOMsg isoMsg = request;
        isoMsg.setPackager(packager1);
        logger.info("== Packed ISO REQUEST=====\n {}\n=====================", ISOUtil.hexdump(isoMsg.pack()));
        isoMsg.set(39, "00");
        isoMsg.set(72, "success");
        String response = logISOMsg(isoMsg);
        logger.info("\n** <RESPONSE> ***\n {} \n ** </RESPONSE> *** ", response);
        return isoMsg;
    }

    @Override
    public ISOMsg processMTI800(ISOMsg request) throws ISOException {
        ISOPackager packager= new FlexCubePackager();
        ISOPackager packager1=new TransportPackager();
        ISOMsg res = new ISOMsg();
        switch (request.getString(3)) {
            case "900000": // echoTest to Flex
                request.unset(3);
                CheckConnectionWrapper checkConnectionWrapper = connectionInterface.checkConnectionDetailsToFlex();
                String iso = logISOMsg(request);
                logger.info("\n** <REQUEST> ***\n {} \n ** </REQUEST> *** ", iso);
                request.setPackager(packager);
                logger.info("== Packed ISO REQUEST=====\n {}\n=====================", ISOUtil.hexdump(request.pack()));

                try {
                    logger.info("****Processing Starts**");
                    ISOMsg isoMsg1 = connectionInterface.sendISOMessageToFlex(checkConnectionWrapper, request);
                    String response = logISOMsg(isoMsg1);
                    isoMsg1.setPackager(packager);
                    logger.info("== Packed ISO RESPONSE=====\n {}\n=====================", ISOUtil.hexdump(isoMsg1.pack()));
                    logger.info("\n** <RESPONSE> ***\n {} \n ** </RESPONSE> *** ", response);
                    String responseDetails = switchResponseCodeFromFLEX(isoMsg1.getString(39));
                    String[] responseData = responseDetails.split("@");
                    isoMsg1.unset(60);
                    isoMsg1.set(60,responseData[1]);
                    logger.info("*** Response Details*******\n  ~~~~~~Response Code~~~~~~ : {} \n ~~~Response Description~~~~ :{}\n ~~~~~~~~Response Group~~~~~~ : {}\n***********End Of Response Details*********", responseData[0], responseData[1], responseData[2]);
                    res = isoMsg1;
                } catch (ISOException e) {
                    res = request;
                    res.set(39, "06");
                    res.set(72, e.getMessage());
                    res.setResponseMTI();
                    String response = logISOMsg(res);
                    logger.info("\n** <RESPONSE> ***\n {} \n ** </RESPONSE> *** ", response);
                    logger.info("*****Response Details**\n  ~~Response Code~~ : 06 \n ~~~~~~Response Description~~~~ :{}\n****End Of Response Details***", e.getMessage());
                }

                break;

            default:
                request.unset(3);
                CheckConnectionWrapper checkConnectionWrapper1 = connectionInterface.checkConnectionDetailsToWay4();
                String iso1 = logISOMsg(request);
                logger.info("\n** <REQUEST> ***\n {} \n ** </REQUEST> *** ", iso1);
                request.setPackager(packager1);
                logger.info("== Packed ISO REQUEST=====\n {}\n=====================", ISOUtil.hexdump(request.pack()));
                try {
                    ISOMsg isoMsg1 = connectionInterface.sendISOMessageToWay4(checkConnectionWrapper1, request);
                    String responseDetails = switchResponseCodeFromWay4(isoMsg1.getString(39));
                    String iso2 = logISOMsg(isoMsg1);
                    isoMsg1.setPackager(packager1);// for packing purpose only
                    logger.info("== Packed ISO RESPONSE=====\n {}\n=====================", ISOUtil.hexdump(isoMsg1.pack()));
                    logger.info("\n** <RESPONSE> ***\n {} \n ** </RESPONSE> *** ", iso2);
                    String[] responseData = responseDetails.split("@");
                    isoMsg1.unset(60);
                    isoMsg1.set(60,responseData[1]);
                    logger.info("*******Response Details*****\n  ~~~~Response Code: {} \n ~~~~Response Description:{}\n ~~~~Response Group: {}\n********End Of Response Details*********", responseData[0], responseData[1], responseData[2]);
                    res = isoMsg1;
                } catch (ISOException e) {
                    res = request;
                    res.set(39, "06");
                    res.set(72, e.getMessage());
                    res.setResponseMTI();
                    String iso2 = logISOMsg(res);
                    logger.info("\n** <RESPONSE> ***\n {} \n ** </RESPONSE> *** ", iso2);
                    logger.info("*******Response Details******\n  ~~~~~~Response Code~~~~~~ : 06 \n ~~~~~~Response Description~~~~ :{}\n***********End Of Response Details*********", e.getMessage());
                }
                break;

        }

        return res;
    }

    @Override
    public ISOMsg defaultResponce(ISOMsg request) throws ISOException {
        ISOPackager packager = new TransportPackager();

        request.setPackager(packager);
        logger.info("********Packed ISO MSG Request******\n {}",ISOUtil.hexdump(request.pack()));
        logger.info("++++++++++++++++++++++++++++++++++++");
        ISOMsg response=request;
        response.set(39,"00");
        logger.info("********Packed ISO MSG Response******\n {}", ISOUtil.hexdump(response.pack()));
        logger.info("++++++++++++++++++++++++++++++++++++");
        return response;
    }

    @Override
    public ISOMsg processMTI800ForTest(ISOMsg request) {
        //    ISOPackager packager = new FlexCubePackager();
        FlexCubePackager packager = new FlexCubePackager();
        try {

            request.setPackager(packager);
            logger.info("*** Packager used : {}", packager);
            String req = logISOMsg(request);
            logger.info("*****=======request===\n {}", req);
            byte[] isoMessage = request.pack();
            logger.info("*** ISO Message **** \n +++> {} ", isoMessage);
            request.set(39, "00");
            request.setResponseMTI();
            String res = logISOMsg(request);
            logger.info("*****=======response===\n {}", res);
        } catch (ISOException e) {
            e.printStackTrace();
        }
        return request;
    }

    @Override
    public ISOMsg accountValidationToFlex(ISOMsg msg) {
        ISOMsg isoMsg = new ISOMsg();
        try {
            isoMsg.setMTI(msg.getMTI()); // will set to mti they need
            isoMsg.set(3, msg.getString(3));// will set to pro code they need
            isoMsg.set(7, msg.getString(7));
            isoMsg.set(11, msg.getString(11));
            isoMsg.set(12, msg.getString(12));
            isoMsg.set(41, msg.getString(41));
            isoMsg.set(42, msg.getString(42));
            isoMsg.set(37, msg.getString(37));
            isoMsg.set(102, msg.getString(102));
            logger.info("~~~~~~~ISO Message Fields consumed~~~~~~");
        } catch (ISOException e) {
            e.printStackTrace();
            logger.error("~~~~~~~ERROR~~~~~~{}", e.getMessage());
        }
        return isoMsg;
    }

    @Override
    public ISOMsg balanceInquiryToCoreBanking(ISOMsg msg) {
        ISOMsg isoMsg = new ISOMsg();
        try {
            isoMsg.setMTI(msg.getMTI());
            isoMsg.set(2, msg.getString(2));
            isoMsg.set(3, msg.getString(3));
            isoMsg.set(4, msg.getString(4));
            isoMsg.set(7, msg.getString(7));
            isoMsg.set(11, msg.getString(11));
            isoMsg.set(12, msg.getString(12));
            isoMsg.set(13, msg.getString(13));
            isoMsg.set(18, msg.getString(18));
            isoMsg.set(22, msg.getString(22));
            isoMsg.set(25, msg.getString(25));
            isoMsg.set(26, msg.getString(26));
            isoMsg.set(28, msg.getString(28));
            isoMsg.set(32, msg.getString(32));
            isoMsg.set(33, msg.getString(33));
            isoMsg.set(35, msg.getString(35));
            isoMsg.set(37, msg.getString(37));
            isoMsg.set(41, msg.getString(41));
            isoMsg.set(42, msg.getString(42));
            isoMsg.set(43, msg.getString(43));
            isoMsg.set(49, msg.getString(49));
            isoMsg.set(60, msg.getString(60));
            isoMsg.set(102, msg.getString(102));
            logger.info("~~~~~~~ISO Message Fields consumed~~~~~~");
        } catch (ISOException e) {
            e.printStackTrace();
            logger.error("~~~~~~~ERROR~~~~~~{}", e.getMessage());
        }

        return isoMsg;
    }

    @Override
    public ISOMsg withdrawalToCoreBanking(ISOMsg msg) {
        ISOMsg isoMsg = new ISOMsg();
        try {
            isoMsg.setMTI(msg.getMTI());
            isoMsg.set(2, msg.getString(2));
            isoMsg.set(3, "010000");
            isoMsg.set(4, msg.getString(4));
            isoMsg.set(7, msg.getString(7));
            isoMsg.set(11, msg.getString(11));
            isoMsg.set(12, msg.getString(12));
            isoMsg.set(13, msg.getString(13));
            isoMsg.set(18, msg.getString(18));
            isoMsg.set(22, msg.getString(22));
            isoMsg.set(25, msg.getString(25));
            isoMsg.set(26, msg.getString(26));
            isoMsg.set(28, msg.getString(28));
            isoMsg.set(32, msg.getString(32));
            isoMsg.set(33, msg.getString(33));
            isoMsg.set(35, msg.getString(35));
            isoMsg.set(37, msg.getString(37));
            isoMsg.set(41, msg.getString(41));
            isoMsg.set(42, msg.getString(42));
            isoMsg.set(43, msg.getString(43));
            isoMsg.set(49, msg.getString(49));
            isoMsg.set(60, msg.getString(60));
            isoMsg.set(102, msg.getString(102));
            logger.info("~~~~~~~ISO Message Fields consumed For Sending~~~~~~");
        } catch (ISOException e) {
            e.printStackTrace();
            logger.error("~~~~~~~ERROR~~~~~~{}", e.getMessage());
        }

        return isoMsg;
    }

    @Override
    public ISOMsg depositToCoreBanking(ISOMsg msg) {
        ISOMsg isoMsg = new ISOMsg();
        try {
            isoMsg.setMTI(msg.getMTI());
            isoMsg.set(2, msg.getString(2));
            isoMsg.set(3, "010000");
            isoMsg.set(4, msg.getString(4));
            isoMsg.set(7, msg.getString(7));
            isoMsg.set(11, msg.getString(11));
            isoMsg.set(12, msg.getString(12));
            isoMsg.set(13, msg.getString(13));
            isoMsg.set(18, msg.getString(18));
            isoMsg.set(22, msg.getString(22));
            isoMsg.set(25, msg.getString(25));
            isoMsg.set(26, msg.getString(26));
            isoMsg.set(28, msg.getString(28));
            isoMsg.set(32, msg.getString(32));
            isoMsg.set(33, msg.getString(33));
            isoMsg.set(35, msg.getString(35));
            isoMsg.set(37, msg.getString(37));
            isoMsg.set(41, msg.getString(41));
            isoMsg.set(42, msg.getString(42));
            isoMsg.set(43, msg.getString(43));
            isoMsg.set(49, msg.getString(49));
            isoMsg.set(60, msg.getString(60));
            isoMsg.set(103, msg.getString(103));
            logger.info("~~~~~~~ISO Message Fields consumed For Sending~~~~~~");
        } catch (ISOException e) {
            e.printStackTrace();
            logger.error("~~~~~~~ERROR~~~~~~{}", e.getMessage());
        }

        return isoMsg;
    }

    @Override
    public ISOMsg fundTransferToCoreBanking(ISOMsg msg) {
        ISOMsg isoMsg = new ISOMsg();
        try {
            isoMsg.setMTI(msg.getMTI());
            isoMsg.set(2, msg.getString(2));
            isoMsg.set(3, "010000");
            isoMsg.set(4, msg.getString(4));
            isoMsg.set(7, msg.getString(7));
            isoMsg.set(11, msg.getString(11));
            isoMsg.set(12, msg.getString(12));
            isoMsg.set(13, msg.getString(13));
            isoMsg.set(18, msg.getString(18));
            isoMsg.set(22, msg.getString(22));
            isoMsg.set(25, msg.getString(25));
            isoMsg.set(26, msg.getString(26));
            isoMsg.set(28, msg.getString(28));
            isoMsg.set(32, msg.getString(32));
            isoMsg.set(33, msg.getString(33));
            isoMsg.set(35, msg.getString(35));
            isoMsg.set(37, msg.getString(37));
            isoMsg.set(41, msg.getString(41));
            isoMsg.set(42, msg.getString(42));
            isoMsg.set(43, msg.getString(43));
            isoMsg.set(49, msg.getString(49));
            isoMsg.set(60, msg.getString(60));
            isoMsg.set(102, msg.getString(102));
            isoMsg.set(103, msg.getString(103));
            logger.info("~~~~~~~ISO Message Fields consumed For Sending~~~~~~");
        } catch (ISOException e) {
            e.printStackTrace();
            logger.error("~~~~~~~ERROR~~~~~~{}", e.getMessage());
        }

        return isoMsg;
    }


    @Override
    public ISOMsg balanceInquiryToWay4(ISOMsg msg) {
        ISOMsg isoMsg = new ISOMsg();
        try {
            isoMsg.setMTI(msg.getMTI());
            isoMsg.set(2, msg.getString(2));
            isoMsg.set(3, msg.getString(3));
            isoMsg.set(4, msg.getString(4));
            isoMsg.set(7, msg.getString(7));
            isoMsg.set(11, msg.getString(11));
            isoMsg.set(12, msg.getString(12));
            isoMsg.set(13, msg.getString(13));
            isoMsg.set(14, msg.getString(14));
            isoMsg.set(18, msg.getString(18));
            isoMsg.set(22, msg.getString(22));
            isoMsg.set(25, msg.getString(25));
            isoMsg.set(26, msg.getString(26));
            isoMsg.set(28, msg.getString(28));
            isoMsg.set(32, msg.getString(32));
            isoMsg.set(33, msg.getString(33));
            isoMsg.set(35, msg.getString(35));
            isoMsg.set(37, msg.getString(37));
            isoMsg.set(41, msg.getString(41));
            isoMsg.set(42, msg.getString(42));
            isoMsg.set(43, msg.getString(43));
            isoMsg.set(49, msg.getString(49));
            isoMsg.set(52, msg.getString(52));
            isoMsg.set(53, msg.getString(53));
            logger.info("~~~~~~~ISO Message Fields consumed~~~~~~");
        } catch (ISOException e) {
            //e.printStackTrace();
            logger.error("~~~~~~~ERROR~~~~~~{}", e.getMessage());
        }
        return isoMsg;
    }

    @Override
    public ISOMsg cashDepositToWay4(ISOMsg msg) {
        ISOMsg isoMsg = new ISOMsg();
        try {
            isoMsg.setMTI(msg.getMTI());
            isoMsg.set(2, msg.getString(2));
            isoMsg.set(3, msg.getString(3));
            isoMsg.set(4, msg.getString(4));
            isoMsg.set(7, msg.getString(7));
            isoMsg.set(11, msg.getString(11));
            isoMsg.set(12, msg.getString(12));
            isoMsg.set(13, msg.getString(13));
            isoMsg.set(14, msg.getString(14));
            isoMsg.set(18, msg.getString(18));
            isoMsg.set(22, msg.getString(22));
            isoMsg.set(25, msg.getString(25));
            isoMsg.set(26, msg.getString(26));
            isoMsg.set(28, msg.getString(28));
            isoMsg.set(32, msg.getString(32));
            isoMsg.set(33, msg.getString(33));
            isoMsg.set(35, msg.getString(35));
            isoMsg.set(37, msg.getString(37));
            isoMsg.set(41, msg.getString(41));
            isoMsg.set(42, msg.getString(42));
            isoMsg.set(43, msg.getString(43));
            isoMsg.set(49, msg.getString(49));
            isoMsg.set(52, msg.getString(52));
            isoMsg.set(53, msg.getString(53));
            logger.info("~~~~~~~ISO Message Fields consumed~~~~~~");
        } catch (ISOException e) {
            //e.printStackTrace();
            logger.error("~~~~~~~ERROR~~~~~~{}", e.getMessage());
        }
        return isoMsg;
    }

    @Override
    public ISOMsg cashWithdrawalToWay4(ISOMsg msg) {
        ISOMsg isoMsg = new ISOMsg();
        try {
            isoMsg.setMTI(msg.getMTI());
            isoMsg.set(2, msg.getString(2));
            isoMsg.set(3, msg.getString(3));
            isoMsg.set(4, msg.getString(4));
            isoMsg.set(7, msg.getString(7));
            isoMsg.set(11, msg.getString(11));
            isoMsg.set(12, msg.getString(12));
            isoMsg.set(13, msg.getString(13));
            isoMsg.set(14, msg.getString(14));
            isoMsg.set(18, msg.getString(18));
            isoMsg.set(22, msg.getString(22));
            isoMsg.set(25, msg.getString(25));
            isoMsg.set(26, msg.getString(26));
            isoMsg.set(28, msg.getString(28));
            isoMsg.set(32, msg.getString(32));
            isoMsg.set(33, msg.getString(33));
            isoMsg.set(35, msg.getString(35));
            isoMsg.set(37, msg.getString(37));
            isoMsg.set(41, msg.getString(41));
            isoMsg.set(42, msg.getString(42));
            isoMsg.set(43, msg.getString(43));
            isoMsg.set(49, msg.getString(49));
            isoMsg.set(52, msg.getString(52));
            isoMsg.set(53, msg.getString(53));
            logger.info("~~~~~~~ISO Message Fields consumed~~~~~~");
        } catch (ISOException e) {
            //e.printStackTrace();
            logger.error("~~~~~~~ERROR~~~~~~{}", e.getMessage());
        }
        return isoMsg;
    }

    @Override
    public String logISOMsg(ISOMsg msg) {
        StringBuilder finalStr = new StringBuilder("\n");
        try {
            finalStr.append("  MTI : ").append(msg.getMTI()).append("\n");
            for (int i = 1; i <= msg.getMaxField(); i++) {
                if (msg.hasField(i)) {
                    finalStr.append("Field-").append(i).append(" : ").append(msg.getString(i)).append("\n");
                }
            }
        } catch (ISOException e) {
            e.printStackTrace();
        }
        return finalStr.toString();
    }

}


