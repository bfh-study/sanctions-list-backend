package com.github.bfh.study.slb.provider.seco.entities;

import com.github.bfh.study.slb.provider.entities.SimpleType;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

/**
 * Represents a language(ISO).
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class LanguageType extends SimpleType {

    @XmlAttribute(required = true, name = "lang")
    private String language;

    public String getLanguage() {
        return language;
    }
}
