package my.divine.project.web.command.common;

import my.divine.project.db.Facade;
import my.divine.project.db.TransactionManagerFacade;
import my.divine.project.db.connector.postgresql.PostgresService;
import my.divine.project.exception.AppException;
import my.divine.project.exception.ExceptionMessages;
import my.divine.project.model.constant.Topic;
import my.divine.project.model.entity.User;
import my.divine.project.web.Paths;
import my.divine.project.web.command.Command;
import my.divine.project.web.command.commandUtils.CommandUtils;
import my.divine.project.web.validator.Validator;
import my.divine.project.web.validator.user.LoginValidator;
import my.divine.project.web.validator.user.PasswordValidator;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class AuthenticationCommand extends Command {

    private static final Logger LOG = Logger.getLogger(AuthenticationCommand.class);

    private static final Validator<String> LOGIN_VALIDATOR = LoginValidator.getInstance();

    private static final Validator<String> PASSWORD_VALIDATOR = PasswordValidator.getInstance();

    private static final Facade FACADE =
            TransactionManagerFacade.getInstance(PostgresService.getInstance());

    /**
     *  Command for authentication
     *  verification of the field user who will be posted to the session
     * @param req
     * @param resp
     * @return path to main page
     * @throws IOException
     * @throws AppException
     */

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, AppException {
        LOG.debug("Command starts");

        String login = req.getParameter("login");
        LOG.trace(String.format("Request parameter: login --> %s", login));
        String password = req.getParameter("password");

        LOGIN_VALIDATOR.validate(login);
        PASSWORD_VALIDATOR.validate(password);

        User user = FACADE.getUserByLoginAndPassword(login, password);
        checkUser(user);
        List <Topic> topics = CommandUtils.getAllTopics();




        HttpSession session = req.getSession();
        session.setAttribute("user", user);
        session.setAttribute("topics", topics);


        LOG.debug("Command finished");
        return Paths.MAIN_SERVLET;
    }
    private void checkUser(User user) throws AppException {
        if (!user.isFilled()) {
            throw new AppException(ExceptionMessages.USER_NOT_EXIST_MESSAGE);
        }
    }
}
