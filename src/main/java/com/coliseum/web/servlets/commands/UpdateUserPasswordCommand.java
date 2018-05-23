package com.coliseum.web.servlets.commands;

import com.coliseum.exceptions.ServiceException;
import com.coliseum.model.entities.User;
import com.coliseum.model.services.ServiceFactory;
import com.coliseum.model.services.UserService;
import com.coliseum.model.services.impl.UserServiceImpl;
import com.coliseum.web.util.Attribute;
import com.coliseum.web.util.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

public class UpdateUserPasswordCommand implements ICommand {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpdateUserPasswordCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.info("Executing update user password command");
        User userProfile = new User();
        String uuid = request.getParameter(Attribute.ID_ATR.getAttribute());
        userProfile.setId(UUID.fromString(uuid));
        String password = request.getParameter(Attribute.PASSWORD_ATR.getAttribute());
        String passwordRepeat = request.getParameter(Attribute.PASSWORD_REPEAT_ATR.getAttribute());

        //TODO check to null
        if (password.isEmpty() || passwordRepeat.isEmpty()) {
            LOGGER.info("Not all fields of form change password are filled");
            //TODO output message: wrong input
            request.getRequestDispatcher(Command.PROFILE_PAGE_CMD.getCommand()).forward(request, response);
            return;
        }
        if (!password.equals(passwordRepeat)) {
            LOGGER.info("Passwords not equal");
            //TODO output message: wrong input
            request.getRequestDispatcher(Command.PROFILE_PAGE_CMD.getCommand()).forward(request, response);
            return;
        }
        userProfile.setPassword(password);
        UserService userService = ((ServiceFactory) request.getServletContext()
                .getAttribute(Attribute.SERVICE_FACTORY_ATR.getAttribute())).getService(UserServiceImpl.class);
        try {
            userService.modify(userProfile);
        } catch (ServiceException e) {
            e.printStackTrace(); //TODO
        }
        //TODO output message: changes update success
        request.getRequestDispatcher(Command.PROFILE_PAGE_CMD.getCommand()).forward(request, response);
    }

}
