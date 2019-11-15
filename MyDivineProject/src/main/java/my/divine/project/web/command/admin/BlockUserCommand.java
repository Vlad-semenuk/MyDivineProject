package my.divine.project.web.command.admin;

import my.divine.project.db.Facade;
import my.divine.project.db.TransactionManagerFacade;
import my.divine.project.db.connector.postgresql.PostgresService;
import my.divine.project.exception.AppException;
import my.divine.project.model.entity.User;
import my.divine.project.web.Paths;
import my.divine.project.web.command.Command;
import my.divine.project.web.validator.Validator;
import my.divine.project.web.validator.user.LoginValidator;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BlockUserCommand extends Command {
    private static final Logger LOG = Logger.getLogger(BlockUserCommand.class);

    private static final Validator<String> LOGIN_VALIDATOR = LoginValidator.getInstance();
    private static final Facade FACADE =
            TransactionManagerFacade.getInstance(PostgresService.getInstance());

    /**
     * Block user by login by administrator
     * @param req
     * @param resp
     * @return path to personal page
     * @throws IOException
     * @throws AppException
     */
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, AppException {
        LOG.debug("Command start");

        String login = req.getParameter("login");

        LOG.trace(String.format("Block user by login %s", login));

        User user = FACADE.getUserByLogin(login);
        LOGIN_VALIDATOR.validate(user.getLogin());

        FACADE.setUserFieldBlockedByUserLogin(true, user.getLogin());



        LOG.debug("Command finished");
        return Paths.PERSONAL_PAGE;
    }
}
