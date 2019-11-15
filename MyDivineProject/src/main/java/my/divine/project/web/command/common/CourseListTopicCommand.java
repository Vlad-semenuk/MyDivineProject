package my.divine.project.web.command.common;

import my.divine.project.db.Facade;
import my.divine.project.db.TransactionManagerFacade;
import my.divine.project.db.connector.postgresql.PostgresService;
import my.divine.project.exception.AppException;
import my.divine.project.model.constant.Topic;
import my.divine.project.model.entity.Course;
import my.divine.project.web.Paths;
import my.divine.project.web.command.Command;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CourseListTopicCommand extends Command {

    private static final Logger LOG = Logger.getLogger(CourseListTopicCommand.class);

    private static final Facade FACADE =
            TransactionManagerFacade.getInstance(PostgresService.getInstance());


    /**
     Select a specific topics
     * @param req
     * @param resp
     * @return Path to course list page
     * @throws IOException
     * @throws AppException
     */

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, AppException {
        LOG.debug("Command start");

        Topic selectTopic = Topic.valueOf(req.getParameter("topics"));
        LOG.trace(String.format("Request parameter: topic --> %s", selectTopic));

        List<Course> courses = getCoursesByTopic(selectTopic);

        LOG.trace(String.format("List course of size --> %s", courses.size()));

         req.setAttribute("courses", courses);
        LOG.debug("Command finished");
        return Paths.COURSE_LIST_SORT_PAGE;
    }

    private List<Course> getCoursesByTopic (Topic topic) throws AppException{
        List<Course> courses = FACADE.getCoursesByTopic(topic);
        return  courses;
    }
}
