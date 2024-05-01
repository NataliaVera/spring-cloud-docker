package org.nvera.springcloud.ms.users.repository;

import org.nvera.springcloud.ms.users.entities.Users;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<Users, Long> {
    Optional<Users> findByEmail(String email);
}
