package com.coliseum.web.util;

import com.coliseum.model.entities.enums.Role;

public enum SecurityConf {

    DEFAULT_PAGE_ACCESS(Command.DEFAULT_PAGE_CMD, new Role[]{Role.GUEST, Role.CLIENT, Role.MANAGER, Role.ADMIN}),
    LOGIN_PAGE_ACCESS(Command.LOGIN_PAGE_CMD, new Role[]{Role.GUEST}),
    HOME_PAGE_ACCESS(Command.HOME_PAGE_CMD, new Role[]{Role.CLIENT, Role.MANAGER, Role.ADMIN}),
    REG_PAGE_ACCESS(Command.REG_PAGE_CMD, new Role[]{Role.GUEST, Role.ADMIN}),
    PROFILE_PAGE_ACCESS(Command.PROFILE_PAGE_CMD, new Role[]{Role.CLIENT, Role.MANAGER, Role.ADMIN}),
    MOVIES_PAGE_ACCESS(Command.MOVIES_PAGE_CMD, new Role[]{Role.GUEST, Role.CLIENT, Role.MANAGER, Role.ADMIN}),
    TIMETABLE_PAGE_ACCESS(Command.TIMETABLE_PAGE_CMD, new Role[]{Role.GUEST, Role.CLIENT, Role.MANAGER, Role.ADMIN}),
    SESSION_PAGE_ACCESS(Command.SESSION_PAGE_CMD, new Role[]{Role.GUEST, Role.CLIENT, Role.MANAGER, Role.ADMIN}),
    AUTH_ACCESS(Command.AUTH_CMD, new Role[]{Role.GUEST}),
    LOGOUT_ACCESS(Command.LOGOUT_CMD, new Role[]{Role.CLIENT, Role.MANAGER, Role.ADMIN}),
    CREATE_USER_ACCESS(Command.CREATE_USER_CMD, new Role[]{Role.GUEST, Role.ADMIN}),
    UPDATE_USER_PASSWORD_ACCESS(Command.UPDATE_USER_PASSWORD_CMD, new Role[]{Role.CLIENT, Role.MANAGER, Role.ADMIN}),
    UPDATE_USER_INFO_ACCESS(Command.UPDATE_USER_INFO_CMD, new Role[]{Role.CLIENT, Role.MANAGER, Role.ADMIN});

    private Command command;
    private Role[] roles;

    SecurityConf(Command command, Role[] roles) {
        this.command = command;
        this.roles = roles;
    }

    public static SecurityConf valueOfByField(Command command) {
        for (SecurityConf securityConf : values()) {
            if (securityConf.command == command) {
                return securityConf;
            }
        }
        throw new IllegalArgumentException();
    }

    public boolean isAvailable(Role role) {
        for (Role availableRole : roles) {
            if (availableRole == role) {
                return true;
            }
        }
        return false;
    }

}
