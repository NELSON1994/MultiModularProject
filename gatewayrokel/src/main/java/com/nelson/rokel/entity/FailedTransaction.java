package com.nelson.rokel.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

/*
@Author:Nelson
 */
@Data
@Entity
@Table(name = "FAILED_TRANSACTIONS")
public class FailedTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ONLINE_ACTIVITY_SEQ")
    @SequenceGenerator(sequenceName = "online_activity_seq", allocationSize = 1, name = "ONLINE_ACTIVITY_SEQ")
    @Column(name = "TRANSACTIONNUMBER")
    private BigDecimal transactionnumber;
    @Column(name = "FIELD000")
    private String field0;
    @Column(name = "FIELD001")
    private String field1;
    @Column(name = "FIELD002")
    private String field2;
    @Column(name = "FIELD003")
    private String field3;
    @Column(name = "FIELD004")
    private String field4;
    @Column(name = "FIELD005")
    private String field5;
    @Column(name = "FIELD006")
    private String field6;
    @Column(name = "FIELD007")
    private String field7;
    @Column(name = "FIELD008")
    private String field8;
    @Column(name = "FIELD009")
    private String field9;
    @Column(name = "FIELD010")
    private String field10;
    @Column(name = "FIELD011")
    private String field11;
    @Column(name = "FIELD012")
    private String field12;
    @Column(name = "FIELD013")
    private String field13;
    @Column(name = "FIELD014")
    private String field14;
    @Column(name = "FIELD015")
    private String field15;
    @Column(name = "FIELD016")
    private String field16;
    @Column(name = "FIELD017")
    private String field17;
    @Column(name = "FIELD018")
    private String field18;
    @Column(name = "FIELD019")
    private String field19;
    @Column(name = "FIELD020")
    private String field20;
    @Column(name = "FIELD021")
    private String field21;
    @Column(name = "FIELD022")
    private String field22;
    @Column(name = "FIELD023")
    private String field23;
    @Column(name = "FIELD024")
    private String field24;
    @Column(name = "FIELD025")
    private String field25;
    @Column(name = "FIELD026")
    private String field26;
    @Column(name = "FIELD027")
    private String field27;
    @Column(name = "FIELD028")
    private String field28;
    @Column(name = "FIELD029")
    private String field29;
    @Column(name = "FIELD030")
    private String field30;
    @Column(name = "FIELD031")
    private String field31;
    @Column(name = "FIELD032")
    private String field32;
    @Column(name = "FIELD033")
    private String field33;
    @Column(name = "FIELD034")
    private String field34;
    @Column(name = "FIELD035")
    private String field35;
    @Column(name = "FIELD036")
    private String field36;
    @Column(name = "FIELD037")
    private String field37;
    @Column(name = "FIELD038")
    private String field38;
    @Column(name = "FIELD039")
    private String field39;
    @Column(name = "FIELD040")
    private String field40;
    @Column(name = "FIELD041")
    private String field41;
    @Column(name = "FIELD042")
    private String field42;
    @Column(name = "FIELD043")
    private String field43;
    @Column(name = "FIELD044")
    private String field44;
    @Column(name = "FIELD045")
    private String field45;
    @Column(name = "FIELD046")
    private String field46;
    @Column(name = "FIELD047")
    private String field47;
    @Column(name = "FIELD048")
    private String field48;
    @Column(name = "FIELD049")
    private String field49;
    @Column(name = "FIELD050")
    private String field50;
    @Column(name = "FIELD051")
    private String field51;
    @Column(name = "FIELD052")
    private String field52;
    @Column(name = "FIELD053")
    private String field53;
    @Column(name = "FIELD054")
    private String field54;
    @Column(name = "FIELD055")
    private String field55;
    @Column(name = "FIELD056")
    private String field56;
    @Column(name = "FIELD057")
    private String field57;
    @Column(name = "FIELD058")
    private String field58;
    @Column(name = "FIELD059")
    private String field59;
    @Column(name = "FIELD060")
    private String field60;
    @Column(name = "FIELD061")
    private String field61;
    @Column(name = "FIELD062")
    private String field62;
    @Column(name = "FIELD063")
    private String field63;
    @Column(name = "FIELD064")
    private String field64;
    @Column(name = "FIELD065")
    private String field65;
    @Column(name = "FIELD066")
    private String field66;
    @Column(name = "FIELD067")
    private String field67;
    @Column(name = "FIELD068")
    private String field68;
    @Column(name = "FIELD069")
    private String field69;
    @Column(name = "FIELD070")
    private String field70;
    @Column(name = "FIELD071")
    private String field71;
    @Column(name = "FIELD072")
    private String field72;
    @Column(name = "FIELD073")
    private String field73;
    @Column(name = "FIELD074")
    private String field74;
    @Column(name = "FIELD075")
    private String field75;
    @Column(name = "FIELD076")
    private String field76;
    @Column(name = "FIELD077")
    private String field77;
    @Column(name = "FIELD08")
    private String field78;
    @Column(name = "FIELD079")
    private String field79;
    @Column(name = "FIELD080")
    private String field80;
    @Column(name = "FIELD081")
    private String field81;
    @Column(name = "FIELD082")
    private String field82;
    @Column(name = "FIELD083")
    private String field83;
    @Column(name = "FIELD084")
    private String field84;
    @Column(name = "FIELD085")
    private String field85;
    @Column(name = "FIELD086")
    private String field86;
    @Column(name = "FIELD087")
    private String field87;
    @Column(name = "FIELD088")
    private String field88;
    @Column(name = "FIELD089")
    private String field89;
    @Column(name = "FIELD090")
    private String field90;
    @Column(name = "FIELD091")
    private String field91;
    @Column(name = "FIELD092")
    private String field92;
    @Column(name = "FIELD093")
    private String field93;
    @Column(name = "FIELD094")
    private String field94;
    @Column(name = "FIELD095")
    private String field95;
    @Column(name = "FIELD096")
    private String field96;
    @Column(name = "FIELD097")
    private String field97;
    @Column(name = "FIELD098")
    private String field98;
    @Column(name = "FIELD099")
    private String field99;
    @Column(name = "FIELD100")
    private String field100;
    @Column(name = "FIELD101")
    private String field101;
    @Column(name = "FIELD102")
    private String field102;
    @Column(name = "FIELD103")
    private String field103;
    @Column(name = "FIELD104")
    private String field104;
    @Column(name = "FIELD105")
    private String field105;
    @Column(name = "FIELD106")
    private String field106;
    @Column(name = "FIELD107")
    private String field107;
    @Column(name = "FIELD108")
    private String field108;
    @Column(name = "FIELD109")
    private String field109;
    @Column(name = "FIELD110")
    private String field110;
    @Column(name = "FIELD111")
    private String field111;
    @Column(name = "FIELD112")
    private String field112;
    @Column(name = "FIELD113")
    private String field113;
    @Column(name = "FIELD114")
    private String field114;
    @Column(name = "FIELD115")
    private String field115;
    @Column(name = "FIELD116")
    private String field116;
    @Column(name = "FIELD117")
    private String field117;
    @Column(name = "FIELD118")
    private String field118;
    @Column(name = "FIELD119")
    private String field119;
    @Column(name = "FIELD120")
    private String field120;
    @Column(name = "FIELD121")
    private String field121;
    @Column(name = "FIELD122")
    private String field122;
    @Column(name = "FIELD123")
    private String field123;
    @Column(name = "FIELD124")
    private String field124;
    @Column(name = "FIELD125")
    private String field125;
    @Column(name = "FIELD126")
    private String field126;
    @Column(name = "FIELD127")
    private String field127;
    @Column(name = "FIELD128")
    private String field128;
}
