package com.coliseum.web.servlets.commands;

import com.coliseum.exceptions.DataException;
import com.coliseum.exceptions.ServiceException;
import com.coliseum.model.entities.User;
import com.coliseum.model.services.ServiceFactory;
import com.coliseum.model.services.UserService;
import com.coliseum.model.services.impl.UserServiceImpl;
import com.coliseum.web.util.Attribute;
import com.coliseum.web.util.Command;
import com.coliseum.web.validators.TextValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class AuthCommand implements ICommand {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.info("Executing auth command");
        String username = request.getParameter(Attribute.USERNAME_ATR.getAttribute());
        String password = request.getParameter(Attribute.PASSWORD_ATR.getAttribute());

        try {
            if (TextValidator.isValidUsername(username)) {
                LOGGER.info("Fields are not valid");
                request.setAttribute(Attribute.MESSAGE_ATR.getAttribute(), "You use not valid symbols");
                request.getRequestDispatcher(Command.LOGIN_PAGE_CMD.getCommand()).forward(request, response);
                return;
            }
        } catch (DataException e) {
            e.printStackTrace();
        }
        UserService userService = ((ServiceFactory) request.getServletContext()
                .getAttribute(Attribute.SERVICE_FACTORY_ATR.getAttribute())).getService(UserServiceImpl.class);
        User currentUser = (User) request.getSession().getAttribute(Attribute.CURRENT_USER_ATR.getAttribute());
        try {
            currentUser = userService.authentication(username, password);
        } catch (ServiceException e) {
            LOGGER.error("Something with db, " , e.getMessage());
            response.sendRedirect(request.getContextPath() + "/" + Command.ERROR_PAGE_CMD.getCommand());
        }
        if (Objects.nonNull(currentUser)) {
            LOGGER.info("Successfully authentication, username: " + username);
            request.getSession().setAttribute(Attribute.CURRENT_USER_ATR.getAttribute(), currentUser);
            response.sendRedirect(request.getContextPath() + "/" + Command.HOME_PAGE_CMD.getCommand());
        } else {
            LOGGER.info("Wrong username or password");
            request.setAttribute(Attribute.MESSAGE_ATR.getAttribute(), "Wrong username or password!");
            request.getRequestDispatcher(Command.LOGIN_PAGE_CMD.getCommand()).forward(request, response);
        }
    }

}
