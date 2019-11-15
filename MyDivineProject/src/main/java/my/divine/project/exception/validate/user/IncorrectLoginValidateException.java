package my.divine.project.exception.validate.user;

import my.divine.project.exception.validate.ValidateException;

public class IncorrectLoginValidateException extends ValidateException {
    public IncorrectLoginValidateException(String message) {
        super(message);
    }
}
