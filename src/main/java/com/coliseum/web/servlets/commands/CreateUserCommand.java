package com.coliseum.web.servlets.commands;

import com.coliseum.exceptions.ServiceException;
import com.coliseum.model.entities.User;
import com.coliseum.model.entities.enums.Role;
import com.coliseum.model.services.ServiceFactory;
import com.coliseum.model.services.UserService;
import com.coliseum.model.services.impl.UserServiceImpl;
import com.coliseum.web.servlets.commands.pages.RegistrationPageCommand;
import com.coliseum.web.util.Attribute;
import com.coliseum.web.util.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateUserCommand implements ICommand {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationPageCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.info("Executing registration command");
        String username = request.getParameter(Attribute.USERNAME_ATR.getAttribute());
        String password = request.getParameter(Attribute.PASSWORD_ATR.getAttribute());
        String passwordRepeat = request.getParameter(Attribute.PASSWORD_REPEAT_ATR.getAttribute());
        String email = request.getParameter(Attribute.EMAIL_ATR.getAttribute());
        String firstName = request.getParameter(Attribute.FIRST_NAME_ATR.getAttribute());
        String lastName = request.getParameter(Attribute.LAST_NAME_ATR.getAttribute());

        if (username.isEmpty() || password.isEmpty() || passwordRepeat.isEmpty() || email.isEmpty()
                || firstName.isEmpty() || lastName.isEmpty()) {
            LOGGER.info("Not all fields are filled");
            //TODO output message: wrong input
            request.getRequestDispatcher(Command.REG_PAGE_CMD.getCommand()).forward(request, response);
            return;
        }
        if (!password.equals(passwordRepeat)) {
            LOGGER.info("Passwords not equal");
            //TODO output message: wrong input
            request.getRequestDispatcher(Command.REG_PAGE_CMD.getCommand()).forward(request, response);
            return;
        }

        User user = new User(username, password, email, firstName, lastName,
                (((User) request.getSession().getAttribute(Attribute.CURRENT_USER_ATR.getAttribute())).getRole() ==
                        Role.GUEST) ? Role.CLIENT : Role.MANAGER);
        UserService userService = ((ServiceFactory) request.getServletContext()
                .getAttribute(Attribute.SERVICE_FACTORY_ATR.getAttribute())).getService(UserServiceImpl.class);
        try {
            userService.create(user);
        } catch (ServiceException e) {
            e.printStackTrace(); //TODO
        }
        //TODO output message: reg success
        response.sendRedirect(request.getContextPath() + "/" +
                (((User) request.getSession().getAttribute(Attribute.CURRENT_USER_ATR.getAttribute()))
                        .getRole() != Role.GUEST ?
                        Command.HOME_PAGE_CMD.getCommand() :
                        Command.LOGIN_PAGE_CMD.getCommand()));
    }

}
