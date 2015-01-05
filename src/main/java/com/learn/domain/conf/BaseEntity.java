package com.learn.domain.conf;

import javax.persistence.Id;

public abstract class BaseEntity extends BaseObject<Id> {

    protected BaseEntity() { super(); }

}