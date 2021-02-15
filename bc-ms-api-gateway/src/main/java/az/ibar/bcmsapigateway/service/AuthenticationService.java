package az.ibar.bcmsapigateway.service;

import az.ibar.bcmsapigateway.model.AuthRequest;
import az.ibar.bcmsapigateway.model.AuthToken;

public interface AuthenticationService {
    AuthToken authenticate(AuthRequest authRequest);
}
