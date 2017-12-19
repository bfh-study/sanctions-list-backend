package com.github.bfh.study.slb.domain.entities;

import org.bitbucket.samsamann.rest.base.entities.BaseEntity;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Store;

import javax.persistence.Entity;


/**
 * Represents passport info.
 *
 * @author Samuel Ackermann
 */
@Entity
public class PassportInfo extends BaseEntity {

    @Field(store = Store.YES)
    private String number;

    @Field(store = Store.YES)
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
        this.number = number.substring(0, Math.min(number.length(), 255));
        this.country = country;
    }
}
