package my.divine.project.web.command.teacher;

import my.divine.project.db.Facade;
import my.divine.project.db.TransactionManagerFacade;
import my.divine.project.db.connector.postgresql.PostgresService;
import my.divine.project.exception.AppException;
import my.divine.project.model.entity.User;
import my.divine.project.web.Paths;
import my.divine.project.web.command.Command;
import my.divine.project.web.command.common.RegCommandCommand;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class SetAssessmentCommand extends Command {
    private static final Logger LOG = Logger.getLogger(SetAssessmentCommand.class);

    private static final Facade FACADE =
            TransactionManagerFacade.getInstance(PostgresService.getInstance());



    /**
     * Graduation by the teacher after completion of the course
     * @param req
     * @param resp
     * @return Path to view course of teacher page
     * @throws IOException
     * @throws AppException
     */

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, AppException {
        LOG.debug("Command start");

//        int courseID = Integer.parseInt(req.getParameter("id"));
        int courseID = (Integer) req.getSession().getAttribute("id");


        LOG.trace(String.format("Request parameter course id = %s", courseID));

        String login = req.getParameter("login");
        LOG.trace(String.format("Request parameter user login = %s", login));

        int assessment =  Integer.parseInt(req.getParameter("assessment"));
        LOG.trace(String.format("Request parameter assessment = %s", assessment));

        if(assessment >= 50){
            System.out.println("pass");
            FACADE.setAssessmentToUserByCourse(login, courseID, assessment, true);
        } else if(assessment < 50){
            System.out.println("failed");
            FACADE.setAssessmentToUserByCourse(login, courseID, assessment, false);
        }




        int pass = 0;
        int failed = 0;

       Map<User, Boolean> userResult =  FACADE.getUsersCourseResult(courseID);



        for (Boolean b : userResult.values()){
            if (b){
                pass++;
            } else {
                failed = userResult.size() - (userResult.size() - pass);
            }
        }



        Map<User, Integer> students = FACADE.getUsersByCourse(courseID);

        req.getSession().setAttribute("students", students);
        req.getSession().setAttribute("pass", pass);
        req.getSession().setAttribute("failed", failed);


        LOG.debug("Command finished");
        return Paths.VIEW_COURSE_PAGE;
    }
}
