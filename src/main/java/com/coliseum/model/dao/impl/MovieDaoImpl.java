package com.coliseum.model.dao.impl;

import com.coliseum.exceptions.PersistentException;
import com.coliseum.model.dao.MovieDao;
import com.coliseum.model.entities.Movie;
import com.coliseum.model.entities.enums.MovieStatus;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MovieDaoImpl extends AbstractJDBCDao<Movie, UUID> implements MovieDao<Connection> {

    {
        LOGGER = LoggerFactory.getLogger(MovieDaoImpl.class);
        SQL_FIND_ALL = "SELECT " + Movie.ID_COLUMN + ", " + Movie.TITLE_COLUMN + ", " + Movie.DURATION_COLUMN + ", " + Movie.STATUS_COLUMN + " FROM " + Movie.TABLE_NAME;
        SQL_FIND_ALL_LIMIT = SQL_FIND_ALL + " LIMIT ? OFFSET ?";
        SQL_FIND_BY_PK = SQL_FIND_ALL + " WHERE " + Movie.ID_COLUMN + "=?";
        SQL_GET_COUNT = "SELECT count(*) AS " + Movie.COUNT + " FROM " + Movie.TABLE_NAME;
        SQL_INSERT = "INSERT INTO " + Movie.TABLE_NAME + " (" + Movie.ID_COLUMN + ", " + Movie.TITLE_COLUMN + ", " + Movie.DURATION_COLUMN + ", " + Movie.STATUS_COLUMN + ") VALUES (?, ?, ?, ?)";
        SQL_UPDATE = "UPDATE " + Movie.TABLE_NAME + " SET " + Movie.TITLE_COLUMN + "=?, " + Movie.DURATION_COLUMN + "=?, " + Movie.STATUS_COLUMN + "=? WHERE " + Movie.ID_COLUMN + "=?";
        SQL_DELETE = "DELETE FROM " + Movie.TABLE_NAME + " WHERE " + Movie.ID_COLUMN + "=?";
    }

    MovieDaoImpl() {
    }

    @Override
    protected List<Movie> parseResultSet(ResultSet resultSet) throws PersistentException {
        List<Movie> list = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Movie movie = new Movie();
                movie.setId(UUID.fromString(resultSet.getString(Movie.ID_COLUMN)));
                movie.setTitle(resultSet.getString(Movie.TITLE_COLUMN));
                movie.setDuration(resultSet.getInt(Movie.DURATION_COLUMN));
                movie.setStatus(MovieStatus.valueOf(resultSet.getString(Movie.STATUS_COLUMN)));
                list.add(movie);
            }
        } catch (SQLException e) {
            LOGGER.error("parseResultSet ", e);
            throw new PersistentException(e);
        }
        return list;
    }

    @Override
    protected void parseResultSetGeneratedKeys(ResultSet generatedKeys, Movie object) throws PersistentException {
        // This is mock, there is no need for this method because key for this entity generated on client side
    }

    @Override
    protected int parseResultSetCount(ResultSet resultSet) throws PersistentException {
        int count = 0;
        try {
            if (resultSet.next()) {
                count = resultSet.getInt(Movie.COUNT);
            }
        } catch (SQLException e) {
            LOGGER.error("parseResultSetCount ", e);
            throw new PersistentException(e);
        }
        return count;
    }

    @Override
    protected void preparedStatementForInsert(PreparedStatement preparedStatement, Movie object) throws PersistentException {
        try {
            preparedStatement.setObject(1, object.getId());
            preparedStatement.setString(2, object.getTitle());
            preparedStatement.setInt(3, object.getDuration());
            preparedStatement.setString(4, object.getStatus().toString());
        } catch (SQLException e) {
            LOGGER.error("preparedStatementForInsert ", e);
            throw new PersistentException(e);
        }
    }

    @Override
    protected void preparedStatementForUpdate(PreparedStatement preparedStatement, Movie object) throws PersistentException {
        try {
            preparedStatement.setString(1, object.getTitle());
            preparedStatement.setInt(2, object.getDuration());
            preparedStatement.setString(3, object.getStatus().toString());
            preparedStatement.setObject(4, object.getId());
        } catch (SQLException e) {
            LOGGER.error("preparedStatementForUpdate ", e);
            throw new PersistentException(e);
        }
    }

}
