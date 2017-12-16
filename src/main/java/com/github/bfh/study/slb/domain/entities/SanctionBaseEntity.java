package com.github.bfh.study.slb.domain.entities;

import org.bitbucket.samsamann.rest.base.entities.BaseEntity;

import javax.persistence.MappedSuperclass;

/**
 * base class for sanction programs and entities.
 *
 * @author Samuel Ackermann
 */
@MappedSuperclass
public abstract class SanctionBaseEntity extends BaseEntity {

    private int sourceEntityId;

    public SanctionBaseEntity(int sourceEntityId) {
        this.sourceEntityId = sourceEntityId;
    }

    public int getSourceEntityId() {
        return sourceEntityId;
    }
}
