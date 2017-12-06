package com.github.bfh.study.slb.provider.eu.entities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(XmlAccessType.FIELD)
public abstract class SubEntry extends BaseEntry {

    @XmlAttribute(name = "Entity_id", required = true)
    private int entityId;

    public int getEntityId() {
        return entityId;
    }
}
