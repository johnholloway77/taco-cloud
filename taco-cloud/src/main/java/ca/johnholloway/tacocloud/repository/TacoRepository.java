package ca.johnholloway.tacocloud.repository;

import ca.johnholloway.tacocloud.model.Taco;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TacoRepository extends CrudRepository<Taco,Long> {
}
