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

public class ToUserCoursesCommand extends Command {

    private static final Logger LOG = Logger.getLogger(ToUserCoursesCommand.class);

    private static final Facade FACADE =
            TransactionManagerFacade.getInstance(PostgresService.getInstance());

    /**
     *
     * Receiving all student courses for the specified login with grades
     * and status divisions
     * @param req
     * @param resp
     * @return path to student courses page
     * @throws IOException
     * @throws AppException
     */

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, AppException {
        LOG.debug("Command start");

        User user = (User) req.getSession().getAttribute("user");

        Map<Course, Integer> coursesOpen = FACADE.getCoursesByUserLogin(user.getLogin(), State.OPEN);
        Map<Course, Integer> coursesDuring = FACADE.getCoursesByUserLogin(user.getLogin(), State.DURING);
        Map<Course, Integer> coursesFinished = FACADE.getCoursesByUserLogin(user.getLogin(), State.FINISHED);

        LOG.trace(String.format("Size of course Open, During, Finished --> %s %s %s", coursesOpen.size(), coursesDuring.size(), coursesFinished.size()));

       req.getSession().setAttribute("coursesOpen", coursesOpen);
       req.getSession().setAttribute("coursesDuring", coursesDuring);
       req.getSession().setAttribute("coursesFinished", coursesFinished);



        LOG.debug("Command finished");
        return Paths.STUDENT_COURSES_PAGE;
    }
}
