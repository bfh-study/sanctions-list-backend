package com.github.bfh.study.slb.provider.seco.entities;

import com.github.bfh.study.slb.provider.entities.SimpleType;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * Created by samuel on 08.11.17.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class SanctionProgram extends SSIDType {

    @XmlElement(required = true, name = "program-key")
    private List<LanguageType> keys;

    @XmlElement(required = true, name = "program-name")
    private List<LanguageType> names;

    @XmlElement(required = true, name = "sanctions-set")
    private List<SanctionSet> sets;

    @XmlElement(required = true)
    private SimpleType origin;

    /**
     * standard constructor
     */
    public SanctionProgram() {
        keys = new ArrayList<>();
        names = new ArrayList<>();
        sets = new ArrayList<>();
    }

    public List<LanguageType> getProgramKeys() {
        return keys;
    }

    public List<LanguageType> getProgramNames() {
        return names;
    }

    public List<SanctionSet> getSanctionSets() {
        return sets;
    }

    public SimpleType getOrigin() {
        return origin;
    }
}
