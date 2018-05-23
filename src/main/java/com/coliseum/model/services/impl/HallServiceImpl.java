package com.coliseum.model.services.impl;

import com.coliseum.model.dao.HallDao;
import com.coliseum.model.entities.Hall;
import com.coliseum.model.services.HallService;
import org.slf4j.LoggerFactory;

import java.sql.Connection;

public class HallServiceImpl extends AbstractJDBCService<Hall, Integer, HallDao<Connection>> implements HallService {

    public HallServiceImpl(HallDao<Connection> dao) {
        super(dao);
        LOGGER = LoggerFactory.getLogger(HallServiceImpl.class);
    }

}
