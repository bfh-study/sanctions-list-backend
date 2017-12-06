package com.github.bfh.study.slb.domain.entities;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class SanctionEntity {

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
