package com.github.bfh.study.slb.provider.eu.entities;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 * Represent a person, ship or organisation in the sanction list.
 *
 * @author Samuel Ackermann
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Entity extends BaseEntry {

    @XmlAttribute(name = "Type", required = true)
    private Character type;

    @XmlElement(name = "NAME")
    private List<EntryInfo> names;

    @XmlElement(name = "BIRTH")
    private List<BirthInfo> birthInfoList;

    public Character getType() {
        return type;
    }

    public List<EntryInfo> getNames() {
        return names;
    }

    public List<BirthInfo> getBirthInfoList() {
        return birthInfoList;
    }
}
