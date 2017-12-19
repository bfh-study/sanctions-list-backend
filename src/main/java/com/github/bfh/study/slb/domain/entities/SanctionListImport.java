package com.github.bfh.study.slb.domain.entities;

import org.bitbucket.samsamann.rest.base.entities.BaseEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

/**
 * Represents a sanction import.
 *
 * @author Samuel Ackermann
 */
@Entity
public class SanctionListImport extends BaseEntity {

    private LocalDateTime uploadDateTime;

    private LocalDate sanctionListDate;

    private String type;

    private String status;

    @OneToOne
    private SanctionSource source;

    public LocalDateTime getUploadDateTime() {
        return uploadDateTime;
    }

    public void setUploadDateTime(LocalDateTime uploadDateTime) {
        this.uploadDateTime = uploadDateTime;
    }

    public LocalDate getSanctionListDate() {
        return sanctionListDate;
    }

    public void setSanctionListDate(LocalDate sanctionListDate) {
        this.sanctionListDate = sanctionListDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public SanctionSource getSource() {
        return source;
    }

    public void setSource(SanctionSource source) {
        this.source = source;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
