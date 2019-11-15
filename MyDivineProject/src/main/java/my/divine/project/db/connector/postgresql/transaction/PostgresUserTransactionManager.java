package my.divine.project.db.connector.postgresql.transaction;

import my.divine.project.db.connector.DAOFactory;
import my.divine.project.db.transaction.UserTransactionManager;
import my.divine.project.exception.ExceptionMessages;
import my.divine.project.exception.db.DBException;
import my.divine.project.model.constant.Role;
import my.divine.project.model.entity.User;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class PostgresUserTransactionManager implements UserTransactionManager {
    private static final Logger LOG = Logger.getLogger(PostgresUserTransactionManager.class);

    private static PostgresUserTransactionManager postgresUserTransactionManager;

    private DAOFactory daoFactory;

    private PostgresUserTransactionManager(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public static PostgresUserTransactionManager getInstance(DAOFactory daoFactory) {
        if (postgresUserTransactionManager == null) {
            synchronized (PostgresUserTransactionManager.class) {
                if (postgresUserTransactionManager == null) {
                    postgresUserTransactionManager = new PostgresUserTransactionManager(daoFactory);
                }
            }
        }
        return postgresUserTransactionManager;
    }

    @Override
    public boolean createUser(User user) throws DBException {
        Connection connection = daoFactory.getConnection();

        boolean result;
        try {
            result = daoFactory.getUserDAO().createUser(connection, user);

        } catch (SQLException e) {
            LOG.error(ExceptionMessages.COULD_NOT_CREATE_USER_MESSAGE, e);
            daoFactory.rollBack(connection);
            throw new DBException(ExceptionMessages.COULD_NOT_CREATE_USER_MESSAGE, e);
        }
        daoFactory.commit(connection);
        daoFactory.close(connection);
        return result;
    }

    @Override
    public User getUserByLogin(String login) throws DBException {
        Connection connection = daoFactory.getConnection();
        User user;
        try {
            user = daoFactory.getUserDAO().getUserByLogin(connection, login);
        } catch (SQLException e) {
            LOG.error(ExceptionMessages.FAILED_TO_GET_USER_BY_LOGIN_MESSAGE, e);

            throw new DBException(ExceptionMessages.FAILED_TO_GET_USER_BY_LOGIN_MESSAGE, e);
        }
        daoFactory.close(connection);
        return user;
    }

    @Override
    public boolean updateUserFullName(String login, String fullName) throws DBException {
        Connection connection = daoFactory.getConnection();

        boolean result;
        try {
            result = daoFactory.getUserDAO().updateUserFullName(connection, login, fullName);

        } catch (SQLException e) {
            LOG.error(ExceptionMessages.COULD_NOT_EDIT_USER_MESSAGE, e);
            daoFactory.rollBack(connection);
            throw new DBException(ExceptionMessages.COULD_NOT_EDIT_USER_MESSAGE, e);
        }
        daoFactory.commit(connection);
        daoFactory.close(connection);
        return result;
    }

    @Override
    public User getUserByLoginAndPassword(String login, String password) throws DBException {
        Connection connection = daoFactory.getConnection();
        User user;
        try{
            user = daoFactory.getUserDAO().getUserByLoginAndPassword(connection, login, password);
        } catch (SQLException e){
            LOG.error(ExceptionMessages.FAILED_TO_GET_USER_BY_LOGIN_AND_PASS_MESSAGE, e);

            throw new DBException(ExceptionMessages.FAILED_TO_GET_USER_BY_LOGIN_AND_PASS_MESSAGE, e);
        }
        daoFactory.close(connection);
        return user;
    }

    @Override
    public boolean setUserFieldRoleByUserLogin(String userLogin) throws DBException {
        Connection connection = daoFactory.getConnection();

        boolean result;
        try {
            result = daoFactory.getUserDAO().setUserFieldRoleByUserLogin(connection, userLogin);

        } catch (SQLException e) {
            LOG.error(ExceptionMessages.COULD_NOT_SET_USER_FIELD_ROLE_MESSAGE, e);
            daoFactory.rollBack(connection);
            throw new DBException(ExceptionMessages.COULD_NOT_SET_USER_FIELD_ROLE_MESSAGE, e);
        }
        daoFactory.commit(connection);
        daoFactory.close(connection);
        return result;
    }


    @Override
    public List<User> getAllTeacher() throws DBException {
        Connection connection = daoFactory.getConnection();
        List <User> teachers;
        try{
            teachers = daoFactory.getUserDAO().getAllTeacher(connection);

        } catch (SQLException e){
            LOG.error(ExceptionMessages.FAILED_TO_GET_ALL_TEACHERS_MASSAGE, e);
            throw new DBException(ExceptionMessages.FAILED_TO_GET_ALL_TEACHERS_MASSAGE, e);
        }
        daoFactory.close(connection);
        return teachers;
    }

    @Override
    public boolean setUserFieldBlockedByUserLogin(boolean blocked, String userLogin) throws DBException {
        Connection connection = daoFactory.getConnection();
        boolean result;
        try {
            result  = daoFactory.getUserDAO().setUserFieldBlockedByUserLogin(connection, blocked, userLogin);

        } catch (SQLException e){
            LOG.error(ExceptionMessages.COULD_NOT_SET_USER_FIELD_BLOCK_MESSAGE, e);

            daoFactory.rollBack(connection);
            throw new DBException(ExceptionMessages.COULD_NOT_SET_USER_FIELD_BLOCK_MESSAGE, e);
        }
        daoFactory.commit(connection);
        daoFactory.close(connection);
        return result;
    }
}
