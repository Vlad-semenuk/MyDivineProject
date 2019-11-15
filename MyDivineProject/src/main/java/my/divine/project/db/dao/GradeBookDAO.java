package my.divine.project.db.dao;



import my.divine.project.exception.db.DBException;
import my.divine.project.model.constant.State;
import my.divine.project.model.entity.Course;
import my.divine.project.model.entity.User;

import java.sql.Connection;

import java.sql.SQLException;
import java.util.Map;

public interface GradeBookDAO {

     boolean setUserToCourse (Connection connection, String login, int courseID) throws SQLException;

     Map< User,Integer> getUsersByCourse (Connection connection, int courseID) throws SQLException;

    Map<Course, Integer> getCoursesByUserLogin (Connection connection, String userLogin, State state) throws SQLException;

    Map<User, Boolean> getUsersCourseResult(Connection connection,int courseId) throws SQLException;


    boolean checkUserReg(Connection connection, String login, int courseID) throws SQLException;

     Integer getQuantityStudentFromCourse (Connection connection, int courseID) throws SQLException;

     boolean deleteUserFromCourse (Connection connection, String userLogin, int courseID) throws SQLException;

    boolean setAssessmentToUserByCourse (Connection connection, String userLogin, int courseID, int assessment, boolean result)throws SQLException;


}
