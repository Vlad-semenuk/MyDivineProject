package my.divine.project.web.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidatorUtils {
    private ValidatorUtils() {

    }

    public static boolean suitPatter(Pattern pattern, String text) {
        Matcher matcher = pattern.matcher(text);
        return matcher.matches();
    }
}
