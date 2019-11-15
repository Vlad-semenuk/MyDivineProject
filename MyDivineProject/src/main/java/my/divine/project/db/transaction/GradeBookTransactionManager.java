package my.divine.project.db.transaction;

import com.google.common.collect.Table;
import my.divine.project.exception.db.DBException;
import my.divine.project.model.constant.State;
import my.divine.project.model.entity.Course;
import my.divine.project.model.entity.User;

import java.util.List;
import java.util.Map;

/**
 * Created by Semenuk Vladislav
 */


public interface GradeBookTransactionManager {

    boolean setUserToCourse (String login, int courseID) throws DBException;

    Map< User,Integer> getUsersByCourse (int courseID) throws DBException;

    Map<Course, Integer> getCoursesByUserLogin (String userLogin, State state) throws DBException;

    Map<User, Boolean> getUsersCourseResult(int courseId) throws DBException;


    boolean checkUserReg(String login, int courseID) throws DBException;

    Integer getQuantityStudentFromCourse (int courseID)throws DBException;

    boolean deleteUserFromCourse (String userLogin, int courseID) throws DBException;

    boolean setAssessmentToUserByCourse (String userLogin, int courseID, int assessment, boolean result)throws DBException;


}
