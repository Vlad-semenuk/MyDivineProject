package my.divine.project.web.command.common;

import my.divine.project.db.Facade;
import my.divine.project.db.TransactionManagerFacade;
import my.divine.project.db.connector.postgresql.PostgresService;
import my.divine.project.exception.AppException;
import my.divine.project.model.entity.Course;
import my.divine.project.web.Paths;
import my.divine.project.web.command.Command;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CourseListTeacherCommand extends Command {

    private static final Logger LOG = Logger.getLogger(CourseListTeacherCommand.class);

    private static final Facade FACADE =
            TransactionManagerFacade.getInstance(PostgresService.getInstance());

    /**
     *
     Select a specific teacher
     * @param req
     * @param resp
     * @return Path to course list page
     * @throws IOException
     * @throws AppException
     */

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, AppException {
        LOG.debug("Command start");

        String selectTeacher = req.getParameter("teachers");
        LOG.trace(String.format("Request parameter: teacher --> %s", selectTeacher));

        List<Course> courses = getCoursesByTeacher(selectTeacher);

        LOG.trace(String.format("List courses size --> %s", courses.size()));


        req.setAttribute("courses", courses);


        LOG.debug("Command finished");

        return Paths.COURSE_LIST_SORT_PAGE;
    }

    private List<Course> getCoursesByTeacher(String teacher) throws AppException {
        List<Course> courses = FACADE.getCoursesByTeacher(teacher);

        return courses;
    }
}
