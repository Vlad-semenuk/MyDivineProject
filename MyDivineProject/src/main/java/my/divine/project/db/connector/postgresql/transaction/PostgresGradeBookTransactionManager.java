package my.divine.project.db.connector.postgresql.transaction;

import com.google.common.collect.Table;
import my.divine.project.db.connector.DAOFactory;
import my.divine.project.db.transaction.GradeBookTransactionManager;
import my.divine.project.exception.ExceptionMessages;
import my.divine.project.exception.db.DBException;
import my.divine.project.model.constant.State;
import my.divine.project.model.entity.Course;
import my.divine.project.model.entity.User;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

public class PostgresGradeBookTransactionManager implements GradeBookTransactionManager {
    private static final Logger LOG = Logger.getLogger(PostgresGradeBookTransactionManager.class);

    private static PostgresGradeBookTransactionManager postgresGradeBookTransactionManager;

    private DAOFactory daoFactory;

    private PostgresGradeBookTransactionManager(DAOFactory daoFactory){
        this.daoFactory = daoFactory;
    }

    public static PostgresGradeBookTransactionManager getInstance (DAOFactory daoFactory){
        if (postgresGradeBookTransactionManager == null) {
            synchronized (PostgresGradeBookTransactionManager.class) {
                if (postgresGradeBookTransactionManager == null) {
                    postgresGradeBookTransactionManager = new PostgresGradeBookTransactionManager(daoFactory);
                }
            }
        }
        return postgresGradeBookTransactionManager;
    }

    @Override
    public boolean setUserToCourse(String login, int courseID) throws DBException {
        Connection connection = daoFactory.getConnection();
        boolean result;

        try {
            result = daoFactory.getGradeBookDAO().setUserToCourse(connection, login, courseID);

        } catch (SQLException e) {
            LOG.error(ExceptionMessages.COULD_NOT_SET_USER_TO_COURSE_MASSAGE, e);
            daoFactory.rollBack(connection);
            throw new DBException(ExceptionMessages.COULD_NOT_SET_USER_TO_COURSE_MASSAGE, e);
        }
        daoFactory.commit(connection);
        daoFactory.close(connection);
        return result;
    }

    @Override
    public Map< User, Integer> getUsersByCourse(int courseID) throws DBException {
        Connection connection = daoFactory.getConnection();
        Map< User, Integer> gradeBookForTeacher;
        try {
            gradeBookForTeacher = daoFactory.getGradeBookDAO().getUsersByCourse(connection, courseID);
        } catch (SQLException e){
            LOG.error(ExceptionMessages.FAILED_TO_GET_ALL_USERS_FROM_COURSE_MASSAGE, e);
            throw new DBException(ExceptionMessages.FAILED_TO_GET_ALL_USERS_FROM_COURSE_MASSAGE, e);
        }
        daoFactory.close(connection);
        return gradeBookForTeacher;
    }

    @Override
    public Map<Course, Integer> getCoursesByUserLogin(String userLogin, State state) throws DBException {
        Connection connection = daoFactory.getConnection();
        Map< Course, Integer> gradeBookForStudent;
        try {
            gradeBookForStudent = daoFactory.getGradeBookDAO().getCoursesByUserLogin(connection, userLogin, state);
        } catch (SQLException e){
            LOG.error(ExceptionMessages.FAILED_TO_GET_ALL_COURSE_IN_USER_MASSAGE, e);
            throw new DBException(ExceptionMessages.FAILED_TO_GET_ALL_COURSE_IN_USER_MASSAGE, e);
        }
        daoFactory.close(connection);
        return gradeBookForStudent;
    }

    @Override
    public Map<User, Boolean> getUsersCourseResult(int courseId) throws DBException {
        Connection connection = daoFactory.getConnection();
        Map< User, Boolean> gradeBookForTeacher;
        try {
            gradeBookForTeacher = daoFactory.getGradeBookDAO().getUsersCourseResult(connection, courseId);
        } catch (SQLException e){
            LOG.error(ExceptionMessages.FAILED_TO_GET_ALL_USERS_FROM_COURSE_MASSAGE, e);
            throw new DBException(ExceptionMessages.FAILED_TO_GET_ALL_USERS_FROM_COURSE_MASSAGE, e);
        }
        daoFactory.close(connection);
        return gradeBookForTeacher;
    }

    @Override
    public boolean checkUserReg( String login, int courseID) throws DBException {
        Connection connection = daoFactory.getConnection();
        boolean result;
        try {
            result = daoFactory.getGradeBookDAO().checkUserReg(connection, login, courseID);
        } catch (SQLException e){
            LOG.error(ExceptionMessages.USER_IS_ALREADY_REGISTERED_FOR_COURSE_MASSAGE, e);
            throw new DBException(ExceptionMessages.USER_IS_ALREADY_REGISTERED_FOR_COURSE_MASSAGE, e);
        }
        daoFactory.close(connection);
        return result;
    }

    @Override
    public Integer getQuantityStudentFromCourse(int courseID) throws DBException {
        Connection connection = daoFactory.getConnection();
        int result;
        try {
            result = daoFactory.getGradeBookDAO().getQuantityStudentFromCourse(connection, courseID);

        } catch (SQLException e) {
            LOG.error(ExceptionMessages.FAILED_TO_GET_COUNT_STUDENTS_FROM_COURSE_MASSAGE, e);
            throw new DBException(ExceptionMessages.FAILED_TO_GET_COUNT_STUDENTS_FROM_COURSE_MASSAGE, e);
        }
        daoFactory.close(connection);
        return result;
    }

    @Override
    public boolean deleteUserFromCourse(String userLogin, int courseID) throws DBException {
        Connection connection = daoFactory.getConnection();
        boolean result;

        try {
            result = daoFactory.getGradeBookDAO().deleteUserFromCourse(connection, userLogin, courseID);

        } catch (SQLException e) {
            LOG.error(ExceptionMessages.COULD_NOT_DELETE_USER_FROM_COURSE_MASSAGE, e);
            daoFactory.rollBack(connection);
            throw new DBException(ExceptionMessages.COULD_NOT_DELETE_USER_FROM_COURSE_MASSAGE, e);
        }
        daoFactory.commit(connection);
        daoFactory.close(connection);
        return result;
    }

    @Override
    public boolean setAssessmentToUserByCourse(String userLogin, int courseID, int assessment, boolean result) throws DBException {
        Connection connection = daoFactory.getConnection();
        boolean result2;

        try {
            result2 = daoFactory.getGradeBookDAO().setAssessmentToUserByCourse(connection, userLogin, courseID, assessment, result);

        } catch (SQLException e) {
            LOG.error(ExceptionMessages.COULD_NOT_SET_ASSESSMENT_TO_STUDENT_BY_COURSE_MASSAGE, e);
            daoFactory.rollBack(connection);
            throw new DBException(ExceptionMessages.COULD_NOT_SET_ASSESSMENT_TO_STUDENT_BY_COURSE_MASSAGE, e);
        }
        daoFactory.commit(connection);
        daoFactory.close(connection);
        return result2;
    }


}
