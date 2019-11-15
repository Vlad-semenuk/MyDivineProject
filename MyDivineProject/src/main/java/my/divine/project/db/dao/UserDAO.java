package my.divine.project.db.dao;

import my.divine.project.exception.db.DBException;
import my.divine.project.model.constant.Role;
import my.divine.project.model.entity.User;

import java.sql.Connection;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {

     boolean createUser(Connection connection, User user) throws SQLException;

     User getUserByLogin(Connection connection, String login) throws SQLException;

     User getUserByLoginAndPassword(Connection connection, String login, String password) throws SQLException;

    boolean updateUserFullName (Connection connection, String login,String fullName) throws SQLException;

     boolean setUserFieldRoleByUserLogin(
            Connection connection, String userLogin) throws SQLException;

    List<User> getAllTeacher(Connection connection) throws SQLException;

     boolean setUserFieldBlockedByUserLogin(
            Connection connection, boolean blocked, String userLogin) throws SQLException;


}
