package com.github.bfh.study.slb.domain.entities;

import org.bitbucket.samsamann.rest.base.entities.BaseEntity;
import org.hibernate.search.annotations.DateBridge;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Resolution;
import org.hibernate.search.annotations.Store;

import java.time.LocalDate;
import javax.persistence.Entity;

/**
 * Represents information about the birth.
 *
 * @author Samuel Ackermann
 */
@Entity
public class BirthInfo extends BaseEntity {

    @Field(store = Store.YES)
    @DateBridge(resolution = Resolution.YEAR)
    private LocalDate date;

    @Field(store = Store.YES)
    private String place;

    @Field(store = Store.YES)
    private String country;

    /**
     * default constructor (for persisting purpose).
     */
    BirthInfo() {
    }

    /**
     * constructor.
     *
     * @param date birthday
     * @param place birthplace
     * @param country country of birth
     */
    public BirthInfo(LocalDate date, String place, String country) {
        this.date = date;
        this.place = place;
        this.country = country;
    }
}
