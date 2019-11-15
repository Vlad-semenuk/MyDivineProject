package my.divine.project.db.transaction;

import my.divine.project.exception.db.DBException;
import my.divine.project.model.constant.Role;
import my.divine.project.model.entity.User;

import java.util.List;
/**
 * Created by Semenuk Vladislav
 */


public interface UserTransactionManager {

    boolean createUser (User user) throws DBException;

    User getUserByLogin (String login) throws DBException;

    boolean updateUserFullName (String login,String fullName) throws DBException;

    User getUserByLoginAndPassword(String login, String password) throws DBException;

    boolean setUserFieldRoleByUserLogin(String userLogin) throws DBException;

    List<User> getAllTeacher() throws DBException;

    boolean setUserFieldBlockedByUserLogin(boolean blocked, String userLogin) throws DBException;


}
