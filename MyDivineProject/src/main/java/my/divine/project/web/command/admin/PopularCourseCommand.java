package my.divine.project.web.command.admin;

import my.divine.project.db.Facade;
import my.divine.project.db.TransactionManagerFacade;
import my.divine.project.db.connector.postgresql.PostgresService;
import my.divine.project.exception.AppException;
import my.divine.project.model.entity.Course;
import my.divine.project.web.Paths;
import my.divine.project.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PopularCourseCommand extends Command {
    private static final Facade FACADE =
            TransactionManagerFacade.getInstance(PostgresService.getInstance());
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, AppException {

        List<Course> courses = FACADE.getCoursesBySortCount();
        List<Course> popular = new ArrayList<>();

        for (int i = 0;i < 3; i++ ){
            popular.add(courses.get(i));
        }


        req.getSession().setAttribute("popular", popular);
        return  Paths.POPULAR_COURSES_PAGE;
    }
}
