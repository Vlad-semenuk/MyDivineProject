package my.divine.project.web.validator.course;

import my.divine.project.exception.validate.ValidateException;
import my.divine.project.exception.validate.course.IncorrectCourseNameException;
import my.divine.project.exception.validate.user.IncorrectFullNameValidateException;
import my.divine.project.web.validator.Validator;
import my.divine.project.web.validator.ValidatorUtils;

import java.util.regex.Pattern;

public class NameValidator implements Validator<String > {
    private static final String REGEX_FOR_NAME = "(\\pL||\\s||\\d){2,32}";

    private static final Pattern PATTERN_FOR_NAME = Pattern.compile(REGEX_FOR_NAME);

    private static NameValidator nameValidator = new NameValidator();

    private NameValidator (){}

    public static NameValidator getInstance(){
        return nameValidator;
    }
    @Override
    public void validate(String name) throws ValidateException {
        if (name == null || !ValidatorUtils.suitPatter(PATTERN_FOR_NAME, name)) {
            throw new IncorrectCourseNameException("Incorrect  course name, " + System.lineSeparator() +
                    "name consist only latter and spaces, and " + System.lineSeparator() +
                    "max length of course name - 32, min length - 2");
        }

    }
}
