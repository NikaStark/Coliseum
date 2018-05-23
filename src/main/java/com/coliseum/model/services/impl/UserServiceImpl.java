package com.coliseum.model.services.impl;

import com.coliseum.exceptions.ServiceException;
import com.coliseum.model.dao.UserDao;
import com.coliseum.model.entities.User;
import com.coliseum.model.services.UserService;
import com.coliseum.model.services.util.ConnectionAccess;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.util.Objects;
import java.util.UUID;

public class UserServiceImpl extends AbstractJDBCService<User, UUID, UserDao<Connection>> implements UserService {

    public UserServiceImpl(UserDao<Connection> dao) {
        super(dao);
        LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    }

    @Override
    public User authentication(String username, String password) throws ServiceException {
        LOGGER.info("Authentication by username and password");
        return ConnectionAccess.connectionWrap(connection -> dao.authentication(username, password, connection));
    }

    @Override
    public void modify(User user) throws ServiceException {
        LOGGER.info("Modify user");
        ConnectionAccess.transactionWrap(connection -> {
            User oldUser = dao.getByPK(user.getId(), connection);
            if (Objects.nonNull(user.getUsername())) {
                oldUser.setUsername(user.getUsername());
            }
            if (Objects.nonNull(user.getPassword())) {
                oldUser.setPassword(user.getPassword());
            }
            if (Objects.nonNull(user.getEmail())) {
                oldUser.setEmail(user.getEmail());
            }
            if (Objects.nonNull(user.getFirstName())) {
                oldUser.setFirstName(user.getFirstName());
            }
            if (Objects.nonNull(user.getLastName())) {
                oldUser.setLastName(user.getLastName());
            }
            dao.update(oldUser, connection);
            return null;
        });
    }

}
