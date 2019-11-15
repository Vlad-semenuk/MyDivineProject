package my.divine.project.db.transaction;

import my.divine.project.exception.db.DBException;
import my.divine.project.model.constant.State;
import my.divine.project.model.constant.Topic;
import my.divine.project.model.entity.Course;


import java.util.List;

/**
 * Created by Semenuk Vladislav
 */

public interface CourseTransactionManager {

    boolean createCourse (Course course)throws DBException;

    Course getCourseByID (int courseID) throws DBException;

    List<Course> getCoursesByState ()throws DBException;

    List <Course> getCourses() throws DBException;

    List <Course> getCoursesByTopic (Topic topic)throws DBException;

    List <Course> getCoursesByTeacher (String userLogin)throws DBException;

    List <Course> getCoursesBySortName (String sort ) throws DBException;

    boolean updateCourse (Course course)throws DBException;

    boolean deleteCourse (int courseID)throws DBException;

    boolean setFieldTeacher (int courseID, String userLogin)throws DBException;

    boolean setFieldState (int courseID, State state) throws DBException;

    boolean setFieldCountToCourse( int courseID, int count) throws DBException;

    List <Course> getCoursesBySortCount () throws DBException;
}
