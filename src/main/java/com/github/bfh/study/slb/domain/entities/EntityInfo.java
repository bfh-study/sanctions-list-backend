package com.github.bfh.study.slb.domain.entities;

import org.bitbucket.samsamann.rest.base.entities.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;

/**
 * information about a entity. Like names, function or title.
 *
 * @author Samuel Ackermann
 */
@Entity
public class EntityInfo extends BaseEntity {

    private int sourceId;

    private String firstName;

    private String lastName;

    private String wholeName;

    private Character gender;

    private String title;

    @Lob
    @Column(columnDefinition="TEXT")
    private String function;

    private String language;

    /**
     * default constructor (for persisting purpose).
     */
    EntityInfo(){
    }

    /**
     * constructor.
     *
     * @param sourceId identifier of the source(EU, SECO, ..)
     */
    public EntityInfo(int sourceId) {
        this.sourceId = sourceId;
    }

    public int getSourceId() {
        return sourceId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getWholeName() {
        return wholeName;
    }

    public void setWholeName(String wholeName) {
        this.wholeName = wholeName;
    }

    public Character getGender() {
        return gender;
    }

    public void setGender(Character gender) {
        this.gender = gender;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
