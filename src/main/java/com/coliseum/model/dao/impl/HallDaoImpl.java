package com.coliseum.model.dao.impl;

import com.coliseum.exceptions.PersistentException;
import com.coliseum.model.dao.HallDao;
import com.coliseum.model.entities.Hall;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HallDaoImpl extends AbstractJDBCDao<Hall, Integer> implements HallDao<Connection> {

    {
        LOGGER = LoggerFactory.getLogger(HallDaoImpl.class);
        SQL_FIND_ALL = "SELECT " + Hall.ID_COLUMN + ", " + Hall.COUNT_ROWS_COLUMN + ", " + Hall.COUNT_COLUMNS_COLUMN + ", " + Hall.NAME_COLUMN + " FROM " + Hall.TABLE_NAME;
        SQL_FIND_ALL_LIMIT = SQL_FIND_ALL + " LIMIT ? OFFSET ?";
        SQL_FIND_BY_PK = SQL_FIND_ALL + " WHERE " + Hall.ID_COLUMN + "=?";
        SQL_GET_COUNT = "SELECT count(*) AS " + Hall.COUNT + " FROM " + Hall.TABLE_NAME;
        SQL_INSERT = "INSERT INTO " + Hall.TABLE_NAME + " (" + Hall.ID_COLUMN + ", " + Hall.COUNT_ROWS_COLUMN + ", " + Hall.COUNT_COLUMNS_COLUMN + ", " + Hall.NAME_COLUMN + ") VALUES (?, ?, ?, ?)";
        SQL_UPDATE = "UPDATE " + Hall.TABLE_NAME + " SET " + Hall.COUNT_ROWS_COLUMN + "=?, " + Hall.COUNT_COLUMNS_COLUMN + "=?, " + Hall.NAME_COLUMN + "=? WHERE " + Hall.ID_COLUMN + "=?";
        SQL_DELETE = "DELETE FROM " + Hall.TABLE_NAME + " WHERE " + Hall.ID_COLUMN + "=?";
    }

    HallDaoImpl() {
    }

    @Override
    protected List<Hall> parseResultSet(ResultSet resultSet) throws PersistentException {
        List<Hall> list = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Hall hall = new Hall();
                hall.setId(resultSet.getInt(Hall.ID_COLUMN));
                hall.setCountRows(resultSet.getInt(Hall.COUNT_ROWS_COLUMN));
                hall.setCountColumns(resultSet.getInt(Hall.COUNT_COLUMNS_COLUMN));
                hall.setName(resultSet.getString(Hall.NAME_COLUMN));
                list.add(hall);
            }
        } catch (SQLException e) {
            LOGGER.error("parseResultSet ", e);
            throw new PersistentException(e);
        }
        return list;
    }

    @Override
    protected void parseResultSetGeneratedKeys(ResultSet generatedKeys, Hall object) throws PersistentException {
        try {
            if (generatedKeys.next()) {
                object.setId(generatedKeys.getInt(Hall.ID_COLUMN));
            }
        } catch (SQLException e) {
            LOGGER.error("parseResultSetGeneratedKeys ", e);
            throw new PersistentException(e);
        }
    }

    @Override
    protected int parseResultSetCount(ResultSet resultSet) throws PersistentException {
        int count = 0;
        try {
            if (resultSet.next()) {
                count = resultSet.getInt(Hall.COUNT);
            }
        } catch (SQLException e) {
            LOGGER.error("parseResultSetCount ", e);
            throw new PersistentException(e);
        }
        return count;
    }

    @Override
    protected void preparedStatementForInsert(PreparedStatement preparedStatement, Hall object) throws PersistentException {
        try {
            preparedStatement.setInt(1, object.getId());
            preparedStatement.setInt(2, object.getCountRows());
            preparedStatement.setInt(3, object.getCountColumns());
            preparedStatement.setString(4, object.getName());
        } catch (SQLException e) {
            LOGGER.error("preparedStatementForInsert ", e);
            throw new PersistentException(e);
        }
    }

    @Override
    protected void preparedStatementForUpdate(PreparedStatement preparedStatement, Hall object) throws PersistentException {
        try {
            preparedStatement.setInt(1, object.getCountRows());
            preparedStatement.setInt(2, object.getCountColumns());
            preparedStatement.setString(3, object.getName());
            preparedStatement.setObject(4, object.getId());
        } catch (SQLException e) {
            LOGGER.error("preparedStatementForUpdate ", e);
            throw new PersistentException(e);
        }
    }

}
