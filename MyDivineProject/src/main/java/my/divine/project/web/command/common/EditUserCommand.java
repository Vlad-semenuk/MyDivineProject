package my.divine.project.web.command.common;

import my.divine.project.db.Facade;
import my.divine.project.db.TransactionManagerFacade;
import my.divine.project.db.connector.postgresql.PostgresService;
import my.divine.project.exception.AppException;
import my.divine.project.model.entity.User;
import my.divine.project.web.Paths;
import my.divine.project.web.command.Command;
import my.divine.project.web.validator.Validator;
import my.divine.project.web.validator.common.DateValidator;
import my.divine.project.web.validator.user.FullNameValidator;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditUserCommand extends Command {
    private static final Logger LOG = Logger.getLogger(CourseListSortCommand.class);

    private static final Facade FACADE =
            TransactionManagerFacade.getInstance(PostgresService.getInstance());

    private static final Validator<String> FULL_NAME_VALIDATOR = FullNameValidator.getInstance();



    @Override

    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, AppException {
        LOG.debug("Command start");

        String newFullName = req.getParameter("fullName");
        User user = (User) req.getSession().getAttribute("user");

        LOG.trace(String.format("Request parameter full name --> %s", newFullName));

        FULL_NAME_VALIDATOR.validate(newFullName);
        FACADE.updateUserFullName(user.getLogin(), newFullName);

        user.setFullName(newFullName);
        req.getSession().setAttribute("user", user);
        LOG.debug("Command finished");
        return Paths.PERSONAL_PAGE;
    }
}
