package com.coliseum.model.dao.impl;

import com.coliseum.model.dao.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class JDBCDaoFactory implements DaoFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(JDBCDaoFactory.class);
    private static final JDBCDaoFactory instance = new JDBCDaoFactory();
    private Map<Class, GenericDao> daoMap;

    private JDBCDaoFactory() {
        daoMap = new HashMap<>();
        daoMap.put(HallDaoImpl.class, new HallDaoImpl());
        LOGGER.info("Added implementation dao for " + HallDao.class.getSimpleName());

        daoMap.put(MovieDaoImpl.class, new MovieDaoImpl());
        LOGGER.info("Added implementation dao for " + MovieDao.class.getSimpleName());

        daoMap.put(UserDaoImpl.class, new UserDaoImpl());
        LOGGER.info("Added implementation dao for " + UserDao.class.getSimpleName());

        daoMap.put(SessionDaoImpl.class, new SessionDaoImpl());
        LOGGER.info("Added implementation dao for " + SessionDao.class.getSimpleName());

        daoMap.put(TicketDaoImpl.class, new TicketDaoImpl());
        LOGGER.info("Added implementation dao for " + TicketDao.class.getSimpleName());
    }

    public static JDBCDaoFactory getInstance() {
        return instance;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends GenericDao> T getDao(Class<T> daoEntityInterfaceImpl) {
        LOGGER.info("Get dao implementation " + daoEntityInterfaceImpl.getSimpleName());
        if (!daoMap.containsKey(daoEntityInterfaceImpl)) {
            LOGGER.warn("Dao instance of " + daoEntityInterfaceImpl.getSimpleName() + " not found.");
            return null; //Maybe throwing exception be better
        }
        return (T) daoMap.get(daoEntityInterfaceImpl);
    }

}
