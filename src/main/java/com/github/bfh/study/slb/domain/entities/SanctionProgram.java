package com.github.bfh.study.slb.domain.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

/**
 * Sanction program.
 *
 * @author Samuel Ackermann
 */
@Entity
public class SanctionProgram extends SanctionBaseEntity {

    @Transient
    private String sanctionIdentifier;

    private String origin;

    private LocalDate date;

    //@OneToMany(cascade = CascadeType.ALL)
    //@JoinTable(name = "")
    //private Set<TranslatedText> shortDescription;

    //@OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
    //@Transient
    //private List<TranslatedText> longDescription;

    @OneToMany(cascade = CascadeType.ALL)
    private List<SanctionEntity> entities;

    /**
     * default constructor.
     */
    public SanctionProgram() {
        this(-1, "");
    }

    /**
     * constructor.
     *
     * @param sourceEntityId internal source identifier
     * @param sanctionIdentifier identifier of a sanction program. Is an internal number.
     */
    public SanctionProgram(int sourceEntityId, String sanctionIdentifier) {
        super(sourceEntityId);
        this.sanctionIdentifier = sanctionIdentifier;
        //shortDescription = new HashSet<>();
        //longDescription = new ArrayList<>();
        entities = new ArrayList<>();
    }

    public String getSanctionIdentifier() {
        return sanctionIdentifier;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void addEntity(SanctionEntity entity) {
        entities.add(entity);
    }

    /*public void addShortDescription(TranslatedText description) {
        shortDescription.add(description);
    }*/

    /*public void addLongDescription(TranslatedText description) {
        longDescription.add(description);
    }*/

    public List<SanctionEntity> getEntities() {
        return entities;
    }
}
