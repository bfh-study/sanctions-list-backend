package com.github.bfh.study.slb.domain.entities;

import org.bitbucket.samsamann.rest.base.entities.BaseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class SanctionProgram extends BaseEntity {

    private int internalId;

    private LocalDate date;

    @OneToOne
    private SanctionListImport listImport;

    @OneToMany
    private List<SanctionEntity> entities;

    public SanctionProgram() {
        entities = new ArrayList<>();
    }

    public int getInternalId() {
        return internalId;
    }

    public void setInternalId(int internalId) {
        this.internalId = internalId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public SanctionListImport getListImport() {
        return listImport;
    }

    public void setListImport(SanctionListImport listImport) {
        this.listImport = listImport;
    }

    public void addEntity(SanctionEntity entity) {
        entities.add(entity);
    }

    public List<SanctionEntity> getEntities() {
        return entities;
    }
}
