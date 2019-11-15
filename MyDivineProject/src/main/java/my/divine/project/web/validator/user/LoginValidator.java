package my.divine.project.web.validator.user;

import my.divine.project.exception.validate.ValidateException;
import my.divine.project.exception.validate.user.IncorrectLoginValidateException;
import my.divine.project.web.validator.Validator;
import my.divine.project.web.validator.ValidatorUtils;

import java.util.regex.Pattern;

public class LoginValidator implements Validator<String> {
    private static final String REGEX_FOR_LOGIN = "(\\p{L}||\\w){4,16}";

    private static final Pattern PATTERN_FOR_LOGIN = Pattern.compile(REGEX_FOR_LOGIN);

    private static LoginValidator loginValidator = new LoginValidator();

    private LoginValidator() {
    }

    public static LoginValidator getInstance(){
        return loginValidator;
    }

    @Override
    public void validate(String login) throws ValidateException {
        if (login == null || !ValidatorUtils.suitPatter(PATTERN_FOR_LOGIN, login)) {
            throw new IncorrectLoginValidateException("Incorrect user login, " + System.lineSeparator() +
                    "user login consist only latter, and " + System.lineSeparator() +
                    "max length of login - 16, min length - 4");
        }
    }
}
