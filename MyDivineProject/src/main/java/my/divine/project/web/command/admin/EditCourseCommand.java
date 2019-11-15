package my.divine.project.web.command.admin;

import my.divine.project.db.Facade;
import my.divine.project.db.TransactionManagerFacade;
import my.divine.project.db.connector.postgresql.PostgresService;
import my.divine.project.exception.AppException;
import my.divine.project.model.entity.Course;
import my.divine.project.web.Paths;
import my.divine.project.web.command.Command;

import my.divine.project.web.command.commandUtils.CommandUtils;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditCourseCommand extends Command {

    private static final Logger LOG = Logger.getLogger(EditCourseCommand.class);

    private static final Facade FACADE =
            TransactionManagerFacade.getInstance(PostgresService.getInstance());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, AppException {
        LOG.debug("Command starts");


        Course course = CommandUtils.getCourseFromRequest(req);

        LOG.trace(String.format("Update course by id --> %s", course.getId()));

        FACADE.updateCourse(course);



        LOG.debug("Command finished");
        return Paths.PERSONAL_PAGE;
    }
}
