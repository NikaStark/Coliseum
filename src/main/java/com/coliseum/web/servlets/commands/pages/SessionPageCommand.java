package com.coliseum.web.servlets.commands.pages;

import com.coliseum.exceptions.ServiceException;
import com.coliseum.model.entities.Session;
import com.coliseum.model.entities.Ticket;
import com.coliseum.model.entities.User;
import com.coliseum.model.entities.enums.Role;
import com.coliseum.model.entities.enums.TicketStatus;
import com.coliseum.model.services.HallService;
import com.coliseum.model.services.ServiceFactory;
import com.coliseum.model.services.SessionService;
import com.coliseum.model.services.TicketService;
import com.coliseum.model.services.impl.HallServiceImpl;
import com.coliseum.model.services.impl.SessionServiceImpl;
import com.coliseum.model.services.impl.TicketServiceImpl;
import com.coliseum.web.servlets.commands.ICommand;
import com.coliseum.web.util.Attribute;
import com.coliseum.web.util.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class SessionPageCommand implements ICommand {

    private static final Logger LOGGER = LoggerFactory.getLogger(SessionPageCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.info("Executing session page command");
        String uuid = request.getParameter(Attribute.SESSION_ID_ATR.getAttribute());
        if (Objects.isNull(uuid)) {
            uuid = request.getSession().getAttribute(Attribute.SESSION_ID_ATR.getAttribute()).toString();
        } else {
            request.getSession().setAttribute(Attribute.SESSION_ID_ATR.getAttribute(), uuid);
        }
        ServiceFactory serviceFactory = (ServiceFactory) request.getServletContext()
                .getAttribute(Attribute.SERVICE_FACTORY_ATR.getAttribute());
        SessionService sessionService = serviceFactory.getService(SessionServiceImpl.class);
        TicketService ticketService = serviceFactory.getService(TicketServiceImpl.class);
        HallService hallService = serviceFactory.getService(HallServiceImpl.class);
        try {
            Session session = sessionService.getByPK(UUID.fromString(uuid));
            List<Ticket> tickets = ticketService.getTicketsBySession(session.getId());
            List<Ticket> reservedTickets = new ArrayList<>();
            User currentUser = (User) request.getSession().getAttribute(Attribute.CURRENT_USER_ATR.getAttribute());
            if (currentUser.getRole().equals(Role.CLIENT)) {
                for (Ticket reservedTicket : tickets) {
                    if (reservedTicket.getStatus().equals(TicketStatus.RESERVED)
                            && reservedTicket.getUser().getId().equals(currentUser.getId())) {
                        reservedTickets.add(reservedTicket);
                    }
                }
            }
            session.setHall(hallService.getByPK(session.getHall().getId()));
            request.setAttribute(Attribute.TICKETS_ATR.getAttribute(), tickets);
            request.setAttribute(Attribute.RESERVED_TICKETS_ATR.getAttribute(), reservedTickets);
            request.setAttribute(Attribute.SESSION_ATR.getAttribute(), session);
            request.getRequestDispatcher(Page.SESSION_PAGE.getPath()).forward(request, response);
        } catch (ServiceException e) {
            e.printStackTrace(); //TODO go to error page
        }
    }

}
