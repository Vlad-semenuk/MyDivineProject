package my.divine.project.db.dao;


import my.divine.project.exception.db.DBException;
import my.divine.project.model.constant.State;
import my.divine.project.model.constant.Topic;
import my.divine.project.model.entity.Course;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;



public interface CourseDAO {

    boolean createCourse(Connection connection, Course course) throws SQLException;

    Course getCourseByID (Connection connection, int courseID) throws SQLException;

    List<Course> getCoursesByState(Connection connection) throws SQLException;

    List<Course> getCourses(Connection connection) throws SQLException;

    List<Course> getCoursesBySortCount(Connection connection) throws SQLException;

    List<Course> getCoursesByTopic(Connection connection, Topic topic) throws SQLException;

    List<Course> getCoursesByTeacher(Connection connection, String userLogin) throws SQLException;

    List<Course> getCoursesBySortName(Connection connection, String sort) throws SQLException;

    boolean updateCourse(Connection connection, Course course) throws SQLException;

    boolean deleteCourse(Connection connection, int courseID) throws SQLException;

    boolean setFieldTeacher(Connection connection, int courseID, String userLogin) throws SQLException;

    boolean setFieldState(Connection connection, int courseID, State state) throws SQLException;

    boolean setFieldCountToCourse(Connection connection, int courseID, int count) throws SQLException;


}
