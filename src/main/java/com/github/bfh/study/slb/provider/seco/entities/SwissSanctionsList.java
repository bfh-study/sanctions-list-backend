package com.github.bfh.study.slb.provider.seco.entities;

import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.ArrayList;
import java.util.List;

/**
 * Root node of the swiss sanctions xml file
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

    public SwissSanctionsList() {
        sanctionPrograms = new ArrayList<>();
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
}
