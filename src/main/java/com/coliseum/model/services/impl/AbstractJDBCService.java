package com.coliseum.model.services.impl;

import com.coliseum.exceptions.ServiceException;
import com.coliseum.model.dao.GenericDao;
import com.coliseum.model.entities.Identified;
import com.coliseum.model.services.GenericService;
import com.coliseum.model.services.util.ConnectionAccess;
import org.slf4j.Logger;

import java.io.Serializable;
import java.sql.Connection;
import java.util.List;

public abstract class AbstractJDBCService<T extends Identified<PK>, PK extends Serializable, DaoType extends GenericDao<T, PK, Connection>> implements GenericService<T, PK> {

    protected Logger LOGGER;
    protected DaoType dao;

    public AbstractJDBCService(DaoType dao) {
        this.dao = dao;
    }

    @Override
    public void create(T object) throws ServiceException {
        LOGGER.info("Create new " + object.getClass().getSimpleName());
        ConnectionAccess.connectionWrap(connection -> {
            dao.create(object, connection);
            return null;
        });
    }

    @Override
    public T getByPK(PK key) throws ServiceException {
        LOGGER.info("Get by primary key");
        return ConnectionAccess.connectionWrap(connection -> dao.getByPK(key, connection));
    }

    @Override
    public int getCount() throws ServiceException {
        LOGGER.info("Get count rows");
        return ConnectionAccess.connectionWrap(dao::getCount);
    }

    @Override
    public void update(T object) throws ServiceException {
        LOGGER.info("Update " + object.getClass().getSimpleName());
        ConnectionAccess.connectionWrap(connection -> {
            dao.update(object, connection);
            return null;
        });
    }

    @Override
    public void delete(T object) throws ServiceException {
        LOGGER.info("Delete " + object.getClass().getSimpleName());
        ConnectionAccess.connectionWrap(connection -> {
            dao.delete(object, connection);
            return null;
        });
    }

    @Override
    public List<T> getAll() throws ServiceException {
        LOGGER.info("Get all");
        return ConnectionAccess.connectionWrap(dao::getAll);
    }

    @Override
    public List<T> getAllLimit(int limit, int offset) throws ServiceException {
        LOGGER.info("Get all limit");
        return ConnectionAccess.connectionWrap(connection -> dao.getAllLimit(limit, offset, connection));
    }

}
