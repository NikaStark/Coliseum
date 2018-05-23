package com.coliseum.web.servlets.commands.pages;

import com.coliseum.exceptions.ServiceException;
import com.coliseum.model.services.MovieService;
import com.coliseum.model.services.ServiceFactory;
import com.coliseum.model.services.impl.MovieServiceImpl;
import com.coliseum.web.servlets.commands.ICommand;
import com.coliseum.web.util.Attribute;
import com.coliseum.web.util.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MoviesPageCommand implements ICommand {

    private static final Logger LOGGER = LoggerFactory.getLogger(MoviesPageCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.info("Executing movies page command");
        int countElementsOnPage = 2;
        int numberPage = request.getParameter(Attribute.PAGE_ATR.getAttribute()) == null ? 1 :
                Integer.parseInt(request.getParameter(Attribute.PAGE_ATR.getAttribute()));
        request.setAttribute(Attribute.PAGE_ATR.getAttribute(), numberPage);
        MovieService movieService = ((ServiceFactory) request.getServletContext()
                .getAttribute(Attribute.SERVICE_FACTORY_ATR.getAttribute())).getService(MovieServiceImpl.class);
        try {
            request.setAttribute(Attribute.MOVIES_ATR.getAttribute(),
                    movieService.getAllLimit(countElementsOnPage, countElementsOnPage * (numberPage - 1)));
            int[] pages = new int[(int) Math.ceil(movieService.getCount() / (double)countElementsOnPage)];
            for (int i = 0; i < pages.length; i++) {
                pages[i] = i + 1;
            }
            request.setAttribute(Attribute.PAGES_ATR.getAttribute(), pages);
        } catch (ServiceException e) {
            e.printStackTrace(); //TODO
        }
        request.getRequestDispatcher(Page.MOVIES_PAGE.getPath()).forward(request, response);
    }

}
