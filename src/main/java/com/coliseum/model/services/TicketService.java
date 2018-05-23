package com.coliseum.model.services;

import com.coliseum.exceptions.ServiceException;
import com.coliseum.model.entities.Ticket;

import java.util.List;
import java.util.UUID;

public interface TicketService extends GenericService<Ticket, UUID> {

    List<Ticket> getTicketsBySession(UUID sessionId) throws ServiceException;

}
