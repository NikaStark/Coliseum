package com.coliseum.model.services.impl;

import com.coliseum.model.dao.DaoFactory;
import com.coliseum.model.dao.impl.*;
import com.coliseum.model.services.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class JDBCServiceFactory implements ServiceFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(JDBCServiceFactory.class);
    private static final JDBCServiceFactory instance = new JDBCServiceFactory(JDBCDaoFactory.getInstance());
    private Map<Class, GenericService> servicesMap;

    private JDBCServiceFactory(DaoFactory daoFactory) {
        servicesMap = new HashMap<>();
        servicesMap.put(HallServiceImpl.class, new HallServiceImpl(daoFactory.getDao(HallDaoImpl.class)));
        LOGGER.info("Added implementation service for " + HallService.class.getSimpleName());

        servicesMap.put(MovieServiceImpl.class, new MovieServiceImpl(daoFactory.getDao(MovieDaoImpl.class)));
        LOGGER.info("Added implementation service for " + MovieService.class.getSimpleName());

        servicesMap.put(UserServiceImpl.class, new UserServiceImpl(daoFactory.getDao(UserDaoImpl.class)));
        LOGGER.info("Added implementation service for " + UserService.class.getSimpleName());

        servicesMap.put(SessionServiceImpl.class, new SessionServiceImpl(daoFactory.getDao(SessionDaoImpl.class)));
        LOGGER.info("Added implementation service for " + SessionService.class.getSimpleName());

        servicesMap.put(TicketServiceImpl.class, new TicketServiceImpl(daoFactory.getDao(TicketDaoImpl.class)));
        LOGGER.info("Added implementation service for " + TicketService.class.getSimpleName());
    }

    public static JDBCServiceFactory getInstance() {
        return instance;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends GenericService> T getService(Class<T> serviceEntityInterfaceImpl) {
        LOGGER.info("Get service implementation " + serviceEntityInterfaceImpl.getSimpleName());
        if (!servicesMap.containsKey(serviceEntityInterfaceImpl)) {
            LOGGER.warn("Service instance of " + serviceEntityInterfaceImpl.getSimpleName() + " not found.");
            return null; //Maybe throwing exception be better
        }
        return (T) servicesMap.get(serviceEntityInterfaceImpl);
    }

}
