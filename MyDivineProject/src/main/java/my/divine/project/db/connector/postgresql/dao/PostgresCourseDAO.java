package my.divine.project.db.connector.postgresql.dao;

import my.divine.project.db.connector.postgresql.DAOUtils;
import my.divine.project.db.dao.CourseDAO;
import my.divine.project.model.constant.State;
import my.divine.project.model.constant.Topic;
import my.divine.project.model.entity.Course;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostgresCourseDAO  implements CourseDAO {

    private static final String INSERT_INTO_COURSES_SQL = "INSERT INTO courses (name, topic_id, teacher, start_date, end_date) VALUES(?,?,?,?,?)";

    private static final String SELECT_FROM_COURSES_BY_ID_SQL = "SELECT * FROM courses c INNER JOIN users u on c.teacher = u.login WHERE id = ?";

    private static final String SELECT_FROM_COURSES_BY_STATE_SQL = "SELECT * FROM courses c LEFT JOIN users u on c.teacher = u.login WHERE state_id = 1;";

    private static final String SELECT_ALL_COURSES_SQL = "SELECT * FROM courses c INNER JOIN users u on c.teacher = u.login";

    private static final String SELECT_FROM_COURSES_BY_TOPIC_SQL = "SELECT * FROM courses c INNER JOIN users u on c.teacher = u.login WHERE c.topic_id = ? ";

    private static final String SELECT_FROM_COURSES_BY_TEACHER_SQL = "SELECT * FROM courses c INNER JOIN users u on c.teacher = u.login WHERE u.login = ? ";

    private static final String SELECT_FROM_COURSES_BY_SORT_NAME_SQL = "SELECT * FROM courses c  INNER JOIN users u  on c.teacher = u.login WHERE state_id = 1 ORDER BY name ";

    private static final String SELECT_FROM_COURSE_BY_SORT_COUNT_SQL = "SELECT * FROM courses c  INNER JOIN users u  on c.teacher = u.login WHERE state_id = 1 ORDER BY count DESC";

    private static final String DELETE_FROM_COURSES_SQL = "DELETE FROM courses WHERE id = ?";

    private static final String UPDATE_COURSE_SQL = "UPDATE courses SET name = ?, topic_id = ?, teacher = ?, start_date = ?, end_date = ? WHERE id = ?";

    private static final String UPDATE_FIELD_TEACHER_SQL = "UPDATE  courses SET teacher = ? WHERE id = ?";

    private static final String UPDATE_FIELD_STATE_SQL = "UPDATE  courses SET state_id = ? WHERE id = ?";

    private static final String SET_FIELD_COUNT_TO_COURSE_SQL = "UPDATE  courses SET count = ? WHERE id = ?";




    private static PostgresCourseDAO postgresCourseDAO;

    private PostgresCourseDAO() {
    }

    public static PostgresCourseDAO getInstance() {
        if (postgresCourseDAO == null) {
            synchronized (PostgresUserDAO.class) {
                if (postgresCourseDAO == null) {
                    postgresCourseDAO = new PostgresCourseDAO();
                }
            }
        }
        return postgresCourseDAO;
    }

    @Override
    public boolean createCourse(Connection connection, Course course) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_COURSES_SQL)) {

            DAOUtils.fillPreparedStatement(
                    preparedStatement,
                    course.getName(),
                    course.getTopic().getId(),
                    course.getTeacher().getLogin(),
                    course.getStartDate(),
                    course.getEndDate());


            return preparedStatement.executeUpdate() > 0;
        }
    }

    @Override
    public Course getCourseByID(Connection connection, int courseID) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FROM_COURSES_BY_ID_SQL)) {
            preparedStatement.setInt(1, courseID);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                if (resultSet.next()) {
                    return DAOUtils.getCourse(resultSet);
                }
            }
        }
        return new Course();
    }

    @Override
    public List<Course> getCoursesByState(Connection connection) throws SQLException {
        List<Course> courses = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_FROM_COURSES_BY_STATE_SQL)) {
            while (resultSet.next()) {
                courses.add(DAOUtils.getCourse(resultSet));
            }
        }
        return courses;
    }

    @Override
    public List<Course> getCourses(Connection connection) throws SQLException {
        List<Course> courses = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_ALL_COURSES_SQL)) {
            while (resultSet.next()) {
                courses.add(DAOUtils.getCourse(resultSet));
            }
        }
        return courses;
    }

    @Override
    public List<Course> getCoursesBySortCount(Connection connection) throws SQLException {
        List<Course> courses = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_FROM_COURSE_BY_SORT_COUNT_SQL)) {
            while (resultSet.next()) {
                courses.add(DAOUtils.getCourse(resultSet));
            }
        }
        return courses;
    }

    @Override
    public List<Course> getCoursesByTopic(Connection connection, Topic topic) throws SQLException {
        List<Course> courses = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FROM_COURSES_BY_TOPIC_SQL)) {
            preparedStatement.setInt(1, topic.getId());
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    courses.add(DAOUtils.getCourse(resultSet));
                }
            }
            return courses;
        }
    }

    @Override
    public List<Course> getCoursesByTeacher(Connection connection, String userLogin) throws SQLException {
        List<Course> courses = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FROM_COURSES_BY_TEACHER_SQL)) {
            preparedStatement.setString(1, userLogin);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    courses.add(DAOUtils.getCourse(resultSet));
                }
            }
            return courses;
        }
    }

    @Override
    public List<Course> getCoursesBySortName(Connection connection, String sort) throws SQLException {
        List<Course> courses = new ArrayList<>();
        String sorts = " " + sort;

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_FROM_COURSES_BY_SORT_NAME_SQL + sorts)) {
            while (resultSet.next()) {
                courses.add(DAOUtils.getCourse(resultSet));
            }

        }
        return courses;
    }

    @Override
    public boolean updateCourse(Connection connection, Course course) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_COURSE_SQL)) {
            DAOUtils.fillPreparedStatement(
                    preparedStatement,
                    course.getName(),
                    course.getTopic().getId(),
                    course.getTeacher().getLogin(),
                    course.getStartDate(),
                    course.getEndDate(),
                    course.getId());
            return preparedStatement.executeUpdate() > 0;
        }

    }

    @Override
    public boolean deleteCourse(Connection connection, int courseID) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_FROM_COURSES_SQL)) {
            preparedStatement.setInt(1, courseID);
            return preparedStatement.executeUpdate() > 0;
        }
    }

    @Override
    public boolean setFieldTeacher(Connection connection, int courseID, String userLogin) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_FIELD_TEACHER_SQL)) {
            DAOUtils.fillPreparedStatement(
                    preparedStatement,
                    userLogin,
                    courseID);
            return preparedStatement.executeUpdate() > 0;
        }
    }

    @Override
    public boolean setFieldState(Connection connection, int courseID, State state) throws SQLException {
        try(PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_FIELD_STATE_SQL)){
            DAOUtils.fillPreparedStatement(
                    preparedStatement,
                    state.getId(),
                    courseID);
            return preparedStatement.executeUpdate() > 0;
        }

    }

    @Override
    public boolean setFieldCountToCourse(Connection connection, int courseID, int count) throws SQLException {
        try(PreparedStatement preparedStatement = connection.prepareStatement(SET_FIELD_COUNT_TO_COURSE_SQL)){
            DAOUtils.fillPreparedStatement(
                    preparedStatement,
                    count,
                    courseID);

            return preparedStatement.executeUpdate() > 0;
        }
    }
}
