package az.ibar.bcmsuser.dao.entity;

import az.ibar.bcmsuser.dao.entity.base.BaseEntity;
import az.ibar.bcmsuser.model.enums.MerchantType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
@Table(name = "merchants")
public class UserEntity extends BaseEntity {

    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private MerchantType merchantType;

    private String merchantName;

    private String ownerName;

    private String address;

    private String phoneNumber;

    private boolean verified = false;

}
