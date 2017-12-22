package com.github.bfh.study.slb.domain.entities;

import org.bitbucket.samsamann.rest.base.entities.BaseEntity;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;

import javax.persistence.Entity;

/**
 * Represents information about citizen.
 *
 * @author Samuel Ackermann
 */
@Entity
public class CitizenInfo extends BaseEntity {

    @Field(index = Index.YES, analyze = Analyze.YES)
    private String country;

    /**
     * default constructor (for persisting purpose).
     */
    CitizenInfo() {
    }

    /**
     * normal constructor.
     *
     * @param country citizenship
     */
    public CitizenInfo(String country) {
        this.country = country;
    }

    public String getCountry() {
        return country;
    }
}
