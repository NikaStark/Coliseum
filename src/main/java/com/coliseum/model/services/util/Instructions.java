package com.coliseum.model.services.util;

import com.coliseum.exceptions.PersistentException;
import com.coliseum.exceptions.ServiceException;

import java.sql.Connection;

@FunctionalInterface
public interface Instructions<T> {

    T execute(Connection connection) throws PersistentException, ServiceException;

}