package com.nelson.rokel.serviceInterface;

import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;

/*
 * @Aurthor:Nelson
 */
public interface UFSInterface {
    void splitPosIrisDataAtField112AndSave(String posIrisData);

    boolean saveFailedTransactions(ISOMsg isoMsg) throws ISOException;

    boolean saveToOnlineActivity(ISOMsg isoMsg) throws ISOException;

    String SplitTLVData1(String msg1);
}
