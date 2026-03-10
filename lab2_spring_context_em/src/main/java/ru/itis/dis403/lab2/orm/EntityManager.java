package ru.itis.dis403.lab2.orm;

import java.util.List;

public interface EntityManager {
    <T> T save(T entity); // insert, update
    void remove(Object entity); // delete
    <T> T find(Class<T> entityType, Object key); // select where .......
    <T> List<T> findAll(Class<T> entityType); // select *
}