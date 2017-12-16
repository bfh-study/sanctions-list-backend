package com.github.bfh.study.slb.domain.entities;

import org.bitbucket.samsamann.rest.base.entities.BaseEntity;

import java.time.LocalDate;
import javax.persistence.Entity;

/**
 * Represents information about the birth.
 *
 * @author Samuel Ackermann
 */
@Entity
public class BirthInfo extends BaseEntity {

    private LocalDate date;

    private String place;

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
