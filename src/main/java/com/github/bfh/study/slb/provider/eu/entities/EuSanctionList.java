package com.github.bfh.study.slb.provider.eu.entities;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Represent the root element of the EU sanction list.
 *
 * @author Samuel Ackermann
 */
@XmlRootElement(name = "WHOLE")
@XmlAccessorType(XmlAccessType.FIELD)
public class EuSanctionList {

    @XmlElement(name = "ENTITY")
    private List<Entity> entities;

    public List<Entity> getEntities() {
        return entities;
    }
}
