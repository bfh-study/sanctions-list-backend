package com.github.bfh.study.slb.domain.entities;

import org.bitbucket.samsamann.rest.base.entities.BaseEntity;

import javax.persistence.Entity;

/**
 * Represents passport info.
 *
 * @author Samuel Ackermann
 */
@Entity
public class PassportInfo extends BaseEntity {

    private String number;

    private String country;

    /**
     * default constructor (for persisting purpose).
     */
    PassportInfo() {
    }

    /**
     * constructor.
     *
     * @param number passport number
     * @param country country of issuance
     */
    public PassportInfo(String number, String country) {
        this.number = number;
        this.country = country;
    }
}
