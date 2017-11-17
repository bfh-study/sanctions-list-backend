package com.github.bfh.study.slb.provider.seco.entities;

import com.github.bfh.study.slb.provider.entities.SimpleType;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

/**
 * Created by samuel on 08.11.17.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class LanguageType extends SimpleType {

    @XmlAttribute(required = true, name = "lang")
    private String language;
}
