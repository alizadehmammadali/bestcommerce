package az.ibar.bcmsuser.service.impl;

import az.ibar.bcmsuser.service.PasswordService;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;

@Service
public class Argon2PasswordService implements PasswordService {
    private static final int COMPUTE_ITERATIONS = 3;
    private static final int PARALLELISM = 4;
    private static final int MEMORY_KILOBYTES = 65_536;

    private final Argon2 argon2 = Argon2Factory.create();

    @Override
    public String hash(String password) {
        return argon2.hash(COMPUTE_ITERATIONS, MEMORY_KILOBYTES, PARALLELISM, password.toCharArray(),
                Charset.defaultCharset());
    }

    @Override
    public boolean verify(String hash, String password) {
        return argon2.verify(hash, password.toCharArray());
    }
}