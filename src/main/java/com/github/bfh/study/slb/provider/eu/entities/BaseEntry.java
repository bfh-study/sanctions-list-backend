package com.github.bfh.study.slb.provider.eu.entities;

import java.net.URI;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.datatype.XMLGregorianCalendar;

public abstract class BaseEntry {

    @XmlAttribute(name = "Id", required = true)
    private int id;

    @XmlAttribute(name = "legal_basis")
    private String legalBasis;

    @XmlAttribute(name = "reg_date")
    private XMLGregorianCalendar regDate;

    @XmlAttribute(name = "pdf_link")
    private URI pdfLink;

    @XmlAttribute(name = "programme")
    private String programme;

    public int getId() {
        return id;
    }

    public String getLegalBasis() {
        return legalBasis;
    }

    public XMLGregorianCalendar getRegDate() {
        return regDate;
    }

    public URI getPdfLink() {
        return pdfLink;
    }

    public String getProgramme() {
        return programme;
    }
}
