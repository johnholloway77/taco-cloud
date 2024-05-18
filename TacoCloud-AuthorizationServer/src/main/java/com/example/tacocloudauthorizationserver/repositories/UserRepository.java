package com.example.tacocloudauthorizationserver.repositories;

import com.example.tacocloudauthorizationserver.models.TacoUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<TacoUser, Long> {
    TacoUser findByUsername(String username);
}
