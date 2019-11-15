package my.divine.project.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import my.divine.project.exception.AppException;
import my.divine.project.web.command.Command;
import my.divine.project.web.command.CommandContainer;
import org.apache.log4j.Logger;

/**
 * The main servlet,
 * receives a command and redirects
 */

@WebServlet("/controller")
public class Controller extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(Controller.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.trace("doGet started");

        process(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.trace("doPost started");
        process(req, resp);
    }
    private void process (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.debug("Controller starts");


        String commandName = req.getParameter("command");

        LOG.trace(String.format("Request parameter: command --> %s", commandName));

        Command command = CommandContainer.getCommand(commandName);


        String view = executeCommand(command, req, resp);
        LOG.trace(String.format("Forward address --> %s", view));
        LOG.debug("Controller finished");

        req.getRequestDispatcher(view).forward(req, resp);




    }

    private String executeCommand(Command command, HttpServletRequest req, HttpServletResponse resp){
        String view = Paths.ERROR_SERVLET;
        try {

            view = command.execute(req, resp);
        } catch (IOException | AppException e) {
            req.getSession().setAttribute("error", e);
        }

        return view;

    }
}
