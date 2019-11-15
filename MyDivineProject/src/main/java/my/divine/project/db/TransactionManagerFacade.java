package my.divine.project.db;

import com.google.common.collect.Table;
import my.divine.project.db.connector.TransactionManagerFactory;
import my.divine.project.exception.db.DBException;
import my.divine.project.model.constant.Role;
import my.divine.project.model.constant.State;
import my.divine.project.model.constant.Topic;
import my.divine.project.model.entity.Course;
import my.divine.project.model.entity.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TransactionManagerFacade implements Facade {

    private static Map<TransactionManagerFactory, TransactionManagerFacade> abstractFactoryMap = new HashMap<>();

    private TransactionManagerFactory transactionManagerFactory;

    private TransactionManagerFacade(TransactionManagerFactory abstractFactory) {
        this.transactionManagerFactory = abstractFactory;
    }

    public static TransactionManagerFacade getInstance(TransactionManagerFactory transactionManagerFactory) {
        if (!abstractFactoryMap.containsKey(transactionManagerFactory)) {
            synchronized (TransactionManagerFacade.class) {
                if (!abstractFactoryMap.containsKey(transactionManagerFactory)) {
                    TransactionManagerFacade facade = new TransactionManagerFacade(transactionManagerFactory);
                    abstractFactoryMap.put(transactionManagerFactory, facade);
                    return facade;
                }
            }
        }
        return abstractFactoryMap.get(transactionManagerFactory);
    }

    //user
    @Override
    public boolean createUser(User user) throws DBException {
        return transactionManagerFactory.getUserTransactionManager().createUser(user);
    }

    @Override
    public User getUserByLogin(String login) throws DBException {
        return transactionManagerFactory.getUserTransactionManager().getUserByLogin(login);
    }

    @Override
    public User getUserByLoginAndPassword(String login, String password) throws DBException {
        return transactionManagerFactory.getUserTransactionManager().getUserByLoginAndPassword(login, password);
    }

    @Override
    public boolean setUserFieldRoleByUserLogin( String userLogin) throws DBException {
        return transactionManagerFactory.getUserTransactionManager().setUserFieldRoleByUserLogin(userLogin);
    }

    @Override
    public boolean updateUserFullName(String login, String fullName) throws DBException {
        return transactionManagerFactory.getUserTransactionManager().updateUserFullName(login, fullName);
    }

    @Override
    public List<User> getAllTeacher() throws DBException {
        return transactionManagerFactory.getUserTransactionManager().getAllTeacher();
    }

    @Override
    public boolean setUserFieldBlockedByUserLogin(boolean blocked, String userLogin) throws DBException {
        return transactionManagerFactory.getUserTransactionManager().setUserFieldBlockedByUserLogin(blocked, userLogin);
    }



    //course
    @Override
    public boolean createCourse(Course course) throws DBException {
        return transactionManagerFactory.getCourseTransactionManager().createCourse(course);
    }

    @Override
    public Course getCourseByID(int courseID) throws DBException {
        return transactionManagerFactory.getCourseTransactionManager().getCourseByID(courseID);
    }

    @Override
    public List<Course> getCoursesByState() throws DBException {
        return transactionManagerFactory.getCourseTransactionManager().getCoursesByState();
    }

    @Override
    public List<Course> getCourses() throws DBException {
        return transactionManagerFactory.getCourseTransactionManager().getCourses();
    }

    @Override
    public List<Course> getCoursesByTopic(Topic topic) throws DBException {
        return transactionManagerFactory.getCourseTransactionManager().getCoursesByTopic(topic);
    }

    @Override
    public List<Course> getCoursesByTeacher(String userLogin) throws DBException {
        return transactionManagerFactory.getCourseTransactionManager().getCoursesByTeacher(userLogin);
    }

    @Override
    public List<Course> getCoursesBySortName(String sort) throws DBException {
        return transactionManagerFactory.getCourseTransactionManager().getCoursesBySortName(sort);
    }

    @Override
    public List<Course> getCoursesBySortCount() throws DBException {
        return transactionManagerFactory.getCourseTransactionManager().getCoursesBySortCount();
    }

    @Override
    public boolean updateCourse(Course course) throws DBException {
        return transactionManagerFactory.getCourseTransactionManager().updateCourse(course);
    }

    @Override
    public boolean deleteCourse(int courseID) throws DBException {
        return transactionManagerFactory.getCourseTransactionManager().deleteCourse(courseID);
    }

    @Override
    public boolean setFieldTeacher(int courseID, String userLogin) throws DBException {
        return transactionManagerFactory.getCourseTransactionManager().setFieldTeacher(courseID, userLogin);
    }

    @Override
    public boolean setFieldState(int courseID, State state) throws DBException {
        return transactionManagerFactory.getCourseTransactionManager().setFieldState(courseID, state);
    }

    @Override
    public boolean setFieldCountToCourse(int courseID, int count) throws DBException {
        return transactionManagerFactory.getCourseTransactionManager().setFieldCountToCourse(courseID, count);
    }


    //grade book
    @Override
    public boolean setUserToCourse(String login, int courseID) throws DBException {
        return transactionManagerFactory.getGradeBookTransactionManager().setUserToCourse(login, courseID);
    }


    @Override
    public Map<User, Integer> getUsersByCourse(int courseID) throws DBException {
        return transactionManagerFactory.getGradeBookTransactionManager().getUsersByCourse(courseID);
    }

    @Override
    public Map<User, Boolean> getUsersCourseResult(int courseId) throws DBException {
        return transactionManagerFactory.getGradeBookTransactionManager().getUsersCourseResult(courseId);
    }

    @Override
    public boolean checkUserReg(String login, int courseID) throws DBException {
        return transactionManagerFactory.getGradeBookTransactionManager().checkUserReg(login, courseID);
    }

    @Override
    public Map<Course, Integer> getCoursesByUserLogin(String userLogin, State state) throws DBException {
        return transactionManagerFactory.getGradeBookTransactionManager().getCoursesByUserLogin(userLogin, state);
    }

    @Override
    public Integer getQuantityStudentFromCourse(int courseID) throws DBException {
        return transactionManagerFactory.getGradeBookTransactionManager().getQuantityStudentFromCourse(courseID);
    }

    @Override
    public boolean deleteUserFromCourse(String userLogin, int courseID) throws DBException {
        return transactionManagerFactory.getGradeBookTransactionManager().deleteUserFromCourse(userLogin, courseID);
    }

    @Override
    public boolean setAssessmentToUserByCourse(String userLogin, int courseID, int assessment, boolean result) throws DBException {
        return transactionManagerFactory.getGradeBookTransactionManager().setAssessmentToUserByCourse(userLogin,courseID, assessment, result);
    }




}
