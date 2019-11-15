package my.divine.project.web.command.teacher;

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
import java.util.Map;

public class RemoveStudentCommand extends Command {

    private static final Logger LOG = Logger.getLogger(RemoveStudentCommand.class);

    private static final Facade FACADE =
            TransactionManagerFacade.getInstance(PostgresService.getInstance());


    /**
     * Student exception selected by teacher
     * @param req
     * @param resp
     * @return Path to view course of teacher page
     * @throws IOException
     * @throws AppException
     */

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, AppException {
        LOG.debug("Command start");

        int courseID = (Integer) req.getSession().getAttribute("id");
        LOG.trace(String.format("Request parameter course id = %s", courseID));

        String login = req.getParameter("login");
        LOG.trace(String.format("Request parameter user login = %s", login));

        FACADE.deleteUserFromCourse(login, courseID);

        Map<User, Integer> students = FACADE.getUsersByCourse(courseID);

        req.getSession().setAttribute("students", students);

        LOG.debug("Command finished");
        return Paths.VIEW_COURSE_PAGE;
    }
}
