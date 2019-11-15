package my.divine.project.web.validator.user;

import my.divine.project.exception.validate.ValidateException;
import my.divine.project.model.entity.User;
import my.divine.project.web.validator.Validator;

public class UserValidator implements Validator<User> {

    private static final Validator<String> LOGIN_VALIDATOR = LoginValidator.getInstance();
    private static final Validator<String> PASSWORD_VALIDATOR = PasswordValidator.getInstance();
    private static final Validator<String> FULL_NAME_VALIDATOR = FullNameValidator.getInstance();

    private static UserValidator userValidator = new UserValidator();

    private UserValidator() {
    }

    public static UserValidator getInstance(){
        return userValidator;
    }

    @Override
    public void validate(User user) throws ValidateException {

        LOGIN_VALIDATOR.validate(user.getLogin());
        PASSWORD_VALIDATOR.validate(user.getPassword());
        FULL_NAME_VALIDATOR.validate(user.getFullName());
    }
}
