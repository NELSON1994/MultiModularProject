package com.nelson.rokel.serviceInterface;

import com.nelson.rokel.wrappers.CheckConnectionWrapper;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;

/*
 * @Aurthor:Nelson
 */

public interface ConnectionInterface {

    CheckConnectionWrapper checkConnectionDetailsToWay4();

    CheckConnectionWrapper checkConnectionDetailsToFlex();

    ISOMsg sendISOMessageToWay4(CheckConnectionWrapper checkConnectionWrapper, ISOMsg receivedFromPos) throws ISOException;

    ISOMsg sendISOMessageToFlex(CheckConnectionWrapper checkConnectionWrapper, ISOMsg receivedFromPos) throws ISOException;
}
