package com.learn.repository;

import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import java.io.Serializable;

public class CustomJpaRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements CustomJpaRepository<T, ID> {

    private EntityManager entityManager;

    @Override
    public EntityManager getEntityManager() { return entityManager; }

    @Override
    public void setEntityManager(EntityManager entityManager) { this.entityManager = entityManager; }

    // There are two constructors to choose from, either can be used.
    public CustomJpaRepositoryImpl(Class<T> domainClass, EntityManager entityManager) {
        super(domainClass, entityManager);

        // This is the recommended method for accessing inherited class dependencies.
        this.entityManager = entityManager;
    }

    @Override
    public <S extends T> S save(S entity) {
        entityManager.persist(entity);
        return entity;
    }
}
