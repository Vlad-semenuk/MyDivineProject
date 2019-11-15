package my.divine.project.web.command.student;

import my.divine.project.db.Facade;
import my.divine.project.db.TransactionManagerFacade;
import my.divine.project.db.connector.postgresql.PostgresService;
import my.divine.project.exception.AppException;
import my.divine.project.model.entity.User;
import my.divine.project.web.Paths;
import my.divine.project.web.command.Command;
import my.divine.project.web.command.common.RegCommandCommand;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ToPersonalCommand extends Command {
    private static final Logger LOG = Logger.getLogger(ToPersonalCommand.class);

    private static final Facade FACADE =
            TransactionManagerFacade.getInstance(PostgresService.getInstance());

    /**
     * Each time you access your personal account, the user in the session is updated
     * @param req
     * @param resp
     * @return Path to personal page
     * @throws IOException
     * @throws AppException
     */
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, AppException {
        LOG.debug("Command start");

        User user = (User) req.getSession().getAttribute("user");

        user = FACADE.getUserByLogin(user.getLogin());

        req.getSession().setAttribute("user", user);

        LOG.debug("Command Finished");
        return Paths.PERSONAL_PAGE;
    }
}
