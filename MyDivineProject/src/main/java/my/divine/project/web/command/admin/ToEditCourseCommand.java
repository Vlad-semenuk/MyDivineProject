package my.divine.project.web.command.admin;

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

public class ToEditCourseCommand extends Command {

    private static final Logger LOG = Logger.getLogger(ToEditCourseCommand.class);

    private static final Facade FACADE =
            TransactionManagerFacade.getInstance(PostgresService.getInstance());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, AppException {
        LOG.debug("Command starts");

        int courseID = Integer.parseInt(req.getParameter("id"));
        LOG.trace(String.format("Course id from request --> %s", courseID));

        Course course = FACADE.getCourseByID(courseID);


        req.getSession().setAttribute("course", course);


        LOG.debug("Command finished");
        return Paths.EDIT_COURSES_PAGE;
    }
}
