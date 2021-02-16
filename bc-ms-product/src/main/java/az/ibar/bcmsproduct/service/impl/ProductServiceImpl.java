package az.ibar.bcmsproduct.service.impl;

import az.ibar.bcmsproduct.dao.entity.ProductEntity;
import az.ibar.bcmsproduct.dao.repo.ProductRepository;
import az.ibar.bcmsproduct.mapper.ProductMapper;
import az.ibar.bcmsproduct.model.ProductDTO;
import az.ibar.bcmsproduct.model.enums.SortBy;
import az.ibar.bcmsproduct.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private static final long MINIMUM_INVENTORY_SIZE = 5;

    private final ProductRepository productRepository;

    @Override
    public List<ProductDTO> getList(Long userId, Integer pageIndex, Integer pageSize, SortBy sortBy) {
        Pageable pageable = PageRequest.of(pageIndex, pageSize, Sort.by(sortBy.getFieldName()));
        Page<ProductEntity> pageData = productRepository.findProductEntitiesByUserIdAndInventoryIsGreaterThanEqual(userId, MINIMUM_INVENTORY_SIZE, pageable);
        return pageData.getContent()
                .stream()
                .map(ProductMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDTO create(ProductDTO productDTO) {
        ProductEntity entity = ProductMapper.INSTANCE.toEntity(productDTO);
        return ProductMapper.INSTANCE.toDTO(productRepository.save(entity));
    }
}
