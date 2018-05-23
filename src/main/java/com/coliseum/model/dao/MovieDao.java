package com.coliseum.model.dao;

import com.coliseum.model.entities.Movie;

import java.util.UUID;

public interface MovieDao<Context> extends GenericDao<Movie, UUID, Context> {
}
