package my.divine.project.web.command.admin;

import my.divine.project.db.Facade;
import my.divine.project.db.TransactionManagerFacade;
import my.divine.project.db.connector.postgresql.PostgresService;
import my.divine.project.exception.AppException;
import my.divine.project.model.entity.Course;
import my.divine.project.model.entity.User;
import my.divine.project.web.Paths;
import my.divine.project.web.command.Command;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ToEditCourseListCommand extends Command {

    private static final Logger LOG = Logger.getLogger(ToEditCourseListCommand.class);

    private static final Facade FACADE =
            TransactionManagerFacade.getInstance(PostgresService.getInstance());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, AppException {
        LOG.debug("Command starts");

        List<Course> courses = FACADE.getCourses();
        LOG.trace(String.format("Courses size = %s",courses.size() ));

        req.getSession().setAttribute("courses", courses);

        List<User> teachers = FACADE.getAllTeacher();

        req.getSession().setAttribute("teachers", teachers);

        LOG.debug("Command finished");
        return Paths.EDIT_COURSES_LIST_PAGE;
    }
}
