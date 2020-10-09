package com.nelson.rokel.packager;

import org.jpos.iso.*;

public class FlexCubePackager extends ISOBasePackager {

    private static final boolean pad = false;

    public FlexCubePackager() {
        super();
        ISOFieldPackager[] fld = {
                /*000*/ new IFB_NUMERIC(4, "Message Type Indicator", pad),
                /*001*/ new IFB_BITMAP(16, "Bitmap"),
                /*002*/ new IFB_LLNUM(19, "Primary Account number", pad),
                /*003*/ new IFB_NUMERIC(6, "Processing Code", pad),
                /*004*/ new IFB_NUMERIC(12, "Amount, Transaction", pad),
                /*005*/ new IFB_NUMERIC(12, "Amount, Reconciliation", pad),
                /*006*/ new IFB_NUMERIC(12, "Amount, Cardholder billing", pad),
                /*007*/ new IFB_NUMERIC(10, "Date and time, transmission", pad),
                /*008*/ new IFB_NUMERIC(20, "Customer category", pad),  // NOT IN FLEX
                /*009*/ new IFB_LLLCHAR(999, "PosIris data"),
                /*010*/ new IFB_NUMERIC(99, "Conversion rate, Cardholder billing", pad),
                /*011*/ new IFB_NUMERIC(6, "Systems trace audit number", pad),
                /*012*/ new IFB_NUMERIC(6, "Date and time, Local transaction", pad),
                /*013*/ new IFB_NUMERIC(4, "Date, Effective", pad),
                /*014*/ new IFB_NUMERIC(4, "Date, Expiration", pad),//MMDD
                /*015*/ new IFB_NUMERIC(6, "Date, Settlement", pad),
                /*016*/ new IFB_NUMERIC(4, "Date, Conversion", pad),
                /*017*/ new IFB_NUMERIC(4, "Date, Capture", pad),
                /*018*/ new IFB_NUMERIC(4, "Merchant type", pad),
                /*019*/ new IFB_NUMERIC(3, "Country code, Acquiring institution", pad),//==not in flex
                /*020*/ new IFB_NUMERIC(3, "Country code, Primary account number", pad),//===not in flex
                /*021*/ new IFB_NUMERIC(3, "Country code, Forwarding institution", pad),//===not in flex
                /*022*/ new IFB_NUMERIC(15, "Transaction type", pad),//===not in flex
                /*023*/ new IFB_NUMERIC(3, "Card sequence number", pad),//===not in flex
                /*024*/ new IFB_NUMERIC(3, "Function code", pad),//=====not in flex
                /*025*/ new IFB_NUMERIC(2, "Message reason code", pad),//====not in flex
                /*026*/ new IFB_NUMERIC(4, "Card acceptor business code", pad),//====not in flex
                /*027*/ new IFB_NUMERIC(1, "Approval code length", pad),//=======not in flex
                /*028*/ new IFB_NUMERIC(9, "Date, Reconciliation", pad),
                /*029*/ new IFB_NUMERIC(3, "Reconciliation indicator", pad),
                /*030*/ new IFB_NUMERIC(24, "Amounts, original", pad),
                /*031*/ new IFB_LLCHAR(99, "Acquirer reference data"),
                /*032*/ new IFB_LLNUM(11, "Acquirer institution ident code", pad),
                /*033*/ new IFB_LLNUM(11, "Forwarding institution ident code", pad),
                /*034*/ new IFB_LLCHAR(28, "Primary account number, extended"),
                /*035*/ new IFB_LLNUM(40, "Track 2 data", pad),
                /*036*/ new IFB_LLLCHAR(104, "Track 3 data"),//=======not in flex
                /*037*/ new IF_CHAR(12, "Retrieval reference number"),
                /*038*/ new IF_CHAR(99, "Customer Name"),
                /*039*/ new IF_CHAR(2, "Response / action code"),
                /*040*/ new IFB_NUMERIC(999, "Transaction data for STC transactions", pad),//====not in flex
                /*041*/ new IF_CHAR(8, "Card acceptor terminal identification"),
                /*042*/ new IF_CHAR(15, "Card acceptor identification code"),
                /*043*/ new IFB_LLCHAR(40, "Card acceptor name/location"),//was 99
                /*044*/ new IFB_LLCHAR(99, "Additional response data"),//==not in flex
                /*045*/ new IFB_LLCHAR(76, "Track 1 data"),//===not in flex
                /*046*/ new IFB_LLLCHAR(204, "Amounts, Fees"),//===not in flex
                /*047*/ new IFB_LLLCHAR(999, "Additional data - national"),//===not in flex====LOG IN DATA
                /*048*/ new IFB_LLLCHAR(999, "Additional data - private"),
                /*049*/ new IF_CHAR(3, "Currency code, Transaction"),
                /*050*/ new IF_CHAR(3, "Currency code, Reconciliation"),
                /*051*/ new IF_CHAR(3, "Currency code, Cardholder billing"),// was IF_CHAR
                /*052*/ new IFB_BINARY(8, "Personal identification number (PIN) data"),//===not in flex
                /*053*/ new IFB_LLBINARY(48, "Security related control information"),//=====not in flex
                /*054*/ new IFB_LLLCHAR(120, "Amounts, additional"),
                /*055*/ new IFB_LLLBINARY(999, "IC card system related data"),//=====not in flex
                /*056*/ new IFB_LLNUM(35, "Original data elements", pad),//====not in flex
                /*057*/ new IFB_NUMERIC(3, "Authorization life cycle code", pad),//=====not in flex
                /*058*/ new IFB_LLNUM(11, "Authorizing agent institution Id Code", pad),//====not in flex
                /*059*/ new IFB_LLLCHAR(999, "Transport data"),//=====not in flex
                /*060*/ new IFB_LLLCHAR(999, "Reserved for national use"),
                /*061*/ new IFB_LLLCHAR(999, "Reserved for national use"),//====not in flex
                /*062*/ new IFB_LLLBINARY(999, "Reserved for private use"),//====not in flex
                /*063*/ new IFB_LLLCHAR(999, "Reserved for private use"),//====not in flex
                /*064*/ new IFB_BINARY(8, "Message authentication code field"),
                /*065*/ new IFB_BINARY(8, "Reserved for ISO use"),
                /*066*/ new IFB_LLLCHAR(204, "Amounts, original fees"),
                /*067*/ new IFB_NUMERIC(2, "Extended payment data", pad),
                /*068*/ new IFB_NUMERIC(3, "Country code, receiving institution", pad),
                /*069*/ new IFB_NUMERIC(3, "Country code, settlement institution", pad),
                /*070*/ new IFB_NUMERIC(3, "Country code, authorizing agent Inst.", pad),
                /*071*/ new IFB_NUMERIC(8, "Message number", pad),
                /*072*/ new IFB_LLLCHAR(999, "Data record"),
                /*073*/ new IFB_NUMERIC(6, "Date, action", pad),
                /*074*/ new IFB_NUMERIC(10, "Credits, number", pad),
                /*075*/ new IFB_NUMERIC(10, "Credits, reversal number", pad),
                /*076*/ new IFB_NUMERIC(10, "Debits, number", pad),
                /*077*/ new IFB_NUMERIC(10, "Debits, reversal number", pad),
                /*078*/ new IFB_NUMERIC(10, "Transfer, number", pad),
                /*079*/ new IFB_NUMERIC(10, "Transfer, reversal number", pad),
                /*080*/ new IFB_NUMERIC(10, "Inquiries, number", pad),
                /*081*/ new IFB_NUMERIC(10, "Authorizations, number", pad),
                /*082*/ new IFB_NUMERIC(10, "Inquiries, reversal number", pad),
                /*083*/ new IFB_NUMERIC(10, "Payments, number", pad),
                /*084*/ new IFB_NUMERIC(10, "Payments, reversal number", pad),
                /*085*/ new IFB_NUMERIC(10, "Fee collections, number", pad),
                /*086*/ new IFB_NUMERIC(16, "Credits, amount", pad),
                /*087*/ new IFB_NUMERIC(16, "Credits, reversal amount", pad),
                /*088*/ new IFB_NUMERIC(16, "Debits, amount", pad),
                /*089*/ new IFB_NUMERIC(16, "Debits, reversal amount", pad),
                /*090*/ new IFB_NUMERIC(42, "Authorizations, reversal number", pad),
                /*091*/ new IFB_NUMERIC(3, "Country code, transaction Dest. Inst.", pad),
                /*092*/ new IFB_NUMERIC(3, "Country code, transaction Orig. Inst.", pad),
                /*093*/ new IFB_LLNUM(11, "Transaction Dest. Inst. Id code", pad),
                /*094*/ new IFB_LLNUM(11, "Transaction Orig. Inst. Id code", pad),
                /*095*/ new IFB_NUMERIC(42, "Card issuer reference data",pad),// was 99
                /*096*/ new IFB_LLLBINARY(999, "Key management data"),
                /*097*/ new IFB_AMOUNT(1 + 16, "Amount, Net reconciliation", pad),
                /*098*/ new IF_CHAR(25, "Payee"),
                /*099*/ new IFB_LLCHAR(11, "Settlement institution Id code"),
                /*100*/ new IFB_LLNUM(11, "Receiving institution Id code", pad),
                /*101*/ new IFB_LLCHAR(17, "File name"),
                /*102*/ new IFB_LLCHAR(28, "Account identification 1"),//debit account ==FROM
                /*103*/ new IFB_LLCHAR(28, "Account identification 2"),//Credit account ===TO
                /*104*/ new IFB_LLLCHAR(100, "Transaction description"),
                /*105*/ new IFB_NUMERIC(16, "Credits, Chargeback amount", pad),
                /*106*/ new IFB_NUMERIC(16, "Debits, Chargeback amount", pad),
                /*107*/ new IFB_NUMERIC(10, "Credits, Chargeback number", pad),
                /*108*/ new IFB_NUMERIC(10, "Debits, Chargeback number", pad),
                /*109*/ new IFB_LLCHAR(84, "Credits, Fee amounts"),
                /*110*/ new IFB_LLCHAR(84, "Debits, Fee amounts"),
                /*111*/ new IFB_LLLCHAR(999, "Reserved for ISO use"),// CARD OR CARDLESS TRANSACTION,,1==CARD
                /*112*/ new IFB_LLLCHAR(999, "Reserved for ISO use"),// POSIRIS DATA
                /*113*/ new IFB_LLLCHAR(999, "Reserved for ISO use"),
                /*114*/ new IFB_LLLCHAR(999, "Reserved for ISO use"),
                /*115*/ new IFB_LLLCHAR(999, "Reserved for ISO use"),
                /*116*/ new IFB_LLLCHAR(999, "Reserved for national use"),
                /*117*/ new IFB_LLLCHAR(999, "Reserved for national use"),
                /*118*/ new IFB_LLLCHAR(999, "Reserved for national use"),
                /*119*/ new IFB_LLLCHAR(999, "Reserved for national use"),
                /*120*/ new IFB_LLLCHAR(999, "Reserved for national use"),
                /*121*/ new IFB_LLLCHAR(999, "Reserved for national use"),
                /*122*/ new IFB_LLLCHAR(999, "Reserved for national use"),
                /*123*/ new IFB_LLLCHAR(999, "Reserved for private use"),
                /*124*/ new IFB_LLLCHAR(999, "Reserved for private use"),
                /*125*/ new IFB_LLLCHAR(999, "Reserved for private use"),
                /*126*/ new IFB_LLLCHAR(999, "Reserved for private use"),////=Preauthorization and chargeback
                /*127*/ new IFB_LLLCHAR(999, "Reserved for private use"),//MINISTATEMENT RESPONSE
                /*128*/ new IFB_BINARY(8, "Message authentication code field")
        };
        setFieldPackager(fld);
    }
}
