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

public class UpdateUserInfoCommand implements ICommand {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpdateUserInfoCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.info("Executing update user info command");
        User userProfile = new User();
        String uuid = request.getParameter(Attribute.ID_ATR.getAttribute());
        userProfile.setId(UUID.fromString(uuid));
        String username = request.getParameter(Attribute.USERNAME_ATR.getAttribute());
        String email = request.getParameter(Attribute.EMAIL_ATR.getAttribute());
        String firstName = request.getParameter(Attribute.FIRST_NAME_ATR.getAttribute());
        String lastName = request.getParameter(Attribute.LAST_NAME_ATR.getAttribute());

        //TODO check to null
        if (username.isEmpty() || email.isEmpty() || firstName.isEmpty() || lastName.isEmpty()) {
            LOGGER.info("Not all fields of form change information are filled");
            //TODO output message: wrong input
            response.sendRedirect(request.getContextPath() + "/" + Command.PROFILE_PAGE_CMD.getCommand());
            return;
        }
        userProfile.setUsername(username);
        userProfile.setEmail(email);
        userProfile.setFirstName(firstName);
        userProfile.setLastName(lastName);

        UserService userService = ((ServiceFactory) request.getServletContext()
                .getAttribute(Attribute.SERVICE_FACTORY_ATR.getAttribute())).getService(UserServiceImpl.class);
        try {
            userService.modify(userProfile);
        } catch (ServiceException e) {
            e.printStackTrace(); //TODO
        }
        //TODO output message: changes update success
        response.sendRedirect(request.getContextPath() + "/" + Command.PROFILE_PAGE_CMD.getCommand());
    }

}
