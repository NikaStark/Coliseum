package com.coliseum.web.servlets.commands.pages;

import com.coliseum.web.servlets.commands.ICommand;
import com.coliseum.web.util.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ErrorPageCommand implements ICommand {

    private static final Logger LOGGER = LoggerFactory.getLogger(ErrorPageCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.info("Executing error page command");
        request.getRequestDispatcher(Page.ERROR_PAGE.getPath()).forward(request, response);
    }

}
