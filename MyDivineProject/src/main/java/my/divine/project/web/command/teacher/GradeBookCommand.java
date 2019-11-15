package my.divine.project.web.command.teacher;

import my.divine.project.db.Facade;
import my.divine.project.db.TransactionManagerFacade;
import my.divine.project.db.connector.postgresql.PostgresService;
import my.divine.project.exception.AppException;
import my.divine.project.model.entity.Course;
import my.divine.project.model.entity.User;
import my.divine.project.web.Paths;
import my.divine.project.web.command.Command;
import my.divine.project.web.command.commandUtils.CommandUtils;
import my.divine.project.web.command.common.RegCommandCommand;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GradeBookCommand extends Command {

    private static final Logger LOG = Logger.getLogger(GradeBookCommand.class);

    private static final Facade FACADE =
            TransactionManagerFacade.getInstance(PostgresService.getInstance());

    /**
     * Showing all courses of a particular teacher
     * @param req
     * @param resp
     * @return Path to grade book page
     * @throws IOException
     * @throws AppException
     */
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, AppException {
        LOG.debug("Command start");

        User user = (User) req.getSession().getAttribute("user");
        LOG.trace(String.format("Teacher login --> %s", user.getLogin()));

        List<Course> courses = FACADE.getCoursesByTeacher(user.getLogin());

        CommandUtils.setCount(courses);

        req.getSession().setAttribute("courses", courses);
        req.getSession().removeAttribute("students");

        LOG.debug("Command finished");
        return Paths.GRADE_BOOK_PAGE;
    }
}
