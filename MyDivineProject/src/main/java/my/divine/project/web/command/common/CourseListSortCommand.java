package my.divine.project.web.command.common;

import my.divine.project.db.Facade;
import my.divine.project.db.TransactionManagerFacade;
import my.divine.project.db.connector.postgresql.PostgresService;
import my.divine.project.exception.AppException;
import my.divine.project.exception.ExceptionMessages;
import my.divine.project.model.entity.Course;
import my.divine.project.web.Paths;
import my.divine.project.web.command.Command;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CourseListSortCommand extends Command {

    private static final Logger LOG = Logger.getLogger(CourseListSortCommand.class);

    private static final Facade FACADE =
            TransactionManagerFacade.getInstance(PostgresService.getInstance());

    /**
     * Sorting the list of courses according to the selected sorting
     * @param req
     * @param resp
     * @return path to course list page
     * @throws IOException
     * @throws AppException
     */
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, AppException {

        LOG.debug("Command start");

        String sort = req.getParameter("sort");
        LOG.trace(String.format("Request parameter: sort --> %s", sort));





        List <Course> courses = getCourseList(sort);

        req.setAttribute("courses", courses);

        LOG.debug("Command finished");
        return Paths.COURSE_LIST_SORT_PAGE;
    }

    /**
     * Choice sort
     * @param sort
     * @return Courses list
     * @throws AppException
     */
    private List<Course> getCourseList (String sort ) throws AppException{
        List<Course> courses;
        if ("desc".equals(sort) || "asc".equals(sort)){
            courses = FACADE.getCoursesBySortName(sort);
            return courses;
        } else if ("count".equals(sort)){
            courses = FACADE.getCoursesBySortCount();

        } else {
            throw new AppException(ExceptionMessages.INCORRECT_SEARCH_PARAMETER_MESSAGE);

        }
       return courses;


    }
}
