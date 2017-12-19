package com.github.bfh.study.slb.provider.seco.entities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

/**
 * Represents a birthplace of a sanctioned person.
 *
 * @author Samuel Ackermann
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Birthplace extends SSIDType {

    @XmlAttribute(required = true, name = "place-id")
    private int placeId;

    public int getPlaceId() {
        return placeId;
    }
}
