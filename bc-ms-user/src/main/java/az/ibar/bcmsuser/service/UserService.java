package az.ibar.bcmsuser.service;

import az.ibar.bcmsuser.model.UserDTO;

public interface UserService {

    UserDTO signUp(UserDTO merchantDTO);

    UserDTO getUser(Long userId);

    void verifyUser(String token);

}
