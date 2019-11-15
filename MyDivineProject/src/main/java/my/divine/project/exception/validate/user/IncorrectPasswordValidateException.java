package my.divine.project.exception.validate.user;

import my.divine.project.exception.validate.ValidateException;

public class IncorrectPasswordValidateException extends ValidateException {
    public IncorrectPasswordValidateException (String massage){
        super(massage);
    }
}
