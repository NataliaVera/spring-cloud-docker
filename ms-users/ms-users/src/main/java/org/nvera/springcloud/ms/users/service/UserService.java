package org.nvera.springcloud.ms.users.service;

import org.nvera.springcloud.ms.users.entities.Users;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<Users> findAllUsers();
    Optional<Users> findUserById(Long userId);
    List<Users> findAllUsersById(Iterable<Long> ids);
    Optional<Users> findByEmail(String email);
    Users saveUser(Users user);
    Users updateUser(Long userId, Users user);
    Boolean deleteUser(Long userId);
    Boolean existUser(Long userid);
    boolean existsByEmail(String email);
}
