package ca.johnholloway.tacocloud.repository;

import ca.johnholloway.tacocloud.model.TacoUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<TacoUser, Long> {
    TacoUser findByUsername(String username);
}
