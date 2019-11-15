package my.divine.project.exception.db;

import my.divine.project.exception.AppException;

public class DBException extends AppException {

    public DBException(String massage, Throwable cause){
        super(massage,cause);

    }
}
