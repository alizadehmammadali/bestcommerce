package az.ibar.bcmsuser.mapper;

import az.ibar.bcmsuser.dao.entity.UserEntity;
import az.ibar.bcmsuser.model.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class UserMapper {

    public static final UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mappings({
            @Mapping(target = "password", expression = "java(hidePassword(entity))")
    })
    public abstract UserDTO toDTO(UserEntity entity);

    public abstract UserEntity toEntity(UserDTO merchantDTO);

    protected String hidePassword(UserEntity entity) {
        if (entity == null) {
            return null;
        }
        return "********";
    }

}
