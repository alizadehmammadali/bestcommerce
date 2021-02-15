package az.ibar.bcmsuser.dao.entity;

import az.ibar.bcmsuser.dao.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@EqualsAndHashCode(callSuper = true)
@EntityListeners(AuditingEntityListener.class)
@Table(name = "verify_tokens")
@Data
public class VerifyTokenEntity extends BaseEntity {
    private String email;
    private String token;
    private String verificationUrl;
    private LocalDateTime expireDate;
}
