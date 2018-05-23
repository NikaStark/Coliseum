package com.coliseum.model.dao.impl;

import com.coliseum.exceptions.PersistentException;
import com.coliseum.model.dao.GenericDao;
import com.coliseum.model.entities.Identified;
import org.slf4j.Logger;

import java.io.Serializable;
import java.sql.*;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public abstract class AbstractJDBCDao<T extends Identified<PK>, PK extends Serializable> implements GenericDao<T, PK, Connection> {

    protected String SQL_FIND_ALL;
    protected String SQL_FIND_ALL_LIMIT;
    protected String SQL_FIND_BY_PK;
    protected String SQL_GET_COUNT;
    protected String SQL_INSERT;
    protected String SQL_UPDATE;
    protected String SQL_DELETE;
    protected Logger LOGGER;

    @Override
    public void create(T object, Connection connection) throws PersistentException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatementForInsert(preparedStatement, object);
            preparedStatement.executeUpdate();
            parseResultSetGeneratedKeys(preparedStatement.getGeneratedKeys(), object);
        } catch (SQLException e) {
            LOGGER.error("create", e);
            throw new PersistentException(e);
        }
    }

    @Override
    public T getByPK(PK key, Connection connection) throws PersistentException {
        List<T> list;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_PK)) {
            preparedStatement.setObject(1, key);
            ResultSet resultSet = preparedStatement.executeQuery();
            list = parseResultSet(resultSet);
        } catch (SQLException e) {
            LOGGER.error("getByPK with key=" + key, e);
            throw new PersistentException(e);
        }
        if (Objects.isNull(list) || list.isEmpty()) {
            return null;
        }
        if (list.size() > 1) {
            LOGGER.warn("Received more than one record, with key=" + key);
        }
        return list.iterator().next();
    }

    @Override
    public int getCount(Connection connection) throws PersistentException {
        int count;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_COUNT)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            count = parseResultSetCount(resultSet);
        } catch (SQLException e) {
            LOGGER.error("getCount", e);
            throw new PersistentException(e);
        }
        return count;
    }

    @Override
    public void update(T object, Connection connection) throws PersistentException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE)) {
            preparedStatementForUpdate(preparedStatement, object);
            if (preparedStatement.executeUpdate() != 1) {
                LOGGER.warn("On update modify more then 1 record!");
            }
        } catch (SQLException e) {
            LOGGER.error("update with object=" + object, e);
            throw new PersistentException(e);
        }
    }

    @Override
    public void delete(T object, Connection connection) throws PersistentException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE)) {
            preparedStatement.setObject(1, object.getId());
            if (preparedStatement.executeUpdate() != 1) {
                LOGGER.warn("On delete modify more then 1 record!");
            }
        } catch (SQLException e) {
            LOGGER.error("delete with object=" + object, e);
            throw new PersistentException(e);
        }
    }

    @Override
    public List<T> getAll(Connection connection) throws PersistentException {
        List<T> list;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            list = parseResultSet(resultSet);
        } catch (SQLException e) {
            LOGGER.error("getAll", e);
            throw new PersistentException(e);
        }
        return Objects.nonNull(list) ? list : Collections.<T>emptyList();
    }

    @Override
    public List<T> getAllLimit(int limit, int offset, Connection connection) throws PersistentException {
        List<T> list;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ALL_LIMIT)) {
            preparedStatement.setInt(1, limit);
            preparedStatement.setInt(2, offset);
            ResultSet resultSet = preparedStatement.executeQuery();
            list = parseResultSet(resultSet);
        } catch (SQLException e) {
            LOGGER.error("getAllLimit", e);
            throw new PersistentException(e);
        }
        return Objects.nonNull(list) ? list : Collections.<T>emptyList();
    }

    protected abstract List<T> parseResultSet(ResultSet resultSet) throws PersistentException;

    protected abstract void parseResultSetGeneratedKeys(ResultSet generatedKeys, T object) throws PersistentException;

    protected abstract int parseResultSetCount(ResultSet resultSet) throws PersistentException;

    protected abstract void preparedStatementForInsert(PreparedStatement preparedStatement, T object) throws PersistentException;

    protected abstract void preparedStatementForUpdate(PreparedStatement preparedStatement, T object) throws PersistentException;

}
