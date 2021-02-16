package az.ibar.bcmsproduct.model;

import az.ibar.bcmsproduct.model.enums.DeliveryOption;
import az.ibar.bcmsproduct.model.enums.PaymentOption;
import az.ibar.bcmsproduct.model.enums.ProductCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    private Long id;

    private Long userId;

    @NotEmpty(message = "Name is mandatory")
    private String name;

    @NotEmpty(message = "Description is mandatory")
    private String description;

    @NotNull(message = "Inventory is mandatory")
    private Long inventory;

    @NotNull(message = "UnitPrice is mandatory")
    private Long unitPrice;

    @NotNull(message = "productCategory is mandatory")
    private ProductCategory productCategory;

    @NotEmpty(message = "Payment options can not be null")
    private Set<PaymentOption> paymentOption;

    @NotEmpty(message = "Delivery options can not be null")
    private Set<DeliveryOption> deliveryOption;

}
