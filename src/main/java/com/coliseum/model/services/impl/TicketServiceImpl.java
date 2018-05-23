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
        return ConnectionAccess.connectionWrap(connection -> dao.getTicketsBySession(sessionId, connection));
    }
}
