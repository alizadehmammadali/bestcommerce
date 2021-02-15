package az.ibar.bcmsuser.mapper;

import az.ibar.bcmsuser.dao.entity.UserEntity;
import az.ibar.bcmsuser.dao.entity.VerifyTokenEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.util.UUID;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class VerifyTokenMapper {

    public static final VerifyTokenMapper INSTANCE = Mappers.getMapper(VerifyTokenMapper.class);

    public VerifyTokenEntity toEntity(UserEntity entity, String verificationUrl) {
        String verificationToken = UUID.randomUUID().toString();
        verificationUrl = verificationUrl + "?token=" + verificationToken;
        VerifyTokenEntity verifyTokenEntity = new VerifyTokenEntity();
        verifyTokenEntity.setToken(verificationToken);
        verifyTokenEntity.setEmail(entity.getEmail());
        verifyTokenEntity.setExpireDate(LocalDateTime.now().plusHours(12));
        verifyTokenEntity.setVerificationUrl(verificationUrl);
        return verifyTokenEntity;
    }

}
