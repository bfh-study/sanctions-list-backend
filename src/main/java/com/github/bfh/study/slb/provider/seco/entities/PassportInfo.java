package com.github.bfh.study.slb.provider.seco.entities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * Represents necessary passport info.
 *
 * @author Samuel Ackermann
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class PassportInfo extends  SSIDType {

    @XmlElement(name = "number")
    private String number;

    @XmlElement(name = "issuer")
    private String issuer;

    public String getNumber() {
        return number;
    }

    public String getIssuer() {
        return issuer;
    }
}
