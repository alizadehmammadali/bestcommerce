package az.ibar.bcmsuser.service;

import az.ibar.bcmsuser.util.ValidatorUtil;

public interface PasswordService {
    default void validate(String password) {
        ValidatorUtil.validatePassword(password);
    }

    String hash(String password);

    boolean verify(String hash, String password);
}
