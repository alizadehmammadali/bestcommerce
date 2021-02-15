package az.ibar.bcmsuser.service.impl;

import az.ibar.bcmsuser.client.KafkaClient;
import az.ibar.bcmsuser.dao.entity.UserEntity;
import az.ibar.bcmsuser.dao.entity.VerifyTokenEntity;
import az.ibar.bcmsuser.dao.repo.UserRepository;
import az.ibar.bcmsuser.dao.repo.VerifyTokenRepository;
import az.ibar.bcmsuser.exception.ResourceAlreadyExistsException;
import az.ibar.bcmsuser.exception.ResourceNotFoundException;
import az.ibar.bcmsuser.mapper.NotificationMapper;
import az.ibar.bcmsuser.mapper.UserMapper;
import az.ibar.bcmsuser.mapper.VerifyTokenMapper;
import az.ibar.bcmsuser.model.NotificationDTO;
import az.ibar.bcmsuser.model.UserDTO;
import az.ibar.bcmsuser.service.PasswordService;
import az.ibar.bcmsuser.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.ValidationException;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Value("${spring.application.verification-url}")
    private String verificationUrl;

    private final UserRepository userRepository;

    private final PasswordService passwordService;

    private final VerifyTokenRepository verifyTokenRepository;

    private final KafkaClient kafkaClient;

    @Override
    @Transactional
    public UserDTO signUp(UserDTO merchantDTO) {
        UserEntity entity = UserMapper.INSTANCE.toEntity(merchantDTO);
        String rawPassword = entity.getPassword();
        passwordService.validate(rawPassword);
        if (userRepository.findMerchantEntityByEmail(merchantDTO.getEmail()).isPresent()) {
            throw new ResourceAlreadyExistsException(UserEntity.class, "email", merchantDTO.getEmail());
        }
        entity.setPassword(passwordService.hash(rawPassword));
        UserEntity userEntity = userRepository.save(entity);
        sendVerificationToken(userEntity);
        return UserMapper.INSTANCE.toDTO(userEntity);
    }

    @Override
    public UserDTO getUser(Long userId) {
        Optional<UserEntity> userEntity = userRepository.findById(userId);
        return userEntity.map(UserMapper.INSTANCE::toDTO).orElse(null);
    }

    @Override
    public void verifyUser(String token) {
        Optional<VerifyTokenEntity> verifyTokenEntity = verifyTokenRepository.findVerifyTokenEntityByToken(token);
        if (verifyTokenEntity.isPresent()) {
            if (verifyTokenEntity.get().getExpireDate().isBefore(LocalDateTime.now())) {
                throw new ValidationException("Token has been expired. Please try again");
            }
            Optional<UserEntity> userEntity = userRepository.findMerchantEntityByEmail(verifyTokenEntity.get().getEmail());
            if (userEntity.isPresent()) {
                UserEntity entity = userEntity.get();
                entity.setVerified(true);
                userRepository.save(entity);
            }
        }
    }

    private void sendVerificationToken(UserEntity userEntity) {
        VerifyTokenEntity verifyTokenEntity = VerifyTokenMapper.INSTANCE.toEntity(userEntity, verificationUrl);
        verifyTokenRepository.save(verifyTokenEntity);
        NotificationDTO notificationDTO = NotificationMapper.INSTANCE.toDTO(userEntity, verifyTokenEntity);
        kafkaClient.sendVerificationEmailToKafka(notificationDTO);
    }

}
