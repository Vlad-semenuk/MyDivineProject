package my.divine.project.db;

import com.google.common.collect.Table;
import my.divine.project.exception.db.DBException;
import my.divine.project.model.constant.Role;
import my.divine.project.model.constant.State;
import my.divine.project.model.constant.Topic;
import my.divine.project.model.entity.Course;
import my.divine.project.model.entity.User;

import java.util.List;
import java.util.Map;

/**
 * Facade for  all transaction
 */
public interface Facade {

    //for user

    /**
     * create user
     * @param user
     * @return result transaction
     * @throws DBException
     */
    boolean createUser (User user)throws DBException;

    /**
     * Get user by login
     * @param login
     * @return selected user
     * @throws DBException
     */
    User getUserByLogin (String login) throws DBException;


    User getUserByLoginAndPassword (String login, String password)throws DBException;

    boolean setUserFieldRoleByUserLogin(String userLogin) throws DBException;

    boolean updateUserFullName (String login,String fullName) throws DBException;

    List<User> getAllTeacher() throws DBException;

    boolean setUserFieldBlockedByUserLogin(boolean blocked, String userLogin) throws DBException;




    //for course
    boolean createCourse (Course course)throws DBException;

    Course getCourseByID (int courseID) throws DBException;

    List<Course> getCoursesByState ()throws DBException;

    List <Course> getCourses() throws DBException;

    List <Course> getCoursesByTopic (Topic topic)throws DBException;

    List <Course> getCoursesByTeacher (String userLogin)throws DBException;

    List <Course> getCoursesBySortName (String sort ) throws DBException;

    List <Course> getCoursesBySortCount () throws DBException;

    boolean updateCourse (Course course)throws DBException;

    boolean deleteCourse (int courseID)throws DBException;

    boolean setFieldTeacher (int courseID, String userLogin)throws DBException;

    boolean setFieldState (int courseID, State state) throws DBException;

    boolean setFieldCountToCourse( int courseID, int count) throws DBException;




    //for grade book
    boolean setUserToCourse (String login, int courseID)throws DBException;

    Map<User,Integer> getUsersByCourse (int courseID) throws DBException;

   Map<User, Boolean> getUsersCourseResult(int courseId) throws DBException;


    boolean checkUserReg(String login, int courseID) throws DBException;

    Map<Course, Integer> getCoursesByUserLogin (String userLogin,State state)throws DBException;

    Integer getQuantityStudentFromCourse (int courseID)throws DBException;

    boolean deleteUserFromCourse (String userLogin, int courseID) throws DBException;

    boolean setAssessmentToUserByCourse (String userLogin, int courseID, int assessment, boolean result)throws DBException;





}
