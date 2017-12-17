package com.github.bfh.study.slb.provider.seco.entities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * Represents a identity in the swiss sanction list.
 *
 * @author Samuel Ackermann
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Individual {

    @XmlElement(name = "identity")
    private Identity identity;

    public Identity getIdentity() {
        return identity;
    }
}
