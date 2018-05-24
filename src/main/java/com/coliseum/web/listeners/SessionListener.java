package com.coliseum.web.listeners;

import com.coliseum.exceptions.ServiceException;
import com.coliseum.model.entities.User;
import com.coliseum.model.services.ServiceFactory;
import com.coliseum.model.services.impl.TicketServiceImpl;
import com.coliseum.web.util.Attribute;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {

    private static Logger LOGGER = LoggerFactory.getLogger(SessionListener.class);

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        se.getSession().setAttribute(Attribute.CURRENT_USER_ATR.getAttribute(), User.GUEST_USER);
        LOGGER.info("Session initialize successfully!");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        try {
            ((ServiceFactory) se.getSession().getServletContext()
                    .getAttribute(Attribute.SERVICE_FACTORY_ATR.getAttribute())).getService(TicketServiceImpl.class)
                    .freeReservedTicketsByUser(((User) se.getSession()
                            .getAttribute(Attribute.CURRENT_USER_ATR.getAttribute())).getId());
        } catch (ServiceException e) {
            LOGGER.error("Don't free reserved tickets. ", e.getMessage());
            e.printStackTrace();
        }
        se.getSession().removeAttribute(Attribute.CURRENT_USER_ATR.getAttribute());
        LOGGER.info("Session destroyed successfully!");
    }

}
