package com.github.bfh.study.slb.domain.entities;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import org.bitbucket.samsamann.rest.base.entities.BaseEntity;

@Entity
public class SanctionEntity extends BaseEntity {

    @OneToMany
    private List<EntityInfo> entityInfoList;

    public SanctionEntity() {
        entityInfoList = new ArrayList<>();
    }

    public List<EntityInfo> getEntityInfoList() {
        return entityInfoList;
    }

    public void addEntityInfo(EntityInfo entityInfo) {
        this.entityInfoList.add(entityInfo);
    }
}
