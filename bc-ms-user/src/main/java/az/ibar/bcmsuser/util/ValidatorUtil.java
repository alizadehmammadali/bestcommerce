package az.ibar.bcmsuser.util;

import java.util.regex.Pattern;
import javax.validation.ValidationException;

public class ValidatorUtil {
    private static final String PASSWORD_PATTERN_STRING = "[a-zA-Z0-9]{6,40}";
    private static final Pattern PASSWORD_PATTERN = Pattern.compile(PASSWORD_PATTERN_STRING);

    public static void validatePassword(String password) {
        if (password != null && !PASSWORD_PATTERN.matcher(password).matches()) {
            throw new ValidationException("Password is not valid (should contain at least 6 alphanumeric characters)");
        }
    }
}
