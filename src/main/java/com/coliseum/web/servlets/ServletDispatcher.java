package com.coliseum.web.servlets;

import com.coliseum.web.servlets.commands.FactoryCommand;
import com.coliseum.web.util.Attribute;
import com.coliseum.web.util.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ServletDispatcher extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServletDispatcher.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        Command command = Command.valueOfByField(uri.substring(uri.lastIndexOf('/') + 1));
        req.setAttribute(Attribute.COMMAND_ATR.getAttribute(), command.getCommand());
        LOGGER.info("Dispatch control to command: \"" + command.getCommand() + "\"");
        FactoryCommand.getCommand(command).execute(req, resp);
    }

}
