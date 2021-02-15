package az.ibar.bcmsproduct.mapper;

import az.ibar.bcmsproduct.dao.entity.ProductEntity;
import az.ibar.bcmsproduct.model.ProductDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class ProductMapper {
    public static final ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mappings({})
    public abstract ProductDTO toDTO(ProductEntity entity);

    public abstract ProductEntity toEntity(ProductDTO productDTO);

}
