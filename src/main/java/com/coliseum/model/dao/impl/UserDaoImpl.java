package com.coliseum.model.dao.impl;

import com.coliseum.exceptions.PersistentException;
import com.coliseum.model.dao.UserDao;
import com.coliseum.model.entities.User;
import com.coliseum.model.entities.enums.Role;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class UserDaoImpl extends AbstractJDBCDao<User, UUID> implements UserDao<Connection> {

    {
        LOGGER = LoggerFactory.getLogger(UserDaoImpl.class);
        SQL_FIND_ALL = "SELECT " + User.ID_COLUMN + ", " + User.USERNAME_COLUMN + ", " + User.PASSWORD_COLUMN + ", " + User.EMAIL_COLUMN + ", " + User.FIRST_NAME_COLUMN + ", " + User.LAST_NAME_COLUMN + ", " + User.ROLE_COLUMN + " FROM " + User.TABLE_NAME;
        SQL_FIND_ALL_LIMIT = SQL_FIND_ALL + " LIMIT ? OFFSET ?";
        SQL_FIND_BY_PK = SQL_FIND_ALL + " WHERE " + User.ID_COLUMN + "=?";
        SQL_GET_COUNT = "SELECT count(*) AS " + User.COUNT + " FROM " + User.TABLE_NAME;
        SQL_INSERT = "INSERT INTO " + User.TABLE_NAME + " (" + User.ID_COLUMN + ", " + User.USERNAME_COLUMN + ", " + User.PASSWORD_COLUMN + ", " + User.EMAIL_COLUMN + ", " + User.FIRST_NAME_COLUMN + ", " + User.LAST_NAME_COLUMN + ", " + User.ROLE_COLUMN + ") VALUES (?, ?, ?, ?, ?, ?, ?)";
        SQL_UPDATE = "UPDATE " + User.TABLE_NAME + " SET " + User.USERNAME_COLUMN + "=?, " + User.PASSWORD_COLUMN + "=?, " + User.EMAIL_COLUMN + "=?, " + User.FIRST_NAME_COLUMN + "=?, " + User.LAST_NAME_COLUMN + "=?, " + User.ROLE_COLUMN + "=? WHERE " + User.ID_COLUMN + "=?";
        SQL_DELETE = "DELETE FROM " + User.TABLE_NAME + " WHERE " + User.ID_COLUMN + "=?";
    }

    private String SQL_AUTHENTICATION = SQL_FIND_ALL + " WHERE " + User.USERNAME_COLUMN + "=?" + " AND " + User.PASSWORD_COLUMN + "=?";

    UserDaoImpl() {
    }

    @Override
    public User authentication(String username, String password, Connection connection) throws PersistentException {
        List<User> list;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_AUTHENTICATION)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            list = parseResultSet(resultSet);
        } catch (SQLException e) {
            LOGGER.error("Authentication with username=" + username, e);
            throw new PersistentException(e);
        }
        if (Objects.isNull(list) || list.isEmpty()) {
            return null;
        }
        if (list.size() > 1) {
            LOGGER.warn("Received more than one record, with username=" + username);
        }
        return list.iterator().next();
    }

    @Override
    protected List<User> parseResultSet(ResultSet resultSet) throws PersistentException {
        List<User> list = new ArrayList<>();
        try {
            while (resultSet.next()) {
                User user = new User();
                user.setId(UUID.fromString(resultSet.getString(User.ID_COLUMN)));
                user.setUsername(resultSet.getString(User.USERNAME_COLUMN));
                user.setPassword(resultSet.getString(User.PASSWORD_COLUMN));
                user.setEmail(resultSet.getString(User.EMAIL_COLUMN));
                user.setFirstName(resultSet.getString(User.FIRST_NAME_COLUMN));
                user.setLastName(resultSet.getString(User.LAST_NAME_COLUMN));
                user.setRole(Role.valueOf(resultSet.getString(User.ROLE_COLUMN)));
                list.add(user);
            }
        } catch (SQLException e) {
            LOGGER.error("parseResultSet ", e);
            throw new PersistentException(e);
        }
        return list;
    }

    @Override
    protected void parseResultSetGeneratedKeys(ResultSet generatedKeys, User object) throws PersistentException {
        // This is mock, there is no need for this method because key for this entity generated on client side
    }

    @Override
    protected int parseResultSetCount(ResultSet resultSet) throws PersistentException {
        int count = 0;
        try {
            if (resultSet.next()) {
                count = resultSet.getInt(User.COUNT);
            }
        } catch (SQLException e) {
            LOGGER.error("parseResultSetCount ", e);
            throw new PersistentException(e);
        }
        return count;
    }

    @Override
    protected void preparedStatementForInsert(PreparedStatement preparedStatement, User object) throws PersistentException {
        try {
            preparedStatement.setObject(1, object.getId());
            preparedStatement.setString(2, object.getUsername());
            preparedStatement.setString(3, object.getPassword());
            preparedStatement.setString(4, object.getEmail());
            preparedStatement.setString(5, object.getFirstName());
            preparedStatement.setString(6, object.getLastName());
            preparedStatement.setString(7, object.getRole().toString());
        } catch (SQLException e) {
            LOGGER.error("preparedStatementForInsert ", e);
            throw new PersistentException(e);
        }
    }

    @Override
    protected void preparedStatementForUpdate(PreparedStatement preparedStatement, User object) throws PersistentException {
        try {
            preparedStatement.setString(1, object.getUsername());
            preparedStatement.setString(2, object.getPassword());
            preparedStatement.setString(3, object.getEmail());
            preparedStatement.setString(4, object.getFirstName());
            preparedStatement.setString(5, object.getLastName());
            preparedStatement.setString(6, object.getRole().toString());
            preparedStatement.setObject(7, object.getId());
        } catch (SQLException e) {
            LOGGER.error("preparedStatementForUpdate ", e);
            throw new PersistentException(e);
        }
    }

}
