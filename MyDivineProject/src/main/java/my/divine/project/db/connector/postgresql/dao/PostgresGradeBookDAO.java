package my.divine.project.db.connector.postgresql.dao;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import my.divine.project.db.connector.postgresql.DAOUtils;
import my.divine.project.db.dao.GradeBookDAO;
import my.divine.project.model.constant.State;
import my.divine.project.model.entity.Course;
import my.divine.project.model.entity.User;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class PostgresGradeBookDAO implements GradeBookDAO {

    private static final String SET_USER_TO_COURSE_SQL = "INSERT INTO grade_book (user_login, course_id) VALUES (?,?)";

    private static final String SELECT_ALL_COURSES_IN_USER_SQL = "SELECT  * FROM courses c INNER JOIN  grade_book gb on c.id = gb.course_id INNER JOIN users u on c.teacher = u.login  WHERE gb.user_login =? AND c.state_id = ?";

    private static final String SELECT_ALL_USERS_FROM_COURSE_SQL = "SELECT * FROM users u INNER JOIN   grade_book gb on u.login = gb.user_login   WHERE course_id = ? ORDER BY assessment ASC";

    private static final String SELECT_USERS_COURSE_RESULT_SQL = "SELECT * FROM users u INNER JOIN   grade_book gb on u.login = gb.user_login   WHERE course_id = ? ";

    private static final String SELECT_COUNT_USER_FROM_COURSE_SQL = "SELECT count(*) FROM grade_book WHERE course_id = ?";

    private static final String CHECK_USER_REG_SQL = "SELECT * FROM grade_book WHERE course_id = ? AND user_login = ?";

    private static final String DELETE_USER_FROM_COURSE_SQL = "DELETE FROM grade_book WHERE user_login = ? AND course_id = ?";

    private static final String SET_ASSESSMENT_TO_USER_BY_COURSE_SQL = "UPDATE grade_book SET assessment = ?, result = ? WHERE user_login = ? AND course_id = ?";




    private static PostgresGradeBookDAO postgresGradeBookDAO;

    private PostgresGradeBookDAO (){}

    public static PostgresGradeBookDAO getInstance(){
        if (postgresGradeBookDAO == null){
            synchronized (PostgresGradeBookDAO.class){
                if (postgresGradeBookDAO == null){
                    postgresGradeBookDAO = new PostgresGradeBookDAO();
                }
            }
        }
        return postgresGradeBookDAO;
    }

    @Override
    public boolean setUserToCourse(Connection connection, String login, int courseID) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SET_USER_TO_COURSE_SQL)) {
            DAOUtils.fillPreparedStatement(
                    preparedStatement,
                    login,
                    courseID);
            return preparedStatement.executeUpdate() > 0;
        }
    }

    @Override
    public Map<User, Integer> getUsersByCourse(Connection connection, int courseID) throws SQLException {

       Map<User, Integer> gradeBookForTeacher = new HashMap<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS_FROM_COURSE_SQL)) {
            preparedStatement.setInt(1, courseID);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    gradeBookForTeacher.put( DAOUtils.getUser(resultSet), resultSet.getInt("assessment"));
                }

            }

            return gradeBookForTeacher;
        }
    }

    @Override
    public Map<Course, Integer> getCoursesByUserLogin(Connection connection, String userLogin, State state) throws SQLException {

      Map<Course, Integer> gradeBookForStudent = new HashMap<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_COURSES_IN_USER_SQL)) {
            preparedStatement.setString(1, userLogin);
            preparedStatement.setInt(2, state.getId());
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    gradeBookForStudent.put( DAOUtils.getCourse(resultSet), resultSet.getInt("assessment"));
                }

            }

            return gradeBookForStudent;
        }
    }

    @Override
    public Map<User, Boolean> getUsersCourseResult(Connection connection, int courseId) throws SQLException {
        Map<User, Boolean> gradeBookForTeacher = new HashMap<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USERS_COURSE_RESULT_SQL)) {
            preparedStatement.setInt(1, courseId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    gradeBookForTeacher.put( DAOUtils.getUser(resultSet),
                            resultSet.getBoolean("result"));
                }

            }

            return gradeBookForTeacher;
        }
    }

    @Override
    public boolean checkUserReg(Connection connection, String login, int courseID) throws SQLException {

        boolean result = false;

        try (PreparedStatement preparedStatement = connection.prepareStatement(CHECK_USER_REG_SQL)) {
            preparedStatement.setInt(1, courseID);
            preparedStatement.setString(2, login);
            try (ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()){
                    result = true;
                }
            }
        }
        return result;

    }

    @Override
    public Integer getQuantityStudentFromCourse(Connection connection, int courseID) throws SQLException {
        int result = 0;

        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_COUNT_USER_FROM_COURSE_SQL)) {
            preparedStatement.setInt(1, courseID);
            try (ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()){
                    result = resultSet.getInt("count");
                    return result;
                }
            }


        }
        return result;

    }

    @Override
    public boolean deleteUserFromCourse(Connection connection, String userLogin, int courseID) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER_FROM_COURSE_SQL)) {
            preparedStatement.setString(1, userLogin);
            preparedStatement.setInt(2, courseID);
            return preparedStatement.executeUpdate() > 0;
        }
    }

    @Override
    public boolean setAssessmentToUserByCourse(Connection connection, String userLogin, int courseID, int assessment,  boolean result) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SET_ASSESSMENT_TO_USER_BY_COURSE_SQL)) {
            DAOUtils.fillPreparedStatement(
                    preparedStatement,
                    assessment,
                    result,
                    userLogin,
                    courseID);
            return preparedStatement.executeUpdate() > 0;
        }
    }
}
