package com.github.bfh.study.slb.provider.seco.entities;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 * Represents general information about an entity in the swiss sanction list.
 *
 * @author Samuel Ackermann
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Target extends SSIDType {

    @XmlAttribute(required = true, name = "sanctions-set-id")
    private int sanctionsSetId;

    @XmlElement(required = true, name = "individual")
    private List<Individual> individuals;

    @XmlElement(required = true, name = "modification")
    private List<Modification> modifications;

    public Target() {
        individuals = new ArrayList<>();
        modifications = new ArrayList<>();
    }

    public int getSanctionsSetId() {
        return sanctionsSetId;
    }

    public List<Individual> getIndividuals() {
        return individuals;
    }

    public List<Modification> getModifications() {
        return modifications;
    }
}
