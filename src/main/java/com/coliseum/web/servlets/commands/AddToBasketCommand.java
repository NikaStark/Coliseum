package com.coliseum.web.servlets.commands;

import com.coliseum.exceptions.ServiceException;
import com.coliseum.model.entities.Session;
import com.coliseum.model.entities.Ticket;
import com.coliseum.model.entities.User;
import com.coliseum.model.entities.enums.Role;
import com.coliseum.model.entities.enums.TicketStatus;
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

public class AddToBasketCommand implements ICommand {

    private static final Logger LOGGER = LoggerFactory.getLogger(AddToBasketCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.info("Executing add to basket command");
        if (((User) request.getSession().getAttribute(Attribute.CURRENT_USER_ATR.getAttribute())).getRole() == Role.GUEST) {
            response.sendRedirect(request.getContextPath() + "/" + Command.LOGIN_PAGE_CMD.getCommand());
            return;
        }
        Integer row = Integer.parseInt(request.getParameter(Attribute.ROW_ATR.getAttribute()));
        Integer seat = Integer.parseInt(request.getParameter(Attribute.SEAT_ATR.getAttribute()));
        TicketService ticketService = ((ServiceFactory) request.getServletContext()
                .getAttribute(Attribute.SERVICE_FACTORY_ATR.getAttribute())).getService(TicketServiceImpl.class);
        try {
            Session session = new Session();
            session.setId(UUID.fromString(request.getSession().getAttribute(Attribute.SESSION_ID_ATR.getAttribute()).toString()));
            ticketService.create(new Ticket((User) request.getSession()
                    .getAttribute(Attribute.CURRENT_USER_ATR.getAttribute()), session, row, seat, TicketStatus.RESERVED));
            response.sendRedirect(request.getContextPath() + "/" + Command.SESSION_PAGE_CMD.getCommand());
        } catch (ServiceException e) {
            e.printStackTrace(); //TODO go to error page
        }
    }

}
