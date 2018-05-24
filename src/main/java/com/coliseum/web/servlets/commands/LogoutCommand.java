package com.coliseum.web.servlets.commands;

import com.coliseum.exceptions.ServiceException;
import com.coliseum.model.entities.User;
import com.coliseum.model.services.ServiceFactory;
import com.coliseum.model.services.impl.TicketServiceImpl;
import com.coliseum.web.util.Attribute;
import com.coliseum.web.util.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogoutCommand implements ICommand {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogoutCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.info("Executing logout command");
        try {
            ((ServiceFactory) request.getServletContext().getAttribute(Attribute.SERVICE_FACTORY_ATR.getAttribute()))
                    .getService(TicketServiceImpl.class).freeReservedTicketsByUser(
                    ((User) request.getSession().getAttribute(Attribute.CURRENT_USER_ATR.getAttribute())).getId());
        } catch (ServiceException e) {
            LOGGER.error("Don't free reserved tickets. ", e.getMessage());
            e.printStackTrace();
        }
        request.getSession().setAttribute(Attribute.CURRENT_USER_ATR.getAttribute(), User.GUEST_USER);
        response.sendRedirect(request.getContextPath() + "/" + Command.DEFAULT_PAGE_CMD.getCommand());
    }

}
