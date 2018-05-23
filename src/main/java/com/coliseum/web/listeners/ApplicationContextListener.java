package com.coliseum.web.listeners;

import com.coliseum.model.services.impl.JDBCServiceFactory;
import com.coliseum.web.util.Attribute;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ApplicationContextListener implements ServletContextListener {

    private static Logger LOGGER = LoggerFactory.getLogger(ApplicationContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        LOGGER.info("Context initialize start!");
        ServletContext servletContext = sce.getServletContext();
        try {
            Class.forName("com.coliseum.model.services.util.ConnectionAccess");
            servletContext.setAttribute(Attribute.SERVICE_FACTORY_ATR.getAttribute(), JDBCServiceFactory.getInstance());
            LOGGER.info("Context initialize finish successfully!");
        } catch (ClassNotFoundException e) {
            LOGGER.error("Not found class", e);
            LOGGER.error("Context initialize crash!");
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        LOGGER.info("Context destroyed successfully!");
    }

}
