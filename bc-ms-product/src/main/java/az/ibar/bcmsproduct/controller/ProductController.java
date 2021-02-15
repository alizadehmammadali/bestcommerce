package az.ibar.bcmsproduct.controller;

import az.ibar.bcmsproduct.model.ProductDTO;
import az.ibar.bcmsproduct.model.enums.SortBy;
import az.ibar.bcmsproduct.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductDTO> create(@RequestBody @Valid ProductDTO productDTO,
                                             @RequestHeader("userId") Long userId) {
        productDTO.setUserId(userId);
        return ResponseEntity.ok(productService.create(productDTO));
    }


    @GetMapping
    public ResponseEntity<List<ProductDTO>> getList(@RequestHeader("userId") Long userId, // Sets to header in api gateway(after successfully authentication)
                                                    @RequestParam(value = "pageIndex", defaultValue = "0") Integer pageIndex,
                                                    @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                                                    @RequestParam(value = "sortBy", defaultValue = "inventory") SortBy sortBy) {

        return ResponseEntity.ok(productService.getList(userId, pageIndex, pageSize, sortBy));
    }

}
