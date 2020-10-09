package com.nelson.rokel.service;

import com.nelson.rokel.entity.FailedTransaction;
import com.nelson.rokel.entity.OnlineActivity;
import com.nelson.rokel.entity.PosIris;
import com.nelson.rokel.repository.FailedTransactionRepository;
import com.nelson.rokel.repository.OnlineActivityRepository;
import com.nelson.rokel.repository.PosIrisRepository;
import com.nelson.rokel.serviceInterface.UFSInterface;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/*
 * @Aurthor:Nelson
 */
@Service("ufsInterface")
public class UFSService implements UFSInterface {
    private static final Logger logger = LoggerFactory.getLogger(UFSService.class);
    @Autowired
    private PosIrisRepository posIrisRepository;

    @Autowired
    private OnlineActivityRepository onlineActivityRepository;

    @Autowired
    private FailedTransactionRepository failedTransactionRepository;

    @Override
    public void splitPosIrisDataAtField112AndSave(String posIrisData) {
        PosIris posIris = new PosIris();
        //serail#app Version#osVersion#batteryLevel#chargingStatus#signalStrength#Temperature#terminalVersion#tid
        String[] posHeartBeat = posIrisData.split("#");
        posIris.setSerialNumber(posHeartBeat[0]);
        logger.info("~~Serial No : {}", posHeartBeat[0]);
        posIris.setAppVersion(posHeartBeat[1]);
        logger.info("~~Pos App Version : {}", posHeartBeat[1]);
        posIris.setOsVersion(posHeartBeat[2]);
        logger.info("~~OS Version : {}", posHeartBeat[2]);
        posIris.setBatteryLevel(posHeartBeat[3]);
        logger.info("~~Battery Level : {}", posHeartBeat[3]);
        posIris.setChargingStatus(posHeartBeat[4]);
        logger.info("~~Charging Status : {}", posHeartBeat[4]);
        posIris.setSignalStrength(posHeartBeat[5]);
        logger.info("~~Signal Strength : {}", posHeartBeat[5]);
        posIris.setTerminalTemperature(posHeartBeat[6]);
        logger.info("~~POS Temperature : {}", posHeartBeat[6]);
        posIris.setTerminalVersion(posHeartBeat[7]);
        posIris.setTid(posHeartBeat[8]);
        logger.info("~~TID : {}", posHeartBeat[8]);
        posIris.setObj("");
        posIris.setCreationDate(new Date());

        logger.info("*** Saving PosIris Data **");
        posIrisRepository.save(posIris);
        logger.info("*** Saving Complete **");


//         List<PosIris> list=posIrisRepository.findBySerialNumber(posHeartBeat[0]);
//         for (PosIris p:list){
//             System.out.println("~~~~~~~~~~~~~~~~~~OUTPUT~~~~~~~~"+p.getAppVersion());
//         }

    }

    @Override
    public boolean saveFailedTransactions(ISOMsg isoMsg) throws ISOException {
        FailedTransaction failedTransaction = new FailedTransaction();
        failedTransaction.setField0(isoMsg.getMTI());
        failedTransaction.setField1(isoMsg.getString(1));
        failedTransaction.setField2(isoMsg.getString(2));
        failedTransaction.setField3(isoMsg.getString(3));
        failedTransaction.setField4(isoMsg.getString(4));
        failedTransaction.setField5(isoMsg.getString(5));
        failedTransaction.setField6(isoMsg.getString(6));
        failedTransaction.setField7(isoMsg.getString(7));
        failedTransaction.setField8(isoMsg.getString(8));
        failedTransaction.setField9(isoMsg.getString(9));
        failedTransaction.setField10(isoMsg.getString(10));
        failedTransaction.setField11(isoMsg.getString(11));
        failedTransaction.setField12(isoMsg.getString(12));
        failedTransaction.setField13(isoMsg.getString(13));
        failedTransaction.setField14(isoMsg.getString(14));
        failedTransaction.setField15(isoMsg.getString(15));
        failedTransaction.setField16(isoMsg.getString(16));
        failedTransaction.setField17(isoMsg.getString(17));
        failedTransaction.setField18(isoMsg.getString(18));
        failedTransaction.setField19(isoMsg.getString(19));
        failedTransaction.setField20(isoMsg.getString(20));
        failedTransaction.setField21(isoMsg.getString(21));
        failedTransaction.setField22(isoMsg.getString(22));
        failedTransaction.setField23(isoMsg.getString(23));
        failedTransaction.setField24(isoMsg.getString(24));
        failedTransaction.setField25(isoMsg.getString(25));
        failedTransaction.setField26(isoMsg.getString(26));
        failedTransaction.setField27(isoMsg.getString(27));
        failedTransaction.setField28(isoMsg.getString(28));
        failedTransaction.setField29(isoMsg.getString(29));
        failedTransaction.setField30(isoMsg.getString(30));
        failedTransaction.setField31(isoMsg.getString(31));
        failedTransaction.setField32(isoMsg.getString(32));
        failedTransaction.setField33(isoMsg.getString(33));
        failedTransaction.setField34(isoMsg.getString(34));
        failedTransaction.setField35(isoMsg.getString(35));
        failedTransaction.setField36(isoMsg.getString(36));
        failedTransaction.setField37(isoMsg.getString(37));
        failedTransaction.setField38(isoMsg.getString(38));
        failedTransaction.setField39(isoMsg.getString(39));
        failedTransaction.setField40(isoMsg.getString(40));
        failedTransaction.setField41(isoMsg.getString(41));
        failedTransaction.setField42(isoMsg.getString(42));
        failedTransaction.setField43(isoMsg.getString(43));
        failedTransaction.setField44(isoMsg.getString(44));
        failedTransaction.setField45(isoMsg.getString(45));
        failedTransaction.setField46(isoMsg.getString(46));
        failedTransaction.setField47(isoMsg.getString(47));
        failedTransaction.setField48(isoMsg.getString(48));
        failedTransaction.setField49(isoMsg.getString(49));
        failedTransaction.setField50(isoMsg.getString(50));
        failedTransaction.setField51(isoMsg.getString(51));
        failedTransaction.setField52(isoMsg.getString(52));
        failedTransaction.setField53(isoMsg.getString(53));
        failedTransaction.setField54(isoMsg.getString(54));
        failedTransaction.setField55(isoMsg.getString(55));
        failedTransaction.setField56(isoMsg.getString(56));
        failedTransaction.setField57(isoMsg.getString(57));
        failedTransaction.setField58(isoMsg.getString(58));
        failedTransaction.setField59(isoMsg.getString(59));
        failedTransaction.setField60(isoMsg.getString(60));
        failedTransaction.setField61(isoMsg.getString(61));
        failedTransaction.setField62(isoMsg.getString(62));
        failedTransaction.setField63(isoMsg.getString(63));
        failedTransaction.setField64(isoMsg.getString(64));
        failedTransaction.setField65(isoMsg.getString(65));
        failedTransaction.setField66(isoMsg.getString(66));
        failedTransaction.setField67(isoMsg.getString(67));
        failedTransaction.setField68(isoMsg.getString(68));
        failedTransaction.setField69(isoMsg.getString(69));
        failedTransaction.setField70(isoMsg.getString(70));
        failedTransaction.setField71(isoMsg.getString(71));
        failedTransaction.setField72(isoMsg.getString(72));
        failedTransaction.setField73(isoMsg.getString(73));
        failedTransaction.setField74(isoMsg.getString(74));
        failedTransaction.setField75(isoMsg.getString(75));
        failedTransaction.setField76(isoMsg.getString(76));
        failedTransaction.setField77(isoMsg.getString(77));
        failedTransaction.setField78(isoMsg.getString(78));
        failedTransaction.setField79(isoMsg.getString(79));
        failedTransaction.setField80(isoMsg.getString(80));
        failedTransaction.setField81(isoMsg.getString(81));
        failedTransaction.setField82(isoMsg.getString(82));
        failedTransaction.setField83(isoMsg.getString(83));
        failedTransaction.setField84(isoMsg.getString(84));
        failedTransaction.setField85(isoMsg.getString(85));
        failedTransaction.setField86(isoMsg.getString(86));
        failedTransaction.setField87(isoMsg.getString(87));
        failedTransaction.setField88(isoMsg.getString(88));
        failedTransaction.setField89(isoMsg.getString(89));
        failedTransaction.setField90(isoMsg.getString(90));
        failedTransaction.setField91(isoMsg.getString(91));
        failedTransaction.setField92(isoMsg.getString(92));
        failedTransaction.setField93(isoMsg.getString(93));
        failedTransaction.setField94(isoMsg.getString(94));
        failedTransaction.setField95(isoMsg.getString(95));
        failedTransaction.setField96(isoMsg.getString(96));
        failedTransaction.setField97(isoMsg.getString(97));
        failedTransaction.setField98(isoMsg.getString(98));
        failedTransaction.setField99(isoMsg.getString(99));
        failedTransaction.setField100(isoMsg.getString(100));
        failedTransaction.setField101(isoMsg.getString(101));
        failedTransaction.setField102(isoMsg.getString(102));
        failedTransaction.setField103(isoMsg.getString(103));
        failedTransaction.setField104(isoMsg.getString(104));
        failedTransaction.setField105(isoMsg.getString(105));
        failedTransaction.setField106(isoMsg.getString(106));
        failedTransaction.setField107(isoMsg.getString(107));
        failedTransaction.setField108(isoMsg.getString(108));
        failedTransaction.setField109(isoMsg.getString(109));
        failedTransaction.setField110(isoMsg.getString(110));
        failedTransaction.setField111(isoMsg.getString(111));
        failedTransaction.setField112(isoMsg.getString(112));
        failedTransaction.setField113(isoMsg.getString(113));
        failedTransaction.setField114(isoMsg.getString(114));
        failedTransaction.setField115(isoMsg.getString(115));
        failedTransaction.setField116(isoMsg.getString(116));
        failedTransaction.setField117(isoMsg.getString(117));
        failedTransaction.setField118(isoMsg.getString(118));
        failedTransaction.setField119(isoMsg.getString(119));
        failedTransaction.setField120(isoMsg.getString(120));
        failedTransaction.setField121(isoMsg.getString(121));
        failedTransaction.setField122(isoMsg.getString(122));
        failedTransaction.setField123(isoMsg.getString(123));
        failedTransaction.setField124(isoMsg.getString(124));
        failedTransaction.setField125(isoMsg.getString(125));
        failedTransaction.setField126(isoMsg.getString(126));
        failedTransaction.setField127(isoMsg.getString(127));
        failedTransaction.setField128(isoMsg.getString(128));
        failedTransactionRepository.save(failedTransaction);
        return true;
    }

    @Override
    public boolean saveToOnlineActivity(ISOMsg isoMsg) throws ISOException {
        OnlineActivity onlineActivity = new OnlineActivity();
        onlineActivity.setField0(isoMsg.getMTI());
        onlineActivity.setField1(isoMsg.getString(1));
        onlineActivity.setField2(isoMsg.getString(2));
        onlineActivity.setField3(isoMsg.getString(3));
        onlineActivity.setField4(isoMsg.getString(4));
        onlineActivity.setField5(isoMsg.getString(5));
        onlineActivity.setField6(isoMsg.getString(6));
        onlineActivity.setField7(isoMsg.getString(7));
        onlineActivity.setField8(isoMsg.getString(8));
        onlineActivity.setField9(isoMsg.getString(9));
        onlineActivity.setField10(isoMsg.getString(10));
        onlineActivity.setField11(isoMsg.getString(11));
        onlineActivity.setField12(isoMsg.getString(12));
        onlineActivity.setField13(isoMsg.getString(13));
        onlineActivity.setField14(isoMsg.getString(14));
        onlineActivity.setField15(isoMsg.getString(15));
        onlineActivity.setField16(isoMsg.getString(16));
        onlineActivity.setField17(isoMsg.getString(17));
        onlineActivity.setField18(isoMsg.getString(18));
        onlineActivity.setField19(isoMsg.getString(19));
        onlineActivity.setField20(isoMsg.getString(20));
        onlineActivity.setField21(isoMsg.getString(21));
        onlineActivity.setField22(isoMsg.getString(22));
        onlineActivity.setField23(isoMsg.getString(23));
        onlineActivity.setField24(isoMsg.getString(24));
        onlineActivity.setField25(isoMsg.getString(25));
        onlineActivity.setField26(isoMsg.getString(26));
        onlineActivity.setField27(isoMsg.getString(27));
        onlineActivity.setField28(isoMsg.getString(28));
        onlineActivity.setField29(isoMsg.getString(29));
        onlineActivity.setField30(isoMsg.getString(30));
        onlineActivity.setField31(isoMsg.getString(31));
        onlineActivity.setField32(isoMsg.getString(32));
        onlineActivity.setField33(isoMsg.getString(33));
        onlineActivity.setField34(isoMsg.getString(34));
        onlineActivity.setField35(isoMsg.getString(35));
        onlineActivity.setField36(isoMsg.getString(36));
        onlineActivity.setField37(isoMsg.getString(37));
        onlineActivity.setField38(isoMsg.getString(38));
        onlineActivity.setField39(isoMsg.getString(39));
        onlineActivity.setField40(isoMsg.getString(40));
        onlineActivity.setField41(isoMsg.getString(41));
        onlineActivity.setField42(isoMsg.getString(42));
        onlineActivity.setField43(isoMsg.getString(43));
        onlineActivity.setField44(isoMsg.getString(44));
        onlineActivity.setField45(isoMsg.getString(45));
        onlineActivity.setField46(isoMsg.getString(46));
        onlineActivity.setField47(isoMsg.getString(47));
        onlineActivity.setField48(isoMsg.getString(48));
        onlineActivity.setField49(isoMsg.getString(49));
        onlineActivity.setField50(isoMsg.getString(50));
        onlineActivity.setField51(isoMsg.getString(51));
        onlineActivity.setField52(isoMsg.getString(52));
        onlineActivity.setField53(isoMsg.getString(53));
        onlineActivity.setField54(isoMsg.getString(54));
        onlineActivity.setField55(isoMsg.getString(55));
        onlineActivity.setField56(isoMsg.getString(56));
        onlineActivity.setField57(isoMsg.getString(57));
        onlineActivity.setField58(isoMsg.getString(58));
        onlineActivity.setField59(isoMsg.getString(59));
        onlineActivity.setField60(isoMsg.getString(60));
        onlineActivity.setField61(isoMsg.getString(61));
        onlineActivity.setField62(isoMsg.getString(62));
        onlineActivity.setField63(isoMsg.getString(63));
        onlineActivity.setField64(isoMsg.getString(64));
        onlineActivity.setField65(isoMsg.getString(65));
        onlineActivity.setField66(isoMsg.getString(66));
        onlineActivity.setField67(isoMsg.getString(67));
        onlineActivity.setField68(isoMsg.getString(68));
        onlineActivity.setField69(isoMsg.getString(69));
        onlineActivity.setField70(isoMsg.getString(70));
        onlineActivity.setField71(isoMsg.getString(71));
        onlineActivity.setField72(isoMsg.getString(72));
        onlineActivity.setField73(isoMsg.getString(73));
        onlineActivity.setField74(isoMsg.getString(74));
        onlineActivity.setField75(isoMsg.getString(75));
        onlineActivity.setField76(isoMsg.getString(76));
        onlineActivity.setField77(isoMsg.getString(77));
        onlineActivity.setField78(isoMsg.getString(78));
        onlineActivity.setField79(isoMsg.getString(79));
        onlineActivity.setField80(isoMsg.getString(80));
        onlineActivity.setField81(isoMsg.getString(81));
        onlineActivity.setField82(isoMsg.getString(82));
        onlineActivity.setField83(isoMsg.getString(83));
        onlineActivity.setField84(isoMsg.getString(84));
        onlineActivity.setField85(isoMsg.getString(85));
        onlineActivity.setField86(isoMsg.getString(86));
        onlineActivity.setField87(isoMsg.getString(87));
        onlineActivity.setField88(isoMsg.getString(88));
        onlineActivity.setField89(isoMsg.getString(89));
        onlineActivity.setField90(isoMsg.getString(90));
        onlineActivity.setField91(isoMsg.getString(91));
        onlineActivity.setField92(isoMsg.getString(92));
        onlineActivity.setField93(isoMsg.getString(93));
        onlineActivity.setField94(isoMsg.getString(94));
        onlineActivity.setField95(isoMsg.getString(95));
        onlineActivity.setField96(isoMsg.getString(96));
        onlineActivity.setField97(isoMsg.getString(97));
        onlineActivity.setField98(isoMsg.getString(98));
        onlineActivity.setField99(isoMsg.getString(99));
        onlineActivity.setField100(isoMsg.getString(100));
        onlineActivity.setField101(isoMsg.getString(101));
        onlineActivity.setField102(isoMsg.getString(102));
        onlineActivity.setField103(isoMsg.getString(103));
        onlineActivity.setField104(isoMsg.getString(104));
        onlineActivity.setField105(isoMsg.getString(105));
        onlineActivity.setField106(isoMsg.getString(106));
        onlineActivity.setField107(isoMsg.getString(107));
        onlineActivity.setField108(isoMsg.getString(108));
        onlineActivity.setField109(isoMsg.getString(109));
        onlineActivity.setField110(isoMsg.getString(110));
        onlineActivity.setField111(isoMsg.getString(111));
        onlineActivity.setField112(isoMsg.getString(112));
        onlineActivity.setField113(isoMsg.getString(113));
        onlineActivity.setField114(isoMsg.getString(114));
        onlineActivity.setField115(isoMsg.getString(115));
        onlineActivity.setField116(isoMsg.getString(116));
        onlineActivity.setField117(isoMsg.getString(117));
        onlineActivity.setField118(isoMsg.getString(118));
        onlineActivity.setField119(isoMsg.getString(119));
        onlineActivity.setField120(isoMsg.getString(120));
        onlineActivity.setField121(isoMsg.getString(121));
        onlineActivity.setField122(isoMsg.getString(122));
        onlineActivity.setField123(isoMsg.getString(123));
        onlineActivity.setField124(isoMsg.getString(124));
        onlineActivity.setField125(isoMsg.getString(125));
        onlineActivity.setField126(isoMsg.getString(126));
        onlineActivity.setField127(isoMsg.getString(127));
        onlineActivity.setField128(isoMsg.getString(128));

        onlineActivityRepository.save(onlineActivity);
        return true;

    }


    @Override
    public String SplitTLVData1(String msg1) {
      //  LoginWrapper loginWrapper = new LoginWrapper();
       // ChangePasswordWrapper changePasswordWrapper = new ChangePasswordWrapper();
        String response = null;
        StringBuilder rese = new StringBuilder();
        String tag;
        String len;
        String value;
        String value1 = null;
        String value2 = null;
        String value3 = null;
        String value4 = null;
        String value5 = null;
        String value6 = null;
        while (msg1.length() > 0) {
            tag = msg1.substring(0, 3);
            len = msg1.substring(3, 6);
            int valueLen = Integer.parseInt(len);
            value = msg1.substring(6, valueLen + 6);
            int totalLen = 6 + valueLen;
            switch (Integer.parseInt(tag)) {
                case 20:
                    value1 = value;
                    break;
                case 21:
                    value2 = value;
                    break;
                case 22:
                    value3 = value;
                    break;
                case 23:
                    value3 = value;
                    break;
                case 24:
                    value3 = value;
                    break;
                case 25:
                    value3 = value;
                    break;
                case 26:
                    value3 = value;
                    break;
                case 27:
                    value3 = value;
                    break;
                case 30:
                    value4 = value;
                    break;
                case 31:
                    value5 = value;
                    break;
                case 32:
                    value6 = value;
                    break;
                default:
                    break;
            }
            String output = msg1.substring(0, totalLen);
            msg1 = msg1.replace(output, "");
        }

        return response;
    }

    //ufs Log in


    //ufs change password

}
