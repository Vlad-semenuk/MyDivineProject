package my.divine.project.db.connector.postgresql;

import my.divine.project.db.connector.DAOFactory;
import my.divine.project.db.connector.postgresql.dao.PostgresCourseDAO;
import my.divine.project.db.connector.postgresql.dao.PostgresGradeBookDAO;
import my.divine.project.db.connector.postgresql.dao.PostgresUserDAO;
import my.divine.project.db.dao.CourseDAO;
import my.divine.project.db.dao.GradeBookDAO;
import my.divine.project.db.dao.UserDAO;
import my.divine.project.exception.ExceptionMessages;
import my.divine.project.exception.db.DBException;
import my.divine.project.exception.db.StartupDBException;
import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * pattern DAO for PostgreSQL data base.
 */

public class PostgresDAOFactory implements DAOFactory {

    private static final Logger LOG = Logger.getLogger(PostgresDAOFactory.class);

    private static PostgresDAOFactory mysqlDAOFactory;

    private DataSource dataSource;

    public PostgresDAOFactory(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Getting database connection through data source in application context
     * @return DAO factory
     */
    public static PostgresDAOFactory getInstance() {
        if (mysqlDAOFactory == null) {
            synchronized (PostgresDAOFactory.class) {
                if (mysqlDAOFactory == null) {
                    try {
                        Context initContext = new InitialContext();
                        Context envContext = (Context) initContext.lookup("java:/comp/env");
                        DataSource dataSource = (DataSource) envContext.lookup("jdbc/ST4DB");
                        mysqlDAOFactory = new PostgresDAOFactory(dataSource);
                        LOG.trace("Data source --> " + dataSource);


                    } catch (NamingException e) {
                        LOG.error(ExceptionMessages.ERRORS_WHEN_INITIALIZING_DS_MESSAGE, e);
                        throw new StartupDBException(ExceptionMessages.ERRORS_WHEN_INITIALIZING_DS_MESSAGE, e);

                    }
                }
            }
        }
        return mysqlDAOFactory;
    }

    @Override
    public Connection getConnection() throws DBException {


        try {
            return dataSource.getConnection();

        } catch (SQLException e) {
            LOG.error(ExceptionMessages.FAILED_TO_GET_CONNECTION_MESSAGE, e);
            throw new DBException(ExceptionMessages.FAILED_TO_GET_CONNECTION_MESSAGE, e);
        }

    }

    @Override
    public UserDAO getUserDAO() {
        return PostgresUserDAO.getInstance();
    }

    @Override
    public CourseDAO getCourseDAO() {
        return PostgresCourseDAO.getInstance();
    }

    @Override
    public GradeBookDAO getGradeBookDAO() {
        return PostgresGradeBookDAO.getInstance();
    }

    @Override
    public void close(Connection connection) throws DBException {
        try {
            connection.close();
        } catch (SQLException e) {
            LOG.error(ExceptionMessages.COULD_NOT_CLOSE_CONNECTION_MESSAGE, e);
            throw new DBException(ExceptionMessages.COULD_NOT_CLOSE_CONNECTION_MESSAGE, e);
        }

    }

    @Override
    public void commit(Connection connection) throws DBException {
        try {
            connection.commit();
        } catch (SQLException e) {
            LOG.error(ExceptionMessages.FAILED_TO_MAKE_CHANGES_MESSAGE, e);
            throw new DBException(ExceptionMessages.FAILED_TO_MAKE_CHANGES_MESSAGE, e);
        }

    }

    @Override
    public void rollBack(Connection connection) throws DBException {
        try {
            connection.rollback();
        } catch (SQLException e) {
            LOG.error(ExceptionMessages.COULD_NOT_ROLLBACK_TRANSACTION_MESSAGE, e);
            throw new DBException(ExceptionMessages.COULD_NOT_ROLLBACK_TRANSACTION_MESSAGE, e);
        }

    }
}
