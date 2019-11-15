package my.divine.project.web.command.admin;

import my.divine.project.db.Facade;
import my.divine.project.db.TransactionManagerFacade;
import my.divine.project.db.connector.postgresql.PostgresService;
import my.divine.project.exception.AppException;
import my.divine.project.exception.db.DBException;
import my.divine.project.model.entity.User;
import my.divine.project.web.Paths;
import my.divine.project.web.command.Command;
import my.divine.project.web.validator.Validator;
import my.divine.project.web.validator.user.LoginValidator;
import my.divine.project.web.validator.user.UserValidator;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class AddTeacherCommand extends Command {
    private static final Logger LOG = Logger.getLogger(AddTeacherCommand.class);

    private static final Validator<String> LOGIN_VALIDATOR = LoginValidator.getInstance();

    private static final Facade FACADE =
            TransactionManagerFacade.getInstance(PostgresService.getInstance());

    /**
     * Registration of a new teacher by user login
     * @param req
     * @param resp
     * @return path to personal page
     * @throws IOException
     * @throws AppException
     */
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, AppException {
        LOG.debug("Command start");

        String teacher = req.getParameter("login");

        LOG.trace(String.format("Add teacher by login %s", teacher));


        User user = FACADE.getUserByLogin(teacher);
        LOGIN_VALIDATOR.validate(user.getLogin());


        FACADE.setUserFieldRoleByUserLogin(teacher);

        LOG.debug("Command finished");
        return Paths.PERSONAL_PAGE;
    }
}
