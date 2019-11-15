package my.divine.project.db.connector.postgresql.transaction;

import my.divine.project.db.connector.DAOFactory;
import my.divine.project.db.transaction.CourseTransactionManager;
import my.divine.project.exception.ExceptionMessages;
import my.divine.project.exception.db.DBException;
import my.divine.project.model.constant.State;
import my.divine.project.model.constant.Topic;
import my.divine.project.model.entity.Course;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


public class PostgresCourseTransactionManager implements CourseTransactionManager {

    private static final Logger LOG = Logger.getLogger(PostgresCourseTransactionManager.class);

    private static PostgresCourseTransactionManager postgresCourseTransactionManager;

    private DAOFactory daoFactory;

    private PostgresCourseTransactionManager(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public static PostgresCourseTransactionManager getInstance(DAOFactory daoFactory) {
        if (postgresCourseTransactionManager == null) {
            synchronized (PostgresUserTransactionManager.class) {
                if (postgresCourseTransactionManager == null) {
                    postgresCourseTransactionManager = new PostgresCourseTransactionManager(daoFactory);
                }
            }
        }
        return postgresCourseTransactionManager;
    }
    @Override
    public boolean createCourse(Course course) throws DBException {
        Connection connection = daoFactory.getConnection();

        boolean result;

        try {
            result = daoFactory.getCourseDAO().createCourse(connection, course);

        } catch (SQLException e) {
            LOG.error(ExceptionMessages.COULD_NOT_CREATE_COURSE_MASSAGE, e);
            daoFactory.rollBack(connection);
            throw new DBException(ExceptionMessages.COULD_NOT_CREATE_COURSE_MASSAGE, e);
        }
        daoFactory.commit(connection);
        daoFactory.close(connection);
        return result;
    }

    @Override
    public Course getCourseByID(int courseID) throws DBException {
        Connection connection = daoFactory.getConnection();
       Course course;
        try {
            course = daoFactory.getCourseDAO().getCourseByID(connection, courseID);

        } catch (SQLException e) {
            LOG.error(ExceptionMessages.FAILED_TO_GET_COURSE_BY_ID_MESSAGE, e);

            throw new DBException(ExceptionMessages.FAILED_TO_GET_COURSE_BY_ID_MESSAGE, e);
        }
        daoFactory.close(connection);
        return course;
    }

    @Override
    public List<Course> getCoursesByState() throws DBException {
        Connection connection = daoFactory.getConnection();
        List<Course> courses;
        try {
            courses = daoFactory.getCourseDAO().getCoursesByState(connection);

        } catch (SQLException e) {
            LOG.error(ExceptionMessages.FAILED_TO_GET_COURSES_BY_STATE_MASSAGE, e);

            throw new DBException(ExceptionMessages.FAILED_TO_GET_COURSES_BY_STATE_MASSAGE, e);
        }
      daoFactory.close(connection);
        return courses;
    }

    @Override
    public List<Course> getCourses() throws DBException {
        Connection connection = daoFactory.getConnection();
        List<Course> courses;
        try {
            courses = daoFactory.getCourseDAO().getCourses(connection);


        } catch (SQLException e) {
            LOG.error(ExceptionMessages.FAILED_TO_GET_COURSES_MASSAGE, e);

            throw new DBException(ExceptionMessages.FAILED_TO_GET_COURSES_MASSAGE, e);
        }
        daoFactory.close(connection);
        return courses;
    }

    @Override
    public List<Course> getCoursesByTopic(Topic topic) throws DBException {
        Connection connection = daoFactory.getConnection();
        List<Course> courses;
        try {
            courses = daoFactory.getCourseDAO().getCoursesByTopic(connection, topic);

        } catch (SQLException e) {
            LOG.error(ExceptionMessages.FAILED_TO_GET_COURSES_BY_TOPIC_MASSAGE, e);

            throw new DBException(ExceptionMessages.FAILED_TO_GET_COURSES_BY_TOPIC_MASSAGE, e);
        }
        daoFactory.close(connection);
        return courses;
    }

    @Override
    public List<Course> getCoursesByTeacher(String userLogin) throws DBException {
        Connection connection = daoFactory.getConnection();
        List<Course> courses;
        try {
            courses = daoFactory.getCourseDAO().getCoursesByTeacher(connection, userLogin);

        } catch (SQLException e) {
            LOG.error(ExceptionMessages.FAILED_TO_GET_COURSES_BY_TEACHER_MASSAGE, e);

            throw new DBException(ExceptionMessages.FAILED_TO_GET_COURSES_BY_TEACHER_MASSAGE, e);
        }
        daoFactory.close(connection);
        return courses;
    }

    @Override
    public List<Course> getCoursesBySortName(String sort) throws DBException {
        Connection connection = daoFactory.getConnection();
        List<Course> courses;
        try {
            courses = daoFactory.getCourseDAO().getCoursesBySortName(connection, sort);

        } catch (SQLException e) {
            LOG.error(ExceptionMessages.FAILED_TO_GET_COURSES_BY_SORT_MASSAGE, e);

            throw new DBException(ExceptionMessages.FAILED_TO_GET_COURSES_BY_SORT_MASSAGE, e);
        }
        daoFactory.close(connection);
        return courses;
    }

    @Override
    public boolean updateCourse(Course course) throws DBException {
        Connection connection = daoFactory.getConnection();

        boolean result;

        try {
            result = daoFactory.getCourseDAO().updateCourse(connection, course);

        } catch (SQLException e) {
            LOG.error(ExceptionMessages.COULD_NOT_UPDATE_COURSE_MASSAGE, e);
            daoFactory.rollBack(connection);
            throw new DBException(ExceptionMessages.COULD_NOT_UPDATE_COURSE_MASSAGE, e);
        }
        daoFactory.commit(connection);
        daoFactory.close(connection);
        return result;
    }

    @Override
    public boolean deleteCourse(int courseID) throws DBException {
        Connection connection = daoFactory.getConnection();

        boolean result;

        try {
            result = daoFactory.getCourseDAO().deleteCourse(connection, courseID);

        } catch (SQLException e) {
            LOG.error(ExceptionMessages.COULD_NOT_DELETE_COURSE_MASSAGE, e);
            daoFactory.rollBack(connection);
            throw new DBException(ExceptionMessages.COULD_NOT_DELETE_COURSE_MASSAGE, e);
        }
        daoFactory.commit(connection);
        daoFactory.close(connection);
        return result;
    }

    @Override
    public boolean setFieldTeacher(int courseID, String userLogin) throws DBException {
        Connection connection = daoFactory.getConnection();
        boolean result;

        try {
            result = daoFactory.getCourseDAO().setFieldTeacher(connection, courseID, userLogin);


        } catch (SQLException e) {
            LOG.error(ExceptionMessages.COULD_NOT_SET_COURSE_FIELD_TEACHER_MASSAGE, e);
            daoFactory.rollBack(connection);
            throw new DBException(ExceptionMessages.COULD_NOT_SET_COURSE_FIELD_TEACHER_MASSAGE, e);
        }
        daoFactory.commit(connection);
        daoFactory.close(connection);
        return result;
    }

    @Override
    public boolean setFieldState(int courseID, State state) throws DBException {
        Connection connection = daoFactory.getConnection();
        boolean result;

        try {
            result = daoFactory.getCourseDAO().setFieldState(connection, courseID, state);


        } catch (SQLException e) {
            LOG.error(ExceptionMessages.COULD_NOT_SET_COURSE_FIELD_STATE_MASSAGE, e);
            daoFactory.rollBack(connection);
            throw new DBException(ExceptionMessages.COULD_NOT_SET_COURSE_FIELD_STATE_MASSAGE, e);
        }
        daoFactory.commit(connection);
        daoFactory.close(connection);
        return result;
    }



    @Override
    public List<Course> getCoursesBySortCount() throws DBException {
        Connection connection = daoFactory.getConnection();
        List<Course> courses;
        try {
            courses = daoFactory.getCourseDAO().getCoursesBySortCount(connection);


        } catch (SQLException e) {
            LOG.error(ExceptionMessages.FAILED_TO_GET_COURSES_BY_COUNT_MASSAGE, e);

            throw new DBException(ExceptionMessages.FAILED_TO_GET_COURSES_BY_COUNT_MASSAGE, e);
        }
        daoFactory.close(connection);
        return courses;
    }

    @Override
    public boolean setFieldCountToCourse(int courseID, int count) throws DBException {
        Connection connection = daoFactory.getConnection();
        boolean result;

        try {
            result = daoFactory.getCourseDAO().setFieldCountToCourse(connection, courseID, count);


        } catch (SQLException e) {
            LOG.error(ExceptionMessages.COULD_NOT_SET_COUNT_MASSAGE, e);
            daoFactory.rollBack(connection);
            throw new DBException(ExceptionMessages.COULD_NOT_SET_COUNT_MASSAGE, e);
        }
        daoFactory.commit(connection);
        daoFactory.close(connection);
        return result;


    }
}
