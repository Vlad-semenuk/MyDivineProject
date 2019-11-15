package my.divine.project.web.command.admin;

import my.divine.project.db.Facade;
import my.divine.project.db.TransactionManagerFacade;
import my.divine.project.db.connector.postgresql.PostgresService;
import my.divine.project.exception.AppException;
import my.divine.project.web.Paths;
import my.divine.project.web.command.Command;
import my.divine.project.web.command.common.RegCommandCommand;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChoiceCourseEditCommand extends Command {

    private static final Logger LOG = Logger.getLogger(ChoiceCourseEditCommand.class);


    /**
     * Editing the selected course
     * @param req
     * @param resp
     * @return Path to edit course page
     * @throws IOException
     * @throws AppException
     */
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, AppException {
        LOG.debug("Command starts");

        int courseID = Integer.parseInt(req.getParameter("id"));
        System.out.println("course id = " + courseID);
        LOG.debug("Command finished");
        return Paths.EDIT_COURSES_PAGE;
    }
}
