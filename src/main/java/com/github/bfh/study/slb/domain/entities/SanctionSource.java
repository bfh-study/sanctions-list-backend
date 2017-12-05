package com.github.bfh.study.slb.domain.entities;

import io.crnk.jpa.annotations.JpaResource;
import javax.persistence.Entity;
import org.bitbucket.samsamann.rest.base.entities.BaseEntity;

/**
 * Represent an import source of sanctions lists (EU, SECO).
 *
 * @author Samuel Ackermann
 */
@Entity
@JpaResource(type = "source")
public class SanctionSource extends BaseEntity {

    private SourceName name;

    public SourceName getName() {
        return name;
    }

    public enum SourceName {
        EU,
        SECO
    }
}
