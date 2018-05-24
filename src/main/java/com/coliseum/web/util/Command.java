package com.coliseum.web.util;

public enum Command {

    DEFAULT_PAGE_CMD(""),
    LOGIN_PAGE_CMD("login"),
    HOME_PAGE_CMD("home"),
    REG_PAGE_CMD("registration"),
    PROFILE_PAGE_CMD("profile"),
    MOVIES_PAGE_CMD("movies"),
    TIMETABLE_PAGE_CMD("timetable"),
    SESSION_PAGE_CMD("session"),
    ERROR_PAGE_CMD("error"),
    AUTH_CMD("auth"),
    LOGOUT_CMD("logout"),
    CREATE_USER_CMD("createUser"),
    UPDATE_USER_PASSWORD_CMD("updateUserPassword"),
    UPDATE_USER_INFO_CMD("updateUserInfo"),
    ADD_TO_BASKET_CMD("addToBasket"),
    REMOVE_FROM_BASKET_CMD("removeFromBasket"),
    BUY_BASKET_CMD("buyBasket");

    private String command;

    Command(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

    public static Command valueOfByField(String commandField) {
        for (Command command : values()) {
            if (command.command.equals(commandField)) {
                return command;
            }
        }
        throw new IllegalArgumentException("Try found command: \"" + commandField + "\"");
    }

}
