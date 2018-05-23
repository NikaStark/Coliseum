package com.coliseum.web.servlets.commands;

import com.coliseum.web.servlets.commands.pages.*;
import com.coliseum.web.util.Command;

public enum FactoryCommand {

    DEFAULT_PAGE_CMD(Command.DEFAULT_PAGE_CMD, new DefaultPageCommand()),
    LOGIN_PAGE_CMD(Command.LOGIN_PAGE_CMD, new LoginPageCommand()),
    HOME_PAGE_CMD(Command.HOME_PAGE_CMD, new HomePageCommand()),
    REG_PAGE_CMD(Command.REG_PAGE_CMD, new RegistrationPageCommand()),
    PROFILE_PAGE_CMD(Command.PROFILE_PAGE_CMD, new ProfilePageCommand()),
    MOVIES_PAGE_CMD(Command.MOVIES_PAGE_CMD, new MoviesPageCommand()),
    TIMETABLE_PAGE_CMD(Command.TIMETABLE_PAGE_CMD, new TimetablePageCommand()),
    SESSION_PAGE_CMD(Command.SESSION_PAGE_CMD, new SessionPageCommand()),
    AUTH_CMD(Command.AUTH_CMD, new AuthCommand()),
    LOGOUT_CMD(Command.LOGOUT_CMD, new LogoutCommand()),
    CREATE_USER_CMD(Command.CREATE_USER_CMD, new CreateUserCommand()),
    UPDATE_USER_PASSWORD_CMD(Command.UPDATE_USER_PASSWORD_CMD, new UpdateUserPasswordCommand()),
    UPDATE_USER_INFO_CMD(Command.UPDATE_USER_INFO_CMD, new UpdateUserInfoCommand());

    private Command commandName;
    private ICommand commandImpl;

    FactoryCommand(Command commandName, ICommand commandImpl) {
        this.commandName = commandName;
        this.commandImpl = commandImpl;
    }

    public static ICommand getCommand(Command command) {
        for (FactoryCommand fCommand : values()) {
            if (fCommand.commandName == command) {
                return fCommand.commandImpl;
            }
        }
        throw new IllegalArgumentException();
    }

}
