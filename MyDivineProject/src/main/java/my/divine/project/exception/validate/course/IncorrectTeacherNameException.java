package my.divine.project.exception.validate.course;

import my.divine.project.exception.validate.ValidateException;

public class IncorrectTeacherNameException extends ValidateException {
    public IncorrectTeacherNameException (String massage){
        super(massage);
    }
}
