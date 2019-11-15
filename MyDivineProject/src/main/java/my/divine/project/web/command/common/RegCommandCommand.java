package my.divine.project.web.command.common;

import my.divine.project.db.Facade;
import my.divine.project.db.TransactionManagerFacade;
import my.divine.project.db.connector.postgresql.PostgresService;
import my.divine.project.exception.AppException;
import my.divine.project.model.entity.User;
import my.divine.project.web.Paths;
import my.divine.project.web.command.Command;
import my.divine.project.web.command.commandUtils.CommandUtils;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Create new user in DB
 */
public class RegCommandCommand extends Command {
    private static final Logger LOG = Logger.getLogger(RegCommandCommand.class);


    private static final Facade FACADE =
            TransactionManagerFacade.getInstance(PostgresService.getInstance());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws AppException {
        LOG.debug("Command starts");

        User user = CommandUtils.getUserFromRequest(req);

        LOG.trace(String.format("Request parameter: user --> %s", user.toString()));


        LOG.trace(String.format("User created (true, false) --> %b", FACADE.createUser(user)));


        return Paths.AUTHENTICATION_SERVLET;
    }
}
