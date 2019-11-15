package my.divine.project.exception.validate.course;

import my.divine.project.exception.validate.ValidateException;

public class IncorrectCourseNameException extends ValidateException {
    public IncorrectCourseNameException (String massage){
        super(massage);
    }
}
