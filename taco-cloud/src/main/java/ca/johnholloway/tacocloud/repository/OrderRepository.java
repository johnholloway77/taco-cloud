package ca.johnholloway.tacocloud.repository;

import ca.johnholloway.tacocloud.model.TacoOrder;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepository extends CrudRepository<TacoOrder, UUID> {

}
