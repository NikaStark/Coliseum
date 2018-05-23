package com.coliseum.model.services;

import com.coliseum.exceptions.ServiceException;
import com.coliseum.model.entities.User;

import java.util.UUID;

public interface UserService extends GenericService<User, UUID> {

    User authentication(String username, String password) throws ServiceException;

    void modify(User user) throws ServiceException;

}
