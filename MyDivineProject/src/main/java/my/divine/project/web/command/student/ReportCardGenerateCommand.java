package my.divine.project.web.command.student;

import com.itextpdf.text.DocumentException;
import my.divine.project.db.Facade;
import my.divine.project.db.TransactionManagerFacade;
import my.divine.project.db.connector.postgresql.PostgresService;
import my.divine.project.exception.AppException;
import my.divine.project.exception.ExceptionMessages;
import my.divine.project.model.entity.Course;
import my.divine.project.model.entity.User;
import my.divine.project.web.Paths;
import my.divine.project.web.command.Command;
import my.divine.project.web.report.ReportCardGenerator;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ReportCardGenerateCommand extends Command {
    private static final Logger LOG = Logger.getLogger(ReportCardGenerateCommand.class);

    private static final Facade FACADE =
            TransactionManagerFacade.getInstance(PostgresService.getInstance());

    /**
     * Generating a pdf file displaying information
     * on completed courses for the user
     * @param req
     * @param resp
     * @return PDF file
     * @throws AppException
     */
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws AppException {
        LOG.debug("Command starts");

        int courseID = Integer.parseInt(req.getParameter("id"));

        String assessment = req.getParameter("assessment");
        Course course = FACADE.getCourseByID(courseID);
        LOG.trace(String.format("Request parameter: course --> %s", courseID));
        User user = (User) req.getSession().getAttribute("user");
        LOG.trace(String.format("Session attribute: user --> %s", user.getFullName()));
        String realPath = req.getServletContext().getRealPath(Paths.PDF_REPORTS_PATH);
        LOG.trace(String.format("Context path --> %s", realPath));

        try {
            ReportCardGenerator.createTicket(realPath, user, course, assessment);
        } catch (IOException | DocumentException e) {
            throw new AppException(ExceptionMessages.COULD_NOT_GENERATE_REPORT_CARD_MESSAGE);
        }

        LOG.debug("Command finished");
        return Paths.REPORT_CARD_SERVLET;
    }
}
