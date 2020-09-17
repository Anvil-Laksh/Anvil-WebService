package live.anvill.ws.repository;

import live.anvill.ws.entities.AddressEntity;
import live.anvill.ws.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<AddressEntity,Long> {
    List<AddressEntity> findAllByUserDetails(UserEntity user);
}
