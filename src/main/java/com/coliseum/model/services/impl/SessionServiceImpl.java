package com.coliseum.model.services.impl;

import com.coliseum.model.dao.SessionDao;
import com.coliseum.model.entities.Session;
import com.coliseum.model.services.SessionService;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.util.UUID;

public class SessionServiceImpl extends AbstractJDBCService<Session, UUID, SessionDao<Connection>> implements SessionService {

    public SessionServiceImpl(SessionDao<Connection> dao) {
        super(dao);
        LOGGER = LoggerFactory.getLogger(SessionServiceImpl.class);
    }

}
