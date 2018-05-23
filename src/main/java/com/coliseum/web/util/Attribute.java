package com.coliseum.web.util;

public enum Attribute {
    
    COMMAND_ATR("cmd"),
    CURRENT_USER_ATR("currentUser"),
    ID_ATR("id"),
    USERNAME_ATR("username"),
    PASSWORD_ATR("password"),
    PASSWORD_REPEAT_ATR("passwordRepeat"),
    EMAIL_ATR("email"),
    FIRST_NAME_ATR("firstName"),
    LAST_NAME_ATR("lastName"),
    SERVICE_FACTORY_ATR("serviceFactory"),
    PAGE_ATR("page"),
    PAGES_ATR("pages"),
    MOVIES_ATR("movies"),
    SESSIONS_ATR("sessions"),
    SESSION_ID_ATR("sessionId"),
    NAVIGATION_FORM_ATR("navigationForm");

    private String attribute;

    Attribute(String attribute) {
        this.attribute = attribute;
    }

    public String getAttribute() {
        return attribute;
    }

}
