package az.ibar.bcmsproduct.service.impl;

import az.ibar.bcmsproduct.dao.entity.ProductEntity;
import az.ibar.bcmsproduct.dao.repo.ProductRepository;
import az.ibar.bcmsproduct.mapper.ProductMapper;
import az.ibar.bcmsproduct.model.ProductDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@WebMvcTest(ProductServiceImpl.class)
@ContextConfiguration(classes = {ProductServiceImpl.class})
public class ProductServiceImplTest {

    private static final long USER_ID = 1L;
    private static final Long PRODUCT_ID = 100L;
    private static final int PAGE_NUMBER = 0;
    private static final int PAGE_SIZE = 5;

    private ProductEntity productEntity;

    private ProductDTO productDTO;

    @MockBean
    private ProductMapper productMapper;

    @MockBean
    private ProductRepository productRepository;

    @Autowired
    private ProductServiceImpl productService;

    @Before
    public void setUp() throws Exception {
        productDTO = ProductDTO.builder().id(PRODUCT_ID).build();
        productEntity = new ProductEntity();
        productEntity.setId(PRODUCT_ID);
        productEntity.setUserId(USER_ID);
    }

    @Test
    public void create() {
        when(productMapper.toDTO(productEntity)).thenReturn(productDTO);
        when(productRepository.save(productEntity)).thenReturn(productEntity);
        assertThat(productEntity.getUserId()).isEqualTo(USER_ID);
    }



}