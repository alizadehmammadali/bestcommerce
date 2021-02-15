package az.ibar.bcmsproduct.dao.entity;

import az.ibar.bcmsproduct.dao.entity.base.BaseEntity;
import az.ibar.bcmsproduct.model.enums.DeliveryOption;
import az.ibar.bcmsproduct.model.enums.PaymentOption;
import az.ibar.bcmsproduct.model.enums.ProductCategory;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "products")
public class ProductEntity extends BaseEntity {

    private Long userId;

    private String name;

    private String description;

    private Long inventory;

    private Long unitPrice;

    @Enumerated(EnumType.STRING)
    @Column(name = "product_category")
    private ProductCategory productCategory;

    @ElementCollection(targetClass = PaymentOption.class)
    @JoinTable(name = "paymentOptions", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "paymentOption", nullable = false)
    @Enumerated(EnumType.STRING)
    private Set<PaymentOption> paymentOption;

    @ElementCollection(targetClass = DeliveryOption.class)
    @JoinTable(name = "deliveryOptions", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "deliveryOption", nullable = false)
    @Enumerated(EnumType.STRING)
    private Set<DeliveryOption> deliveryOption;
}
