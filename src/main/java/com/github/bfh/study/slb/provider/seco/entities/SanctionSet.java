package com.github.bfh.study.slb.provider.seco.entities;

import com.github.bfh.study.slb.provider.entities.SimpleType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

/**
 * Created by samuel on 08.11.17.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class SanctionSet extends SimpleType {

    @XmlAttribute(required = true, name = "lang")
    private String language;

    @XmlAttribute(required = true)
    private int ssid;

    public String getLanguage() {
        return language;
    }

    public int getSsid() {
        return ssid;
    }
}
