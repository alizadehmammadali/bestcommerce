package az.ibar.bcmsuser.dao.repo;

import az.ibar.bcmsuser.dao.entity.VerifyTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VerifyTokenRepository extends JpaRepository<VerifyTokenEntity, Long> {
    Optional<VerifyTokenEntity> findVerifyTokenEntityByToken(@Param("token") String token);
}
