package com.github.bfh.study.slb.provider.seco.entities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

/**
 * Created by samuel on 08.11.17.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class SSIDType {

    @XmlAttribute(required = true)
    private int ssid;
}
