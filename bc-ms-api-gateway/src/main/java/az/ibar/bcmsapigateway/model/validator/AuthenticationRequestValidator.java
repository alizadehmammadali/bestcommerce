package az.ibar.bcmsapigateway.model.validator;

import az.ibar.bcmsapigateway.model.AuthRequest;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class AuthenticationRequestValidator implements ConstraintValidator<ValidRequest, AuthRequest> {

    private static final String PASSWORD = "password";
    private static final String PASSWORD_PATTERN_STRING = "[a-zA-Z0-9]{6,40}";
    private static final Pattern PASSWORD_PATTERN = Pattern.compile(PASSWORD_PATTERN_STRING);

    @Override
    public void initialize(ValidRequest constraintAnnotation) {

    }

    @Override
    public boolean isValid(AuthRequest value, ConstraintValidatorContext context) {
        if (value.getPassword() != null && !PASSWORD_PATTERN.matcher(value.getPassword()).matches()) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Password is not valid (should contain at least 6 alphanumeric characters)")
                    .addPropertyNode(PASSWORD).addConstraintViolation();
            return false;
        }
        return true;
    }
}
