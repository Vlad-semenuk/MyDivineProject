package my.divine.project.db.connector;

import my.divine.project.db.transaction.CourseTransactionManager;
import my.divine.project.db.transaction.GradeBookTransactionManager;
import my.divine.project.db.transaction.UserTransactionManager;

public interface TransactionManagerFactory {

    UserTransactionManager getUserTransactionManager();

    CourseTransactionManager getCourseTransactionManager();

    GradeBookTransactionManager getGradeBookTransactionManager();
}
