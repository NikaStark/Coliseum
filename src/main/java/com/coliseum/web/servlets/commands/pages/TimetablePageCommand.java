package com.coliseum.web.servlets.commands.pages;

import com.coliseum.exceptions.ServiceException;
import com.coliseum.model.entities.Session;
import com.coliseum.model.services.HallService;
import com.coliseum.model.services.MovieService;
import com.coliseum.model.services.ServiceFactory;
import com.coliseum.model.services.SessionService;
import com.coliseum.model.services.impl.HallServiceImpl;
import com.coliseum.model.services.impl.MovieServiceImpl;
import com.coliseum.model.services.impl.SessionServiceImpl;
import com.coliseum.web.servlets.commands.ICommand;
import com.coliseum.web.util.Attribute;
import com.coliseum.web.util.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class TimetablePageCommand implements ICommand {

    private static final Logger LOGGER = LoggerFactory.getLogger(TimetablePageCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.info("Executing timetable page command");
        int countElementsOnPage = 2;
        int numberPage = request.getParameter(Attribute.PAGE_ATR.getAttribute()) == null ? 1 :
                Integer.parseInt(request.getParameter(Attribute.PAGE_ATR.getAttribute()));
        request.setAttribute(Attribute.PAGE_ATR.getAttribute(), numberPage);
        ServiceFactory serviceFactory = (ServiceFactory) request.getServletContext()
                .getAttribute(Attribute.SERVICE_FACTORY_ATR.getAttribute());
        SessionService sessionService = serviceFactory.getService(SessionServiceImpl.class);
        MovieService movieService = serviceFactory.getService(MovieServiceImpl.class);
        HallService hallService = serviceFactory.getService(HallServiceImpl.class);
        try {
            List<Session> sessions = sessionService.getAllLimit(countElementsOnPage, countElementsOnPage * (numberPage - 1));
            for (Session session : sessions) {
                session.setMovie(movieService.getByPK(session.getMovie().getId()));
                session.setHall(hallService.getByPK(session.getHall().getId()));
            }
            request.setAttribute(Attribute.SESSIONS_ATR.getAttribute(), sessions);
            int[] pages = new int[(int) Math.ceil(sessionService.getCount() / (double) countElementsOnPage)];
            for (int i = 0; i < pages.length; i++) {
                pages[i] = i + 1;
            }
            request.setAttribute(Attribute.PAGES_ATR.getAttribute(), pages);
        } catch (ServiceException e) {
            e.printStackTrace(); //TODO
        }
        request.getRequestDispatcher(Page.TIMETABLE_PAGE.getPath()).forward(request, response);
    }

}
