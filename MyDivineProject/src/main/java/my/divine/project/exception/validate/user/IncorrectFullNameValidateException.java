package my.divine.project.exception.validate.user;

import my.divine.project.exception.validate.ValidateException;

public class IncorrectFullNameValidateException extends ValidateException {
    public IncorrectFullNameValidateException (String massage){
        super(massage);
    }
}
