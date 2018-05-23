package com.coliseum.model.dao.impl;

import com.coliseum.exceptions.PersistentException;
import com.coliseum.model.dao.TicketDao;
import com.coliseum.model.entities.Session;
import com.coliseum.model.entities.Ticket;
import com.coliseum.model.entities.User;
import com.coliseum.model.entities.enums.TicketStatus;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class TicketDaoImpl extends AbstractJDBCDao<Ticket, UUID> implements TicketDao<Connection> {

    {
        LOGGER = LoggerFactory.getLogger(TicketDaoImpl.class);
        SQL_FIND_ALL = "SELECT " + Ticket.ID_COLUMN + ", " + Ticket.USER_COLUMN + ", " + Ticket.SESSION_COLUMN + ", " + Ticket.ROW_COLUMN + ", " + Ticket.SEAT_COLUMN + ", " + Ticket.STATUS_COLUMN + " FROM " + Ticket.TABLE_NAME;
        SQL_FIND_ALL_LIMIT = SQL_FIND_ALL + " LIMIT ? OFFSET ?";
        SQL_FIND_BY_PK = SQL_FIND_ALL + " WHERE " + Ticket.ID_COLUMN + "=?";
        SQL_GET_COUNT = "SELECT count(*) AS " + Ticket.COUNT + " FROM " + Ticket.TABLE_NAME;
        SQL_INSERT = "INSERT INTO " + Ticket.TABLE_NAME + " (" + Ticket.ID_COLUMN + ", " + Ticket.USER_COLUMN + ", " + Ticket.SESSION_COLUMN + ", " + Ticket.ROW_COLUMN + ", " + Ticket.SEAT_COLUMN + ", " + Ticket.STATUS_COLUMN + ") VALUES (?, ?, ?, ?, ?, ?)";
        SQL_UPDATE = "UPDATE " + Ticket.TABLE_NAME + " SET " + Ticket.USER_COLUMN + "=?, " + Ticket.SESSION_COLUMN + "=?, " + Ticket.ROW_COLUMN + "=?, " + Ticket.SEAT_COLUMN + "=?, " + Ticket.STATUS_COLUMN + "=? WHERE " + Ticket.ID_COLUMN + "=?";
        SQL_DELETE = "DELETE FROM " + Ticket.TABLE_NAME + " WHERE " + Ticket.ID_COLUMN + "=?";
    }

    private String SQL_GET_TICKETS_BY_SESSION = SQL_FIND_ALL + " WHERE " + Ticket.SESSION_COLUMN + "=?";

    TicketDaoImpl() {
    }

    @Override
    public List<Ticket> getTicketsBySession(UUID sessionId, Connection connection) throws PersistentException {
        List<Ticket> list;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_TICKETS_BY_SESSION)) {
            preparedStatement.setObject(1, sessionId);
            ResultSet resultSet = preparedStatement.executeQuery();
            list = parseResultSet(resultSet);
        } catch (SQLException e) {
            LOGGER.error("getTicketsBySession", e);
            throw new PersistentException(e);
        }
        return Objects.nonNull(list) ? list : Collections.<Ticket>emptyList();
    }

    @Override
    protected List<Ticket> parseResultSet(ResultSet resultSet) throws PersistentException {
        List<Ticket> list = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Ticket ticket = new Ticket();
                ticket.setUser(new User());
                ticket.setSession(new Session());
                ticket.setId(UUID.fromString(resultSet.getString(Ticket.ID_COLUMN)));
                ticket.getUser().setId(UUID.fromString(resultSet.getString(Ticket.USER_COLUMN)));
                ticket.getSession().setId(UUID.fromString(resultSet.getString(Ticket.SESSION_COLUMN)));
                ticket.setRow(resultSet.getInt(Ticket.ROW_COLUMN));
                ticket.setSeat(resultSet.getInt(Ticket.SEAT_COLUMN));
                ticket.setStatus(TicketStatus.valueOf(resultSet.getString(Ticket.STATUS_COLUMN)));
                list.add(ticket);
            }
        } catch (SQLException e) {
            LOGGER.error("parseResultSet ", e);
            throw new PersistentException(e);
        }
        return list;
    }

    @Override
    protected void parseResultSetGeneratedKeys(ResultSet generatedKeys, Ticket object) throws PersistentException {
        // This is mock, there is no need for this method because key for this entity generated on client side
    }

    @Override
    protected int parseResultSetCount(ResultSet resultSet) throws PersistentException {
        int count = 0;
        try {
            if (resultSet.next()) {
                count = resultSet.getInt(Ticket.COUNT);
            }
        } catch (SQLException e) {
            LOGGER.error("parseResultSetCount ", e);
            throw new PersistentException(e);
        }
        return count;
    }

    @Override
    protected void preparedStatementForInsert(PreparedStatement preparedStatement, Ticket object) throws PersistentException {
        try {
            preparedStatement.setObject(1, object.getId());
            preparedStatement.setObject(2, object.getUser().getId());
            preparedStatement.setObject(3, object.getSession().getId());
            preparedStatement.setInt(4, object.getRow());
            preparedStatement.setInt(5, object.getSeat());
            preparedStatement.setString(6, object.getStatus().toString());
        } catch (SQLException e) {
            LOGGER.error("preparedStatementForInsert ", e);
            throw new PersistentException(e);
        }
    }

    @Override
    protected void preparedStatementForUpdate(PreparedStatement preparedStatement, Ticket object) throws PersistentException {
        try {
            preparedStatement.setObject(1, object.getUser().getId());
            preparedStatement.setObject(2, object.getSession().getId());
            preparedStatement.setInt(3, object.getRow());
            preparedStatement.setInt(4, object.getSeat());
            preparedStatement.setString(5, object.getStatus().toString());
            preparedStatement.setObject(6, object.getId());
        } catch (SQLException e) {
            LOGGER.error("preparedStatementForUpdate ", e);
            throw new PersistentException(e);
        }
    }
    
}
