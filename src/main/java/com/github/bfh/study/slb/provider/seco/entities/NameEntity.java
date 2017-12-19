package com.github.bfh.study.slb.provider.seco.entities;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * Represents all parts of a name.
 *
 * @author Samuel Ackermann
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class NameEntity extends SSIDType {

    @XmlElement(required = true, name = "name-part")
    private List<NamePart> nameParts;

    public NameEntity() {
        nameParts = new ArrayList<>();
    }

    public List<NamePart> getNameParts() {
        return nameParts;
    }
}
