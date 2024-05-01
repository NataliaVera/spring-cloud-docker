package org.nvera.springcloud.ms.users.service.impl;

import org.nvera.springcloud.ms.users.entities.Users;
import org.nvera.springcloud.ms.users.repository.UserRepository;
import org.nvera.springcloud.ms.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Users> findAllUsers() {
        return (List<Users>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Users> findUserById(Long userId) {
        return repository.findById(userId);
    }

    @Override
    public Optional<Users> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    @Transactional
    public Users saveUser(Users user) {
        return repository.save(user);
    }

    @Override
    @Transactional
    public Users updateUser(Long userId, Users user) {
        Optional<Users> optionalUsers = repository.findById(userId);
        if(optionalUsers.isPresent()){
            Users userDB = optionalUsers.get();
            userDB.setName(user.getName());
            userDB.setEmail(user.getEmail());
            userDB.setPassword(user.getPassword());
            return repository.save(userDB);
        }
        return null;
    }

    @Override
    @Transactional
    public Boolean deleteUser(Long userId) {
        if(!existUser(userId)){
            return Boolean.FALSE;
        }
        repository.deleteById(userId);
        return Boolean.TRUE;
    }

    @Override
    public Boolean existUser(Long userid) {
        return repository.existsById(userid);
    }
}
