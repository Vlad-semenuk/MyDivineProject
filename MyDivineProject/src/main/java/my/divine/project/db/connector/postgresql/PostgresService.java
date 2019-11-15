package my.divine.project.db.connector.postgresql;

import my.divine.project.db.connector.DAOFactory;
import my.divine.project.db.connector.TransactionManagerFactory;
import my.divine.project.db.connector.postgresql.transaction.PostgresCourseTransactionManager;
import my.divine.project.db.connector.postgresql.transaction.PostgresGradeBookTransactionManager;
import my.divine.project.db.connector.postgresql.transaction.PostgresUserTransactionManager;
import my.divine.project.db.transaction.CourseTransactionManager;
import my.divine.project.db.transaction.GradeBookTransactionManager;
import my.divine.project.db.transaction.UserTransactionManager;

public class PostgresService implements TransactionManagerFactory {

    private static PostgresService service;

    private DAOFactory daoFactory;

    private PostgresService(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public static PostgresService getInstance() {
        if (service == null) {
            synchronized (PostgresService.class) {
                if (service == null) {
                    service =
                            new PostgresService(PostgresDAOFactory.getInstance());
                }
            }
        }
        return service;
    }

    @Override
    public UserTransactionManager getUserTransactionManager() {
        return PostgresUserTransactionManager.getInstance(daoFactory);
    }

    @Override
    public CourseTransactionManager getCourseTransactionManager() {
        return PostgresCourseTransactionManager.getInstance(daoFactory);
    }

    @Override
    public GradeBookTransactionManager getGradeBookTransactionManager() {
        return PostgresGradeBookTransactionManager.getInstance(daoFactory);
    }
}
