package my.divine.project.web.command.student;

import my.divine.project.db.Facade;
import my.divine.project.db.TransactionManagerFacade;
import my.divine.project.db.connector.postgresql.PostgresService;
import my.divine.project.exception.AppException;
import my.divine.project.model.constant.State;
import my.divine.project.model.entity.Course;
import my.divine.project.model.entity.User;
import my.divine.project.web.Paths;
import my.divine.project.web.command.Command;
import my.divine.project.web.command.common.RegCommandCommand;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class DeRegistrationCommand extends Command {

    private static final Logger LOG = Logger.getLogger(DeRegistrationCommand.class);

    private static final Facade FACADE =
            TransactionManagerFacade.getInstance(PostgresService.getInstance());

    /**
     * Cancellation of registration for the
     * course of the selected student
     * @param req
     * @param resp
     * @return path to main page
     * @throws IOException
     * @throws AppException
     */

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, AppException {
        LOG.debug("Command start");

        User user = (User) req.getSession().getAttribute("user");
        int courseID = Integer.parseInt(req.getParameter("id"));

        LOG.trace(String.format("Parameters for request id = %s",courseID ));
        LOG.trace(String.format("Parameters for request login = %s",user.getLogin() ));

        FACADE.deleteUserFromCourse(user.getLogin(), courseID);

        Map<Course, Integer> coursesOpen = FACADE.getCoursesByUserLogin(user.getLogin(), State.OPEN);
        Map<Course, Integer> coursesDuring = FACADE.getCoursesByUserLogin(user.getLogin(), State.DURING);
        Map<Course, Integer> coursesFinished = FACADE.getCoursesByUserLogin(user.getLogin(), State.FINISHED);

        req.getSession().setAttribute("coursesOpen", coursesOpen);
        req.getSession().setAttribute("coursesDuring", coursesDuring);
        req.getSession().setAttribute("coursesFinished", coursesFinished);


        LOG.debug("Command finished");
        return Paths.STUDENT_COURSES_PAGE;
    }
}
