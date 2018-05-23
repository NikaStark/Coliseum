package com.coliseum.web.servlets.commands.pages;

import com.coliseum.exceptions.ServiceException;
import com.coliseum.model.entities.Session;
import com.coliseum.model.entities.Ticket;
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
import java.util.List;
import java.util.UUID;

public class SessionPageCommand implements ICommand {

    private static final Logger LOGGER = LoggerFactory.getLogger(SessionPageCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.info("Executing session page command");
        String uuid = request.getParameter(Attribute.SESSION_ID_ATR.getAttribute());
        ServiceFactory serviceFactory = (ServiceFactory) request.getServletContext()
                .getAttribute(Attribute.SERVICE_FACTORY_ATR.getAttribute());
        SessionService sessionService = serviceFactory.getService(SessionServiceImpl.class);
        TicketService ticketService = serviceFactory.getService(TicketServiceImpl.class);
        HallService hallService = serviceFactory.getService(HallServiceImpl.class);
        try {
            Session session = sessionService.getByPK(UUID.fromString(uuid));
            List<Ticket> tickets = ticketService.getTicketsBySession(session.getId());
            session.setHall(hallService.getByPK(session.getHall().getId()));
        } catch (ServiceException e) {
            e.printStackTrace(); //TODO
        }


        request.setAttribute(Attribute.ID_ATR.getAttribute(), userProfile.getId());
        request.setAttribute(Attribute.USERNAME_ATR.getAttribute(), userProfile.getUsername());
        request.setAttribute(Attribute.EMAIL_ATR.getAttribute(), userProfile.getEmail());
        request.setAttribute(Attribute.FIRST_NAME_ATR.getAttribute(), userProfile.getFirstName());
        request.setAttribute(Attribute.LAST_NAME_ATR.getAttribute(), userProfile.getLastName());

        request.getRequestDispatcher(Page.SESSION_PAGE.getPath()).forward(request, response);
    }

}
