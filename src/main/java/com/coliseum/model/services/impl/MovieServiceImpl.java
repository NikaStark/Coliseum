package com.coliseum.model.services.impl;

import com.coliseum.model.dao.MovieDao;
import com.coliseum.model.entities.Movie;
import com.coliseum.model.services.MovieService;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.util.UUID;

public class MovieServiceImpl extends AbstractJDBCService<Movie, UUID, MovieDao<Connection>> implements MovieService {

    public MovieServiceImpl(MovieDao<Connection> dao) {
        super(dao);
        LOGGER = LoggerFactory.getLogger(MovieServiceImpl.class);
    }

}
