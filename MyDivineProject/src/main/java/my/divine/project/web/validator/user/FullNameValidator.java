package my.divine.project.web.validator.user;

import my.divine.project.exception.validate.ValidateException;
import my.divine.project.exception.validate.user.IncorrectFullNameValidateException;
import my.divine.project.web.validator.Validator;
import my.divine.project.web.validator.ValidatorUtils;

import java.util.regex.Pattern;

public class FullNameValidator implements Validator<String> {
    private static final String REGEX_FOR_FULL_NAME = "(\\pL||\\s||\\d){2,32}";

    private static final Pattern PATTERN_FOR_FULL_NAME = Pattern.compile(REGEX_FOR_FULL_NAME);

    private static FullNameValidator fullNameValidator = new FullNameValidator();

    private FullNameValidator() {
    }

    public static FullNameValidator getInstance(){
        return fullNameValidator;
    }

    @Override
    public void validate(String fullName) throws ValidateException {
        if (fullName == null || !ValidatorUtils.suitPatter(PATTERN_FOR_FULL_NAME, fullName)) {
            throw new IncorrectFullNameValidateException("Incorrect full name, " + System.lineSeparator() +
                    "full name consist only latter and spaces, and " + System.lineSeparator() +
                    "max length of full name - 32, min length - 2");
        }
    }
}
