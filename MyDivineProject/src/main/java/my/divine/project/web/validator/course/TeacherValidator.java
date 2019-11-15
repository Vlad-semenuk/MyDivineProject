package my.divine.project.web.validator.course;

import my.divine.project.exception.validate.ValidateException;
import my.divine.project.exception.validate.course.IncorrectCourseNameException;
import my.divine.project.exception.validate.course.IncorrectTeacherNameException;
import my.divine.project.web.validator.Validator;
import my.divine.project.web.validator.ValidatorUtils;

import java.util.regex.Pattern;

public class TeacherValidator implements Validator<String> {
    private static final String REGEX_FOR_TEACHER = "(\\pL||\\s||\\d){2,32}";

    private static final Pattern PATTERN_FOR_TEACHER = Pattern.compile(REGEX_FOR_TEACHER);

    private static TeacherValidator teacherValidator = new TeacherValidator();

    private TeacherValidator(){}

    public static TeacherValidator getInstance(){
        return teacherValidator;
    }
    @Override
    public void validate(String teacher) throws ValidateException {
        if (teacher == null || !ValidatorUtils.suitPatter(PATTERN_FOR_TEACHER, teacher)) {
            throw new IncorrectTeacherNameException("Incorrect  teacher name, " + System.lineSeparator() +
                    "name consist only latter and spaces, and " + System.lineSeparator() +
                    "max length of teacher name - 32, min length - 2");
        }
    }
}
