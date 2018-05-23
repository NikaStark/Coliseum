package com.coliseum.model.dao;

import com.coliseum.exceptions.PersistentException;
import com.coliseum.model.entities.User;

import java.util.UUID;

public interface UserDao<Context> extends GenericDao<User, UUID, Context> {

    User authentication(String username, String password, Context context) throws PersistentException;

}
