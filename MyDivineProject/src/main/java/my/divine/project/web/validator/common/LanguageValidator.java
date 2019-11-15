package my.divine.project.web.validator.common;

import my.divine.project.exception.validate.common.IncorrectLanguageValidateException;
import my.divine.project.exception.validate.ValidateException;
import my.divine.project.web.validator.Validator;
import my.divine.project.web.validator.ValidatorUtils;

import java.util.regex.Pattern;

public class LanguageValidator implements Validator<String> {
    private static final String REGEX_FOR_LANGUAGE = "\\w{2}";

    private static final Pattern PATTERN_FOR_LANGUAGE = Pattern.compile(REGEX_FOR_LANGUAGE);

    private static LanguageValidator dateValidator = new LanguageValidator();

    private LanguageValidator() {
    }

    public static LanguageValidator getInstance() {
        return dateValidator;
    }

    @Override
    public void validate(String language) throws ValidateException {
        if (language == null || !ValidatorUtils.suitPatter(PATTERN_FOR_LANGUAGE, language)) {
            throw new IncorrectLanguageValidateException("Incorrect language");
        }
    }
}
