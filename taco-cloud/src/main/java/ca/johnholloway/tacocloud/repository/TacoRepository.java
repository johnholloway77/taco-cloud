package ca.johnholloway.tacocloud.repository;

import ca.johnholloway.tacocloud.model.Taco;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TacoRepository extends CrudRepository<Taco,Long> {

    Iterable<Taco> findAll(Sort sort);

    Page<Taco> findAll(Pageable pageable);
}
