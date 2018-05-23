package com.coliseum.model.services;

public interface ServiceFactory {

    <T extends GenericService> T getService(Class<T> serviceEntityInterfaceImpl);

}