package com.github.bfh.study.slb.provider.seco.entities;

import java.time.LocalDate;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

/**
 * Represents a birthday date of a person.
 *
 * @author Samuel Ackermann
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Birthday extends  SSIDType {

    @XmlAttribute(required = true, name = "day")
    private int day;

    @XmlAttribute(required = true, name = "month")
    private int month;

    @XmlAttribute(required = true, name = "year")
    private int year;

    /**
     * get date of birth.
     *
     * @return date
     */
    public LocalDate getBirthday() {
        if (day == 0 || month == 0 || year == 0) {
            return null;
        }

        return LocalDate.of(year, month, day);
    }
}
