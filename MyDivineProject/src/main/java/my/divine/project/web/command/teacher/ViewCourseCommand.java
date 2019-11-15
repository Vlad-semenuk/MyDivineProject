package my.divine.project.web.command.teacher;

import my.divine.project.db.Facade;
import my.divine.project.db.TransactionManagerFacade;
import my.divine.project.db.connector.postgresql.PostgresService;
import my.divine.project.exception.AppException;
import my.divine.project.model.constant.State;
import my.divine.project.model.entity.User;
import my.divine.project.web.Paths;
import my.divine.project.web.command.Command;
import my.divine.project.web.command.common.RegCommandCommand;
import org.apache.log4j.Logger;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class ViewCourseCommand extends Command {

    private static final Logger LOG = Logger.getLogger(ViewCourseCommand.class);

    private static final Facade FACADE =
            TransactionManagerFacade.getInstance(PostgresService.getInstance());

    /**
     * Showing a teacher-selected course with a list of students
     * @param req
     * @param resp
     * @return Path to view course of teacher page
     * @throws IOException
     * @throws AppException
     */
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, AppException {
        LOG.debug("Command start");

        int courseID = Integer.parseInt(req.getParameter("id"));
        LOG.trace(String.format("Request parameter course id = %s", courseID));

        State state  = State.valueOf(req.getParameter("state"));
        LOG.trace(String.format("Request parameter course State = %s", state.getName()));

        String name = req.getParameter("name");
        LOG.trace(String.format("Request parameter course name = %s", name));

        Map<User, Integer> students = FACADE.getUsersByCourse(courseID);

        req.getSession().setAttribute("students", students);
        req.getSession().setAttribute("name", name);
        req.getSession().setAttribute("state", state);
        req.getSession().setAttribute("id", courseID);


        LOG.debug("Command finished");
        return Paths.VIEW_COURSE_PAGE;
    }
}
