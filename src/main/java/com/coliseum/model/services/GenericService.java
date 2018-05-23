package com.coliseum.model.services;

import com.coliseum.exceptions.ServiceException;
import com.coliseum.model.entities.Identified;

import java.io.Serializable;
import java.util.List;

public interface GenericService<T extends Identified<PK>, PK extends Serializable> {

    void create(T object) throws ServiceException;

    T getByPK(PK key) throws ServiceException;

    int getCount() throws ServiceException;

    void update(T object) throws ServiceException;

    void delete(T object) throws ServiceException;

    List<T> getAll() throws ServiceException;

    List<T> getAllLimit(int limit, int offset) throws ServiceException;

}
