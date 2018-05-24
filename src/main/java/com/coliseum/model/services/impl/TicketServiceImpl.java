package com.coliseum.model.services.impl;

import com.coliseum.exceptions.ServiceException;
import com.coliseum.model.dao.TicketDao;
import com.coliseum.model.entities.Ticket;
import com.coliseum.model.services.TicketService;
import com.coliseum.model.services.util.ConnectionAccess;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.util.List;
import java.util.UUID;

public class TicketServiceImpl extends AbstractJDBCService<Ticket, UUID, TicketDao<Connection>> implements TicketService {

    public TicketServiceImpl(TicketDao<Connection> dao) {
        super(dao);
        LOGGER = LoggerFactory.getLogger(TicketServiceImpl.class);
    }

    @Override
    public List<Ticket> getTicketsBySession(UUID sessionId) throws ServiceException {
        LOGGER.info("Get tickets by sessionId");
        return ConnectionAccess.connectionWrap(connection -> dao.getTicketsBySession(sessionId, connection));
    }

    @Override
    public void buyReservedTicketsByUser(UUID userId) throws ServiceException {
        LOGGER.info("Buy reserved tickets by userId = " + userId);
        ConnectionAccess.connectionWrap(connection -> {
            dao.buyReservedTicketsByUser(userId, connection);
            return null;
        });
    }

    @Override
    public void freeReservedTicketsByUser(UUID userId) throws ServiceException {
        LOGGER.info("Free reserved tickets by userId = " + userId);
        ConnectionAccess.connectionWrap(connection -> {
            dao.freeReservedTicketsByUser(userId, connection);
            return null;
        });
    }

}
