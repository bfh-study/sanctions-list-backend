package com.github.bfh.study.slb.domain.entities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

/**
 * Represents a sanction entity. It holds all information about a person, organisation or entity.
 *
 * @author Samuel Ackermann
 */
@Entity
public class SanctionEntity extends SanctionBaseEntity {

    @Transient
    private String sanctionIdentifier;

    private String country;

    @OneToMany(cascade = CascadeType.ALL)
    private List<EntityInfo> entityInfoList;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<BirthInfo> birthInfoList;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<PassportInfo> passportInfoSet;

    @ManyToOne
    private SanctionProgram sanctionProgram;

    /**
     * constructor
     *
     * @param sourceId internal source identifier
     * @param sanctionIdentifier identifier of a sanction program. Is an internal number.
     * @param country contry of this entity.
     */
    public SanctionEntity(int sourceId, String sanctionIdentifier, String country) {
        super(sourceId);
        this.sanctionIdentifier = sanctionIdentifier;
        this.country = country;
        entityInfoList = new ArrayList<>();
        birthInfoList = new HashSet<>();
        passportInfoSet = new HashSet<>();
    }

    public String getSanctionIdentifier() {
        return sanctionIdentifier;
    }

    /**
     * Adds new information about this entity.
     *
     * @param entityInfo entity information
     */
    public void addEntityInfo(EntityInfo entityInfo) {
        entityInfoList.add(entityInfo);
    }

    /**
     * Adds birth information.
     *
     * @param birthInfo birth information
     */
    public void addBirthInfo(BirthInfo birthInfo) {
        birthInfoList.add(birthInfo);
    }

    /**
     * Adds passport information.
     *
     * @param passportInfo passport information
     */
    public void addPassportInfo(PassportInfo passportInfo) {
        passportInfoSet.add(passportInfo);
    }

    /**
     * Adds sanction program.
     *
     * @param sanctionProgram sanction program
     */
    public void setSanctionProgram(SanctionProgram sanctionProgram) {
        this.sanctionProgram = sanctionProgram;
    }
}
