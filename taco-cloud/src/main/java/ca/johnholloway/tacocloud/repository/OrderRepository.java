package ca.johnholloway.tacocloud.repository;

import ca.johnholloway.tacocloud.model.TacoOrder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderRepository extends CrudRepository<TacoOrder, String> {
}
