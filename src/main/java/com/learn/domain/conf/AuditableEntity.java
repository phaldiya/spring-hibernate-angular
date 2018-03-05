package com.learn.domain.conf;

import com.learn.domain.Person;
import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@MappedSuperclass
public abstract class AuditableEntity<T extends Serializable> extends BaseObject<Id> {

    private static final long serialVersionUID = 1L;

    @CreatedDate
    @NotNull
    private DateTime createdDate = DateTime.now();

    @LastModifiedDate
    @NotNull
    private DateTime lastModifiedDate = DateTime.now();

    @CreatedBy
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    @NotNull
    private Person createdBy;

    @LastModifiedBy
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    @NotNull
    private Person lastModifiedBy;

    // Properties
    public abstract T getId();

    public DateTime getCreatedDate() { return createdDate; }

    public DateTime getLastModifiedDate() { return lastModifiedDate; }

    public Person getCreatedBy() { return createdBy; }

    public Person getLastModifiedBy() { return lastModifiedBy; }

    public void setCreatedDate(DateTime createdDate) { this.createdDate = createdDate; }

    public void setLastModifiedDate(DateTime lastModifiedDate) { this.lastModifiedDate = lastModifiedDate; }

    public void setCreatedBy(Person createdBy) { this.createdBy = createdBy; }

    public void setLastModifiedBy(Person lastModifiedBy) { this.lastModifiedBy = lastModifiedBy; }
}