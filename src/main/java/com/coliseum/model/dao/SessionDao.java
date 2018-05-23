package com.coliseum.model.dao;

import com.coliseum.model.entities.Session;

import java.util.UUID;

public interface SessionDao<Context> extends GenericDao<Session, UUID, Context> {
}
