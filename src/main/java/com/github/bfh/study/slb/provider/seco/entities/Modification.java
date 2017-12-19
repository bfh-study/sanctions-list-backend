package com.github.bfh.study.slb.provider.seco.entities;

import java.time.LocalDate;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * Represents modification of a record in the swiss sanction list.
 *
 * @author Samuel Ackermann
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Modification {

    @XmlAttribute(required = true, name = "modification-type")
    private ModificationType modificationType;

    @XmlAttribute(required = true, name = "effective-date")
    private XMLGregorianCalendar effectiveDate;

    @XmlEnum
    public enum ModificationType {
        @XmlEnumValue("listed") LISTED,
        @XmlEnumValue("amended") AMENDED,
        @XmlEnumValue("de-listed") DE_LISTED;
    }

    public ModificationType getModificationType() {
        return modificationType;
    }

    /**
     * get modification date.
     *
     * @return date
     */
    public LocalDate getEffectiveDate() {
        return LocalDate.of(
            effectiveDate.getYear(),
            effectiveDate.getMonth(),
            effectiveDate.getDay()
        );
    }
}
