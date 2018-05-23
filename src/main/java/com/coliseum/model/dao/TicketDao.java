package com.coliseum.model.dao;

import com.coliseum.exceptions.PersistentException;
import com.coliseum.model.entities.Ticket;

import java.util.List;
import java.util.UUID;

public interface TicketDao<Context> extends GenericDao<Ticket, UUID, Context> {

    List<Ticket> getTicketsBySession(UUID sessionId, Context context) throws PersistentException;

}
