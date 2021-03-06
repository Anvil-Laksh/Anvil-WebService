package live.anvill.ws.repository;

import live.anvill.ws.entities.UserEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long> {
UserEntity findByEmail(String email);
UserEntity findByUserId(String userId);

}
