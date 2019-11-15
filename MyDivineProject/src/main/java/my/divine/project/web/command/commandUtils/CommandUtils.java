package my.divine.project.web.command.commandUtils;

import my.divine.project.db.Facade;
import my.divine.project.db.TransactionManagerFacade;
import my.divine.project.db.connector.postgresql.PostgresService;
import my.divine.project.exception.AppException;
import my.divine.project.exception.db.DBException;
import my.divine.project.exception.validate.ValidateException;
import my.divine.project.model.constant.State;
import my.divine.project.model.constant.Topic;
import my.divine.project.model.entity.Course;
import my.divine.project.model.entity.User;
import my.divine.project.model.utility.ScheduledSetState;
import my.divine.project.web.validator.Validator;
import my.divine.project.web.validator.common.DateValidator;
import my.divine.project.web.validator.course.NameValidator;
import my.divine.project.web.validator.user.UserValidator;
import org.apache.log4j.Logger;


import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DataBindingException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class CommandUtils {


    private static final Validator<String> DATE_VALIDATOR = DateValidator.getInstance();

    private static final Validator<User> USER_VALIDATOR = UserValidator.getInstance();

    private static final Validator<String> COURSE_NAME_VALIDATOR = NameValidator.getInstance();

    private static final Facade FACADE =
            TransactionManagerFacade.getInstance(PostgresService.getInstance());

    private CommandUtils() {
    }

    public static User getUserFromRequest(HttpServletRequest req) throws ValidateException {

        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String fullName = req.getParameter("fullName");

        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        user.setFullName(fullName);

        USER_VALIDATOR.validate(user);

        return user;

    }

    public static Course getCourseFromRequest(HttpServletRequest req) throws ValidateException, AppException {


        Course course = new Course();
        int courseID = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("courseName");
        User teacher = getUserByLogin(req.getParameter("teacher"));
        Topic topic = Topic.valueOf(req.getParameter("topics"));
        String startDateStr = req.getParameter("startDate");
        String endDateStr = req.getParameter("endDate");


        DATE_VALIDATOR.validate(startDateStr);
        DATE_VALIDATOR.validate(endDateStr);
        COURSE_NAME_VALIDATOR.validate(name);

        Date startDate = Date.valueOf(startDateStr);
        Date endDate = Date.valueOf(endDateStr);

        course.setId(courseID);
        course.setName(name);
        course.setTopic(topic);
        course.setTeacher(teacher);
        course.setStartDate(startDate);
        course.setEndDate(endDate);


        return course;
    }

    /**
     * Get list to all topics
     * @return Topics list
     */
    public static List<Topic> getAllTopics() {
        List<Topic> topics = new ArrayList<>();
        for (int i = 1; i <= 8; i++) {
            topics.add(Topic.getTopicById(i));
        }
        return topics;


    }

    private static User getUserByLogin(String login) throws DBException {

        return FACADE.getUserByLogin(login);
    }

    public static void setCount(List<Course> courses) throws AppException {
        int count;

        for (int i = 0; i < courses.size(); i++) {
            count = FACADE.getQuantityStudentFromCourse(courses.get(i).getId());

            FACADE.setFieldCountToCourse(courses.get(i).getId(), count);
            courses.get(i).setCount(count);

        }


    }


}
