package my.divine.project.web.validator.user;

import my.divine.project.exception.validate.ValidateException;
import my.divine.project.exception.validate.user.IncorrectPasswordValidateException;
import my.divine.project.web.validator.Validator;
import my.divine.project.web.validator.ValidatorUtils;

import java.util.regex.Pattern;

public class PasswordValidator implements Validator<String> {
    private static final String REGEX_FOR_PASSWORD = "(\\p{L}||\\w){4,16}";

    private static final Pattern PATTERN_FOR_PASSWORD = Pattern.compile(REGEX_FOR_PASSWORD);

    private static PasswordValidator passwordValidator = new PasswordValidator();

    private PasswordValidator() {
    }

    public static PasswordValidator getInstance(){
        return passwordValidator;
    }

    @Override
    public void validate(String password) throws ValidateException {
        if (password == null || !ValidatorUtils.suitPatter(PATTERN_FOR_PASSWORD, password)) {
            throw new IncorrectPasswordValidateException("Incorrect user password, " + System.lineSeparator() +
                    "user password consist only latter, and " + System.lineSeparator() +
                    "max length of password - 16, min length - 4");
        }
    }
}
