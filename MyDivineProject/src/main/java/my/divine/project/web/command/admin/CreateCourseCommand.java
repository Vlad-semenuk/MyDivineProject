package my.divine.project.web.command.admin;

import my.divine.project.db.Facade;
import my.divine.project.db.TransactionManagerFacade;
import my.divine.project.db.connector.postgresql.PostgresService;
import my.divine.project.exception.AppException;
import my.divine.project.model.entity.Course;
import my.divine.project.web.Paths;
import my.divine.project.web.command.Command;
import my.divine.project.web.command.commandUtils.CommandUtils;
import my.divine.project.web.command.common.RegCommandCommand;
import my.divine.project.web.validator.Validator;
import my.divine.project.web.validator.common.DateValidator;
import my.divine.project.web.validator.course.NameValidator;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateCourseCommand extends Command {

    private static final Logger LOG = Logger.getLogger(CreateCourseCommand.class);

    private static final Facade FACADE =
            TransactionManagerFacade.getInstance(PostgresService.getInstance());


    /**
     * Created a new course
     * @param req
     * @param resp
     * @return Path to create course page
     * @throws IOException
     * @throws AppException
     */

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, AppException {
        LOG.debug("Command start");

        Course course = CommandUtils.getCourseFromRequest(req);


        LOG.trace(String.format("Create course %s", course.getName()));

        FACADE.createCourse(course);



        LOG.debug("Command finished");
        return Paths.PERSONAL_PAGE;
    }
}
