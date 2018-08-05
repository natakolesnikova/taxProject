package com.tax.dao;

import com.tax.exception.PersistException;

import java.io.Serializable;
import java.util.List;

/**
 * Унифицированный интерфейс управления объектами
 * @param <T> тип объекта персистенции
 * @param <PK> тип первичного ключа
 */

public interface GenericDAO<T, PK extends Serializable> {
    /** Создает новую запись и соответствующий ей объект */
    T create(T object) throws PersistException;

    /** Создает новую запись, соответствующую объекту object */
    T persist(T object) throws PersistException;

    /** Возвращает объект с базы с первичным ключом key или null */
    T getByPK(int key) throws PersistException;

    /** Обновляет состояние объекта  в базе данных */
    void update(T object) throws PersistException;

    /** Удаляет запись из базы данных */
    void delete(T object) throws PersistException;

    /** Возвращает список всех обьектов с базы */
    List<T> getAll() throws PersistException;
}
