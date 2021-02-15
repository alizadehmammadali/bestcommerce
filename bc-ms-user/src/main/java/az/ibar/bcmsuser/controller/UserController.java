package az.ibar.bcmsuser.controller;

import az.ibar.bcmsuser.model.UserDTO;
import az.ibar.bcmsuser.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

//Merchant user sign-up and info
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/sign-up")
    public ResponseEntity<UserDTO> signUp(@RequestBody @Valid UserDTO userDTO) {
        return new ResponseEntity(userService.signUp(userDTO), HttpStatus.CREATED);
    }

    @GetMapping("/info")
    public UserDTO getUser(@RequestHeader("userId") Long userId) {
        return userService.getUser(userId);
    }

    @GetMapping("/verify")
    public void verifyUser(@RequestParam("token") String token) {
        userService.verifyUser(token);
    }


}
