package az.ibar.bcmsapigateway.controller;

import az.ibar.bcmsapigateway.model.AuthRequest;
import az.ibar.bcmsapigateway.model.AuthToken;
import az.ibar.bcmsapigateway.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path = "/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @RequestMapping(value = "/sign-in", method = RequestMethod.POST)
    public ResponseEntity<AuthToken> signIn(@RequestBody @Valid AuthRequest authRequest) {

        return ResponseEntity.ok(authenticationService.authenticate(authRequest));
    }
}
