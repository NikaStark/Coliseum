package com.coliseum.web.listeners;

import com.coliseum.model.entities.User;
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
        se.getSession().removeAttribute(Attribute.CURRENT_USER_ATR.getAttribute());
        LOGGER.info("Session destroyed successfully!");
    }

}
