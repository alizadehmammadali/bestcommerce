package az.ibar.bcmsapigateway.model;

import az.ibar.bcmsapigateway.model.validator.ValidRequest;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
@ValidRequest
public class AuthRequest {
    @Email(message = "Email is not correct")
    private String email;

    @NotEmpty(message = "Password must not be null or empty")
    private String password;

    private Boolean rememberMe = false; //Default false
}
