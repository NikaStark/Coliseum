package com.coliseum.web.servlets.commands;

import com.coliseum.exceptions.ServiceException;
import com.coliseum.model.entities.Ticket;
import com.coliseum.model.services.ServiceFactory;
import com.coliseum.model.services.TicketService;
import com.coliseum.model.services.impl.TicketServiceImpl;
import com.coliseum.web.util.Attribute;
import com.coliseum.web.util.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

public class RemoveFromBasketCommand implements ICommand {

    private static final Logger LOGGER = LoggerFactory.getLogger(RemoveFromBasketCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.info("Executing remove from basket command");
        TicketService ticketService = ((ServiceFactory) request.getServletContext()
                .getAttribute(Attribute.SERVICE_FACTORY_ATR.getAttribute())).getService(TicketServiceImpl.class);
        Ticket ticket = new Ticket();
        ticket.setId(UUID.fromString(request.getParameter(Attribute.TICKET_ID_ATR.getAttribute())));
        try {
            ticketService.delete(ticket);
            response.sendRedirect(request.getContextPath() + "/" + Command.SESSION_PAGE_CMD.getCommand());
        } catch (ServiceException e) {
            e.printStackTrace(); //TODO go to error page
        }
    }

}
