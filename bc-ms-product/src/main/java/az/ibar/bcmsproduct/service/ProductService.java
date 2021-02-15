package az.ibar.bcmsproduct.service;

import az.ibar.bcmsproduct.model.ProductDTO;
import az.ibar.bcmsproduct.model.enums.SortBy;

import java.util.List;

public interface ProductService {
    List<ProductDTO> getList(Long userId, Integer pageIndex, Integer pageSize, SortBy sortBy);
    ProductDTO create(ProductDTO productDTO);
}
