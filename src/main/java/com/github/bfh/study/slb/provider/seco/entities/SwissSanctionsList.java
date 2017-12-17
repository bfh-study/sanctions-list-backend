package com.github.bfh.study.slb.provider.seco.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * Root node of the swiss sanctions xml file.
 *
 * @author Samuel Ackermann
 */
@XmlRootElement(name = "swiss-sanctions-list")
@XmlAccessorType(XmlAccessType.FIELD)
public class SwissSanctionsList {

    @XmlAttribute(required = true)
    private XMLGregorianCalendar date;

    @XmlAttribute(name = "list-type", required = true)
    private String listType;

    @XmlElement(name = "sanctions-program")
    private List<SanctionProgram> sanctionPrograms;

    @XmlElement(name = "target")
    private List<Target> targets;

    public SwissSanctionsList() {
        sanctionPrograms = new ArrayList<>();
        targets = new ArrayList<>();
    }

    public XMLGregorianCalendar getDate() {
        return date;
    }

    public String getListType() {
        return listType;
    }

    public List<SanctionProgram> getSanctionPrograms() {
        return sanctionPrograms;
    }

    public List<Target> getTargets() {
        return targets;
    }
}
