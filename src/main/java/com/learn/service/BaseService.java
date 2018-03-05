package com.learn.service;

import java.io.Serializable;
import java.util.Set;

public interface BaseService<T, ID extends Serializable> {

    String[] IGNORE_PROPERTIES = new String[]{"id", "createdDate", "createdBy", "lastModifiedDate", "lastModifiedBy"};

    T find(ID id);

    T save(T entity) throws Exception;

    String toJson(T item) throws Exception;

    <E extends Enum> String toJson(T item, Set<E> permissions);
}
