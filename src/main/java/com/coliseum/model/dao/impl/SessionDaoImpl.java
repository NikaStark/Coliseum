package com.coliseum.model.dao.impl;

import com.coliseum.exceptions.PersistentException;
import com.coliseum.model.dao.SessionDao;
import com.coliseum.model.entities.Hall;
import com.coliseum.model.entities.Movie;
import com.coliseum.model.entities.Session;
import com.coliseum.model.entities.enums.SessionStatus;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SessionDaoImpl extends AbstractJDBCDao<Session, UUID> implements SessionDao<Connection> {

    {
        LOGGER = LoggerFactory.getLogger(SessionDaoImpl.class);
        SQL_FIND_ALL = "SELECT " + Session.ID_COLUMN + ", " + Session.MOVIE_COLUMN + ", " + Session.HALL_COLUMN + ", " + Session.START_TIME_COLUMN + ", " + Session.PRICE_COLUMN + ", " + Session.STATUS_COLUMN + " FROM " + Session.TABLE_NAME;
        SQL_FIND_ALL_LIMIT = SQL_FIND_ALL + " LIMIT ? OFFSET ?";
        SQL_FIND_BY_PK = SQL_FIND_ALL + " WHERE " + Session.ID_COLUMN + "=?";
        SQL_GET_COUNT = "SELECT count(*) AS " + Session.COUNT + " FROM " + Session.TABLE_NAME;
        SQL_INSERT = "INSERT INTO " + Session.TABLE_NAME + " (" + Session.ID_COLUMN + ", " + Session.MOVIE_COLUMN + ", " + Session.HALL_COLUMN + ", " + Session.START_TIME_COLUMN + ", " + Session.PRICE_COLUMN + ", " + Session.STATUS_COLUMN + ") VALUES (?, ?, ?, ?, ?, ?)";
        SQL_UPDATE = "UPDATE " + Session.TABLE_NAME + " SET " + Session.MOVIE_COLUMN + "=?, " + Session.HALL_COLUMN + "=?, " + Session.START_TIME_COLUMN + "=?, " + Session.PRICE_COLUMN + "=?, " + Session.STATUS_COLUMN + "=? WHERE " + Session.ID_COLUMN + "=?";
        SQL_DELETE = "DELETE FROM " + Session.TABLE_NAME + " WHERE " + Session.ID_COLUMN + "=?";
    }

    SessionDaoImpl() {
    }

    @Override
    protected List<Session> parseResultSet(ResultSet resultSet) throws PersistentException {
        List<Session> list = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Session session = new Session();
                session.setMovie(new Movie());
                session.setHall(new Hall());
                session.setId(UUID.fromString(resultSet.getString(Session.ID_COLUMN)));
                session.getMovie().setId(UUID.fromString(resultSet.getString(Session.MOVIE_COLUMN)));
                session.getHall().setId(resultSet.getInt(Session.HALL_COLUMN));
                session.setStartTime(resultSet.getTimestamp(Session.START_TIME_COLUMN));
                session.setPrice(resultSet.getFloat(Session.PRICE_COLUMN));
                session.setStatus(SessionStatus.valueOf(resultSet.getString(Session.STATUS_COLUMN)));
                list.add(session);
            }
        } catch (SQLException e) {
            LOGGER.error("parseResultSet ", e);
            throw new PersistentException(e);
        }
        return list;
    }

    @Override
    protected void parseResultSetGeneratedKeys(ResultSet generatedKeys, Session object) throws PersistentException {
        // This is mock, there is no need for this method because key for this entity generated on client side
    }

    @Override
    protected int parseResultSetCount(ResultSet resultSet) throws PersistentException {
        int count = 0;
        try {
            if (resultSet.next()) {
                count = resultSet.getInt(Session.COUNT);
            }
        } catch (SQLException e) {
            LOGGER.error("parseResultSetCount ", e);
            throw new PersistentException(e);
        }
        return count;
    }

    @Override
    protected void preparedStatementForInsert(PreparedStatement preparedStatement, Session object) throws PersistentException {
        try {
            preparedStatement.setObject(1, object.getId());
            preparedStatement.setObject(2, object.getMovie().getId());
            preparedStatement.setInt(3, object.getHall().getId());
            preparedStatement.setTimestamp(4, new Timestamp(object.getStartTime().getTime()));
            preparedStatement.setFloat(5, object.getPrice());
            preparedStatement.setString(6, object.getStatus().toString());
        } catch (SQLException e) {
            LOGGER.error("preparedStatementForInsert ", e);
            throw new PersistentException(e);
        }
    }

    @Override
    protected void preparedStatementForUpdate(PreparedStatement preparedStatement, Session object) throws PersistentException {
        try {
            preparedStatement.setObject(1, object.getMovie().getId());
            preparedStatement.setInt(2, object.getHall().getId());
            preparedStatement.setTimestamp(3, new Timestamp(object.getStartTime().getTime()));
            preparedStatement.setFloat(4, object.getPrice());
            preparedStatement.setString(5, object.getStatus().toString());
            preparedStatement.setObject(6, object.getId());
        } catch (SQLException e) {
            LOGGER.error("preparedStatementForUpdate ", e);
            throw new PersistentException(e);
        }
    }

}
