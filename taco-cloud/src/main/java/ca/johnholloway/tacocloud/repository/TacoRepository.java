package ca.johnholloway.tacocloud.repository;

import ca.johnholloway.tacocloud.model.Taco;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TacoRepository extends CrudRepository<Taco, UUID> {
}
