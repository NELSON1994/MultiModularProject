package com.nelson.mainclass;

import com.nelson.rokel.packager.TransportPackager;
import org.jpos.iso.*;
import org.jpos.iso.channel.NACChannel;
import org.jpos.util.LogSource;
import org.jpos.util.Logger;
import org.jpos.util.SimpleLogListener;

import java.util.Date;

public class ISOTestClass {
    public static void main(String[] args) {

        try{
            byte[] TPDU = new byte[5];
            TPDU[0] = 60;
            TPDU[1] = 00;
            TPDU[2] = 00;
            TPDU[3] = 00;
            TPDU[4] = 00;
            String server;

            ISOPackager packager=new TransportPackager();//packager

             server = "127.0.0.1"; // localhost ADDRESS
           // server = "41.215.130.247"; //remote  public TRACOM ADDRESS
            // server = "172.16.0.6"; //SBM IP ADDRESS
//
            // ISOChannel channel = new NACChannel(server,  9622,  packager, null); // goto server.xml  remove header
            ISOChannel channel = new NACChannel(server,  9045,  packager,TPDU);

            Logger logger = new Logger();
            logger.addListener (new SimpleLogListener(System.out));
            System.out.println("~~~~~~~~~~~~~~START OF TRANSACTION~~~~~~~~~~~~");
            ((LogSource)channel).setLogger (logger, "~~~~CONNECTED CHANNEL~~~~~~");
            new ISOMsg();

            // ISOMsg message = echoTest();
            ISOMsg message = echoTestToFlex();
            // ISOMsg message = CashDepositToWay4();
            // ISOMsg message = CashWithdrawalToWay4();
            //ISOMsg message = BalanceInquiryToWay4();
            // ISOMsg message = FundTransferToFlex();
            // ISOMsg message = CashDepositToFlex();
            //  ISOMsg message = BalanceInqToFlex();


            message.setPackager(packager);
            channel.connect();

            channel.send(message);

            ISOMsg response=channel.receive();

            System.out.println("~~~~~~~~~~~~~END OF TRANSACTION~~~~~");


        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    private static ISOMsg echoTest() throws ISOException {
        String now = ISODate.formatDate(new Date(), "hhmmss");
        String fld7 = ISODate.formatDate(new Date(), "MMDDhhmmss");
        String fld71 = ISODate.formatDate(new Date(), "MMddhhmmss");
        System.out.println("********F 7*******"+fld7);
        System.out.println("********F 71*******"+fld71);
        System.out.println("********F 12*******"+now);
        ISOMsg isoMsg = new ISOMsg();
        isoMsg.setMTI("0800");
        isoMsg.set(3,"001100");
        isoMsg.set(11,"000001");
        isoMsg.set(70,"301");
        // isoMsg.set(112,"191277313221017301065283#sbm agency 1.0#15.08.16.4#89#1#-1126710492#28#18#22838680");
        return isoMsg;
    }
    private static ISOMsg echoTestToFlex() throws ISOException{
        String now = ISODate.formatDate(new Date(), "hhmmss");
        String fld7=ISODate.formatDate(new Date(),"MMDDhhmmss");
        System.out.println("FLD 7============"+fld7);
        System.out.println("FLD 12============"+now);
        ISOMsg isoMsg = new ISOMsg();
        isoMsg.setMTI("0800");
        isoMsg.set(3,"900000");
        isoMsg.set(7,"9000006734");
        isoMsg.set(12,now);
        isoMsg.set(70,"301");
        //isoMsg.set(112,"191277313221017301065283#sbm agency 1.0#15.08.16.4#89#1#-1126710492#28#18#22838680");
        return isoMsg;
    }
    private static ISOMsg BalanceInqToFlex() throws ISOException{
        ISOMsg isoMsg = new ISOMsg();
        isoMsg.setMTI("0200");
        isoMsg.set(2,"6273489999999999");
        isoMsg.set(3,"310000");
        isoMsg.set(4,"000000000000");
        isoMsg.set(7,"0810150815");
        isoMsg.set(11,"201508");
        isoMsg.set(12,"082104");
        isoMsg.set(13,"0810");
        isoMsg.set(18,"6010");
        isoMsg.set(22,"901");
        isoMsg.set(25,"00");
        isoMsg.set(26,"12");
        isoMsg.set(28,"C00000000");
        isoMsg.set(32,"627348");
        isoMsg.set(33,"627348");
        isoMsg.set(35,"6273489999999999=200750100000");
        isoMsg.set(37,"000000081756");
        isoMsg.set(41,"22838680");
        isoMsg.set(42,"627348000000006");
        isoMsg.set(43,"Carltech Printing Services-Kenyatta HiKE");
        isoMsg.set(49,"404");
        isoMsg.set(60,"27          ");
        isoMsg.set(102,"0102064315005");
        isoMsg.set(112,"201277313221017301065283#sbm agency 1.1#15.08.16.4#89#1#-1126710492#28#18#22838681");
        return isoMsg;
    }


    private static ISOMsg CashDepositToWay4() throws ISOException {
        ISOMsg isoMsg = new ISOMsg();
        isoMsg.setMTI("0200");
        isoMsg.set(2, "424108******6385");
        isoMsg.set(3, "280000");
        isoMsg.set(4, "000000**0000");
        isoMsg.set(7, "0727201345");
        isoMsg.set(11, "564513");
        isoMsg.set(12, "152317");
        isoMsg.set(13, "0727");
        isoMsg.set(14, "2102");
        isoMsg.set(18, "6010");
        isoMsg.set(22, "901");
        isoMsg.set(25, "00");
        isoMsg.set(26, "12");
        isoMsg.set(28, "C00000000");
        isoMsg.set(32, "627348");
        isoMsg.set(33, "627348");
        isoMsg.set(35, "*************************************");
        isoMsg.set(37, "020901**4513");
        isoMsg.set(41, "22723320");
        isoMsg.set(42, "627348*****0006");
        isoMsg.set(43, "Stanle******************************KiKE");
        isoMsg.set(49, "404");
        // isoMsg.set(52, "****************");
        isoMsg.set(53, "980101******0000");
        isoMsg.set(112,"201277313221017301065283#sbm agency 1.1#15.08.16.4#89#1#-1126710492#28#18#22838681");


        return isoMsg;
    }

    private static ISOMsg BalanceInquiryToWay4() throws ISOException {
        ISOMsg isoMsg = new ISOMsg();
        isoMsg.setMTI("0100");
        isoMsg.set(2, "424108******8950");
        isoMsg.set(3, "300000");
        isoMsg.set(4, "000000**0000");
        isoMsg.set(7, "0726201022");
        isoMsg.set(11, "392210");
        isoMsg.set(12, "114226");
        isoMsg.set(13, "0726");
        isoMsg.set(14, "2402");
        isoMsg.set(18, "6010");
        isoMsg.set(22, "901");
        isoMsg.set(25, "00");
        isoMsg.set(26, "12");
        isoMsg.set(28, "C00000000");
        isoMsg.set(32, "627348");
        isoMsg.set(33, "627348");
        isoMsg.set(35, "*************************************");
        isoMsg.set(37, "020801**2210");
        isoMsg.set(41, "22837081");
        isoMsg.set(42, "627348*****0006");
        isoMsg.set(43, "Mabini******************************  KE");
        isoMsg.set(49, "404");
        // isoMsg.set(52, "****************");
        isoMsg.set(53, "980101******0000");

        return isoMsg;
    }


    private static ISOMsg CashWithdrawalToWay4() throws ISOException {
        ISOMsg isoMsg = new ISOMsg();
        isoMsg.setMTI("0200");
        isoMsg.set(2, "629110******3991");
        isoMsg.set(3, "010000");
        isoMsg.set(4, "000000**5000");
        isoMsg.set(7, "0727201037");
        isoMsg.set(11, "113710");
        isoMsg.set(12, "123727");
        isoMsg.set(13, "0727");
        isoMsg.set(14, "2407");
        isoMsg.set(18, "6010");
        isoMsg.set(22, "901");
        isoMsg.set(25, "00");
        isoMsg.set(26, "12");
        isoMsg.set(28, "C00000000");
        isoMsg.set(32, "627348");
        isoMsg.set(33, "627348");
        isoMsg.set(35, "*************************************");
        isoMsg.set(37, "020901**3710");
        isoMsg.set(41, "22844871");
        isoMsg.set(42, "627348*****0006");
        isoMsg.set(43, "Betty ******************************  KE");
        isoMsg.set(49, "404");
        // isoMsg.set(52, "****************");
        isoMsg.set(53, "980101******0000");

        return isoMsg;
    }

    private static ISOMsg FundTransferToFlex() throws ISOException {
        ISOMsg isoMsg = new ISOMsg();
        isoMsg.setMTI("0200");
        isoMsg.set(2, "6273489999999999");
        isoMsg.set(3, "420000");
        isoMsg.set(4, "000005800000");
        isoMsg.set(7, "0810150815");
        isoMsg.set(11, "201508");
        isoMsg.set(12, "082104");
        isoMsg.set(13, "0810");
        isoMsg.set(18, "6010");
        isoMsg.set(22, "901");
        isoMsg.set(25, "00");
        isoMsg.set(26, "12");
        isoMsg.set(28, "C00000000");
        isoMsg.set(32, "627348");
        isoMsg.set(33, "627348");
        isoMsg.set(35, "6273489999999999=200750100000");
        isoMsg.set(37, "000000081756");
        isoMsg.set(41, "22838680");
        isoMsg.set(42, "627348000000006");
        isoMsg.set(43, "Carltech Printing Services-Kenyatta HiKE");
        isoMsg.set(49, "404");
        isoMsg.set(60, "27          ");
        isoMsg.set(102, "0102064315005"); //from account
        isoMsg.set(103, "0102064315005"); // to account
        isoMsg.set(112,"991277313221017301065283#sbm agency 1.5#15.08.16.4#89#1#-1126710492#28#18#22838680");

        return isoMsg;
    }

    private static ISOMsg CashDepositToFlex() throws ISOException {
        ISOMsg isoMsg = new ISOMsg();
        isoMsg.setMTI("0200");
        isoMsg.set(2, "6273489999999999");
        isoMsg.set(3, "210000");
        isoMsg.set(4, "000000850000");
        isoMsg.set(7, "0810150815");
        isoMsg.set(11, "201508");
        isoMsg.set(12, "082104");
        isoMsg.set(13, "0810");
        isoMsg.set(18, "6010");
        isoMsg.set(22, "901");
        isoMsg.set(25, "00");
        isoMsg.set(26, "12");
        isoMsg.set(28, "C00000000");
        isoMsg.set(32, "627348");
        isoMsg.set(33, "627348");
        isoMsg.set(35, "6273489999999999=200750100000");
        isoMsg.set(37, "000000081756");
        isoMsg.set(41, "22838680");
        isoMsg.set(42, "627348000000006");
        isoMsg.set(43, "Carltech Printing Services-Kenyatta HiKE");
        isoMsg.set(49, "404");
        isoMsg.set(60, "27          ");
        isoMsg.set(103, "0102064315005"); // to account
        isoMsg.set(112,"891277313221017301065283#sbm agency 1.6#15.08.16.4#89#1#-1126710492#28#18#22838680");

        return isoMsg;
    }

    private static ISOMsg CashWithdrawalToFlex() throws ISOException {
        ISOMsg isoMsg = new ISOMsg();
        isoMsg.setMTI("0200");
        isoMsg.set(2, "6273489999999999");
        isoMsg.set(3, "210000");
        isoMsg.set(4, "000000850000");
        isoMsg.set(7, "0810150815");
        isoMsg.set(11, "201508");
        isoMsg.set(12, "082104");
        isoMsg.set(13, "0810");
        isoMsg.set(18, "6010");
        isoMsg.set(22, "901");
        isoMsg.set(25, "00");
        isoMsg.set(26, "12");
        isoMsg.set(28, "C00000000");
        isoMsg.set(32, "627348");
        isoMsg.set(33, "627348");
        isoMsg.set(35, "6273489999999999=200750100000");
        isoMsg.set(37, "000000081756");
        isoMsg.set(41, "22838680");
        isoMsg.set(42, "627348000000006]");
        isoMsg.set(43, "Carltech Printing Services-Kenyatta HiKE]");
        isoMsg.set(49, "404");
        isoMsg.set(60, "27          ");
        isoMsg.set(103, "0102064315005"); // to account
        isoMsg.set(112,"891277313221017301065283#sbm agency 1.8#15.08.16.4#89#1#-1126710492#28#18#22838680");

        return isoMsg;
    }

}
