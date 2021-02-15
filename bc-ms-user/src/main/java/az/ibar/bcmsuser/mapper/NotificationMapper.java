package az.ibar.bcmsuser.mapper;

import az.ibar.bcmsuser.dao.entity.UserEntity;
import az.ibar.bcmsuser.dao.entity.VerifyTokenEntity;
import az.ibar.bcmsuser.model.NotificationDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class NotificationMapper {
    public static final NotificationMapper INSTANCE = Mappers.getMapper(NotificationMapper.class);

    @Mappings({
            @Mapping(target = "receiverName",source = "userEntity.merchantName"),
            @Mapping(target = "receiverEmail",source = "userEntity.email"),
            @Mapping(target = "verificationUrl",source = "verifyTokenEntity.verificationUrl"),
    })
    public abstract NotificationDTO toDTO(UserEntity userEntity, VerifyTokenEntity verifyTokenEntity);
}
