package com.github.bfh.study.slb.domain.entities;

import io.crnk.jpa.annotations.JpaResource;
import org.bitbucket.samsamann.rest.base.entities.BaseEntity;

import javax.persistence.Entity;

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

    /**
     * List of possible sources.
     */
    public enum SourceName {
        EU,
        SECO
    }
}
