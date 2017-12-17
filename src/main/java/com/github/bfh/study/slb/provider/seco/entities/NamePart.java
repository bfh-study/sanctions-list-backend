package com.github.bfh.study.slb.provider.seco.entities;

import com.github.bfh.study.slb.provider.entities.SimpleType;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

/**
 * Represents a part of a name like first or last name.
 *
 * @author Samuel Ackermann
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class NamePart {

    @XmlAttribute(required = true, name = "name-part-type")
    private String namePartType;

    @XmlElement(name = "value")
    private SimpleType value;

    public String getValue() {
        return value.getValue();
    }

    public String getNamePartType() {
        return namePartType;
    }

    /**
     * list of name part types.
     */
    @XmlType(name = "name-part-type")
    @XmlEnum
    public enum NamePartTypes {
        GIVEN_NAME("given-name"),
        FAMILY_NAME("family-name"),
        FATHER_NAME("father-name"),
        WHOLE_NAME("whole-name"),
        TITLE("title");


        private final String type;

        NamePartTypes(final String type) {
            this.type = type;
        }

        @Override
        public String toString() {
            return type;
        }
    }
}
