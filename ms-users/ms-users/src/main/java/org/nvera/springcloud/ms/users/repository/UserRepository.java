package org.nvera.springcloud.ms.users.repository;

import org.nvera.springcloud.ms.users.entities.Users;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<Users, Long> {
}
