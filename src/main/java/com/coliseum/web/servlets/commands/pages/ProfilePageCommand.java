package com.coliseum.web.servlets.commands.pages;

import com.coliseum.exceptions.ServiceException;
import com.coliseum.model.entities.User;
import com.coliseum.model.services.ServiceFactory;
import com.coliseum.model.services.UserService;
import com.coliseum.model.services.impl.UserServiceImpl;
import com.coliseum.web.servlets.commands.ICommand;
import com.coliseum.web.util.Attribute;
import com.coliseum.web.util.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

public class ProfilePageCommand implements ICommand {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProfilePageCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.info("Executing profile page command");
        String uuid = request.getParameter(Attribute.ID_ATR.getAttribute());
        User userProfile = new User();
        UserService userService = ((ServiceFactory) request.getServletContext()
                .getAttribute(Attribute.SERVICE_FACTORY_ATR.getAttribute())).getService(UserServiceImpl.class);
        UUID userId = Objects.nonNull(uuid) ? UUID.fromString(uuid) :
                ((User) request.getSession().getAttribute(Attribute.CURRENT_USER_ATR.getAttribute())).getId();
        try {
            userProfile = userService.getByPK(userId);
        } catch (ServiceException e) {
            e.printStackTrace(); //TODO
        }
        request.setAttribute(Attribute.ID_ATR.getAttribute(), userProfile.getId());
        request.setAttribute(Attribute.USERNAME_ATR.getAttribute(), userProfile.getUsername());
        request.setAttribute(Attribute.EMAIL_ATR.getAttribute(), userProfile.getEmail());
        request.setAttribute(Attribute.FIRST_NAME_ATR.getAttribute(), userProfile.getFirstName());
        request.setAttribute(Attribute.LAST_NAME_ATR.getAttribute(), userProfile.getLastName());

        request.getRequestDispatcher(Page.PROFILE_PAGE.getPath()).forward(request, response);
    }

}
