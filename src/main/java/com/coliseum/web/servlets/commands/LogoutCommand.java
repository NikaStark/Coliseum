package com.coliseum.web.servlets.commands;

import com.coliseum.model.entities.User;
import com.coliseum.web.util.Attribute;
import com.coliseum.web.util.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogoutCommand implements ICommand {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogoutCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.info("Executing logout command");
        request.getSession().setAttribute(Attribute.CURRENT_USER_ATR.getAttribute(), User.GUEST_USER);
        response.sendRedirect(request.getContextPath() + "/" + Command.DEFAULT_PAGE_CMD.getCommand());
    }

}
