package my.divine.project.db.connector.postgresql;

import my.divine.project.model.constant.Role;
import my.divine.project.model.constant.State;
import my.divine.project.model.constant.Topic;
import my.divine.project.model.entity.Course;
import my.divine.project.model.entity.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOUtils {

    private DAOUtils() {
    }

    /**
     * Fill prepared statement from database query
     * @param preparedStatement
     * @param args
     * @throws SQLException
     */
    public static void fillPreparedStatement(PreparedStatement preparedStatement, Object... args)
            throws SQLException {

        int counter = 1;

        for (Object arg : args) {
            preparedStatement.setObject(counter++, arg);
        }
    }

    /**
     * Getting user from resultSet  and filling in his fields
     * @param resultSet
     * @return User from db
     * @throws SQLException
     */
    public static User getUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setLogin(resultSet.getString("login"));
        user.setPassword(resultSet.getString("password"));
        user.setFullName(resultSet.getString("fullName"));
        user.setBlocked(resultSet.getBoolean("blocked"));


        user.setRole(Role.getRoleById(resultSet.getInt("role_id")));
        return user;
    }

    /**
     * Getting course from resultSet  and filling in his fields
     * @param resultSet
     * @return Course from db
     * @throws SQLException
     */
    public static Course getCourse (ResultSet resultSet)throws SQLException{
        Course course = new Course();

        course.setId(resultSet.getInt("id"));
        course.setName(resultSet.getString("name"));
        course.setTopic(Topic.getTopicById(resultSet.getInt("topic_id")));
        course.setTeacher(getUser(resultSet));
        course.setState(State.getStateById(resultSet.getInt("state_id")));
        course.setStartDate(resultSet.getDate("start_date"));
        course.setEndDate(resultSet.getDate("end_date"));
        course.setCount(resultSet.getInt("count"));
        return course;
    }
}
