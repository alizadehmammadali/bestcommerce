package az.ibar.bcmsapigateway.service.impl;

import az.ibar.bcmsapigateway.dao.entity.UserEntity;
import az.ibar.bcmsapigateway.dao.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<UserEntity> user = userRepository.findUserEntityByEmail(email);

        if (user.isEmpty()) throw new UsernameNotFoundException("Username as email " + email + " not found");

        return user.get();
    }
}
