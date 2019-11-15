package my.divine.project.db.connector.postgresql.dao;

import my.divine.project.db.connector.postgresql.DAOUtils;
import my.divine.project.db.dao.UserDAO;
import my.divine.project.model.constant.Role;
import my.divine.project.model.entity.User;
import org.apache.commons.codec.digest.DigestUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostgresUserDAO implements UserDAO {
    private static final String INSERT_INTO_USERS_SQL = "INSERT INTO users (login, password, fullname) VALUES (?, ?, ?)";

    private static final String SELECT_FROM_USERS_BY_LOGIN_SQL = "SELECT * FROM users WHERE login = ?";

    private static final String SELECT_FROM_USERS_BY_LOGIN_AND_PASSWORD_SQL =
            "SELECT * FROM users WHERE login = ? AND password = ?";


    private static final String UPDATE_USERS_ROLE_SQL = "UPDATE users SET role_id = 2 WHERE login = ?";

    private static final String UPDATE_USER_FULL_NAME_SQL = "UPDATE users SET fullname = ? WHERE login = ?";


    private static final String UPDATE_USERS_BLOCKED_SQL = "UPDATE users SET blocked = ? WHERE login = ?";

    private static final String SELECT_ALL_TEACHERS_SQL = "SELECT * FROM users WHERE role_id = 2";

    private static PostgresUserDAO postgresUserDAO;

    private PostgresUserDAO() {
    }

    public static PostgresUserDAO getInstance() {
        if (postgresUserDAO == null) {
            synchronized (PostgresUserDAO.class) {
                if (postgresUserDAO == null) {
                    postgresUserDAO = new PostgresUserDAO();
                }
            }
        }
        return postgresUserDAO;
    }

    @Override
    public boolean createUser(Connection connection, User user) throws SQLException {

        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_USERS_SQL)) {
            DAOUtils.fillPreparedStatement(
                    preparedStatement,
                    user.getLogin(),
                    DigestUtils.md5Hex(user.getPassword()),
                    user.getFullName());


            return preparedStatement.executeUpdate() > 0;
        }

    }

    @Override
    public User getUserByLogin(Connection connection, String login) throws SQLException {

        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FROM_USERS_BY_LOGIN_SQL)) {
            preparedStatement.setString(1, login);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                if (resultSet.next()) {
                    return DAOUtils.getUser(resultSet);
                }
            }
        }
        return new User();
    }

    @Override
    public User getUserByLoginAndPassword(
            Connection connection, String login, String password) throws SQLException {

        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FROM_USERS_BY_LOGIN_AND_PASSWORD_SQL)) {
            DAOUtils.fillPreparedStatement(preparedStatement, login, DigestUtils.md5Hex(password));

            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                if (resultSet.next()) {
                    return DAOUtils.getUser(resultSet);
                }
            }
        }
        return new User();
    }

    @Override
    public boolean updateUserFullName(Connection connection, String login, String fullName) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_FULL_NAME_SQL)) {
            DAOUtils.fillPreparedStatement(
                    preparedStatement,
                    fullName,
                    login);

            return preparedStatement.executeUpdate() > 0;
        }
    }


    @Override
    public boolean setUserFieldRoleByUserLogin(
            Connection connection, String userLogin) throws SQLException {

        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USERS_ROLE_SQL)) {
            DAOUtils.fillPreparedStatement(
                    preparedStatement,
                    userLogin);

            return preparedStatement.executeUpdate() > 0;
        }
    }

    @Override
    public List<User> getAllTeacher(Connection connection) throws SQLException{
        List<User> teachers = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_ALL_TEACHERS_SQL)) {
            while (resultSet.next()){
                teachers.add(DAOUtils.getUser(resultSet));
            }
        }
        return teachers;
    }


    @Override
    public boolean setUserFieldBlockedByUserLogin(
            Connection connection, boolean blocked, String userLogin) throws SQLException {

        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USERS_BLOCKED_SQL)) {
            DAOUtils.fillPreparedStatement(
                    preparedStatement,
                    blocked,
                    userLogin);

            return preparedStatement.executeUpdate() > 0;
        }
    }
}
