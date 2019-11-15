package my.divine.project.db.connector;

import my.divine.project.db.dao.CourseDAO;
import my.divine.project.db.dao.GradeBookDAO;
import my.divine.project.db.dao.UserDAO;
import my.divine.project.exception.db.DBException;

import java.sql.Connection;

/**
 * Facade dao pattern
 */

public interface DAOFactory {
    Connection getConnection() throws DBException;

    UserDAO getUserDAO();

    CourseDAO getCourseDAO();

    GradeBookDAO getGradeBookDAO();


    void close(Connection connection) throws DBException;

    void commit(Connection connection) throws DBException;

    void rollBack(Connection connection) throws DBException;
}
