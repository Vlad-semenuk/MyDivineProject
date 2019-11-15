package my.divine.project.web.command.common;

import my.divine.project.db.Facade;
import my.divine.project.db.TransactionManagerFacade;
import my.divine.project.db.connector.postgresql.PostgresService;
import my.divine.project.exception.AppException;
import my.divine.project.model.constant.Topic;
import my.divine.project.model.entity.Course;
import my.divine.project.model.entity.User;
import my.divine.project.web.Paths;
import my.divine.project.web.command.Command;
import my.divine.project.web.command.commandUtils.CommandUtils;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ToCourseListCommand extends Command {

    private static final Logger LOG = Logger.getLogger(ToCourseListCommand.class);

    private static final Facade FACADE =
            TransactionManagerFacade.getInstance(PostgresService.getInstance());

    /**
     * list of all available courses
     * @param req
     * @param resp
     * @return Path to courses list page
     * @throws IOException
     * @throws AppException
     */
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, AppException {
        LOG.debug("Command start");


        List<Course> courses = getCourses();

        List<User> teachers = FACADE.getAllTeacher();

        List<Topic> topics = CommandUtils.getAllTopics();

        LOG.trace(String.format("List of courses size--> %s", courses.size()));


        req.setAttribute("courses", courses);
        req.setAttribute("teachers", teachers);
        req.setAttribute("topics", topics);


        LOG.debug("Command finished");
        return Paths.COURSE_LIST_PAGE;
    }

    private List<Course> getCourses () throws AppException{
        int count;

        List<Course> courses = FACADE.getCoursesByState();
        CommandUtils.setCount(courses);
        return courses;

    }


}
