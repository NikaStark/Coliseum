package com.coliseum.web.util;

public enum Page {

    DEFAULT_PAGE("/WEB-INF/pages/index.jsp"),
    LOGIN_PAGE("/WEB-INF/pages/login.jsp"),
    HOME_PAGE("/WEB-INF/pages/home.jsp"),
    REG_PAGE("/WEB-INF/pages/registration.jsp"),
    PROFILE_PAGE("/WEB-INF/pages/profile.jsp"),
    MOVIES_PAGE("/WEB-INF/pages/movies.jsp"),
    TIMETABLE_PAGE("/WEB-INF/pages/timetable.jsp"),
    SESSION_PAGE("/WEB-INF/pages/session.jsp");

    private String path;

    Page(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

}
