package my.divine.project.web.command.student;

import my.divine.project.db.Facade;
import my.divine.project.db.TransactionManagerFacade;
import my.divine.project.db.connector.postgresql.PostgresService;
import my.divine.project.exception.AppException;
import my.divine.project.exception.ExceptionMessages;
import my.divine.project.model.entity.User;
import my.divine.project.web.Paths;
import my.divine.project.web.command.Command;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class RegistrationToCourseCommand extends Command {

    private static final Logger LOG = Logger.getLogger(RegistrationToCourseCommand.class);

    private static final Facade FACADE =
            TransactionManagerFacade.getInstance(PostgresService.getInstance());

    /**
     * Student registration for the selected course
     *
     * @param req
     * @param resp
     * @return path to main page
     * @throws IOException
     * @throws AppException
     */
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, AppException {
        LOG.debug("Command starts");

        int courseID = Integer.parseInt(req.getParameter("id"));
        User user = (User) req.getSession().getAttribute("user");
        if (!FACADE.checkUserReg(user.getLogin(), courseID)){
            FACADE.setUserToCourse(user.getLogin(), courseID);
        }else {
            throw new AppException(ExceptionMessages.USER_IS_ALREADY_REGISTERED_FOR_COURSE_MASSAGE);
        }




        LOG.debug("Command finished");
        return Paths.MAIN_PAGE;
    }
}
