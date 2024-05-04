package org.nvera.springcloud.ms.users.controller;

import jakarta.validation.Valid;
import org.nvera.springcloud.ms.users.entities.Users;
import org.nvera.springcloud.ms.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/findall")
    public List<Users> findAllUsers(){
        return userService.findAllUsers();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Optional<Users>> findById(@PathVariable Long userId){
        Optional<Users> optionalUsers = userService.findUserById(userId);
        if (optionalUsers.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok().body(optionalUsers);
    }

    @GetMapping("/userspercourse")
    public ResponseEntity<?> getStudentsPerCourse(@RequestParam List<Long> ids){
        return ResponseEntity.ok(userService.findAllUsersById(ids));
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveUser(@Valid @RequestBody Users user, BindingResult result) {
        if(result.hasErrors()) {
            return validException(result);
        }
        if(!user.getEmail().isEmpty() && userService.findByEmail(user.getEmail()).isPresent()){
            return ResponseEntity.badRequest().body(
                    Collections.singletonMap("message", "Already exists a user with that email"));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveUser(user));
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@Valid @RequestParam Long userId, @RequestBody Users users, BindingResult result){
        if(result.hasErrors()) {
            return validException(result);
        }
        if(!userService.existUser(userId)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        if(userService.findByEmail(users.getEmail()).isPresent()){
            return ResponseEntity.badRequest().body(
                    Collections.singletonMap("message", "Already exists a user with that email"));
        }
        return ResponseEntity.ok().body(userService.updateUser(userId, users));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable Long id){
        if(!userService.existUser(id)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    private static ResponseEntity<Map<String, String>> validException(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "The field " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }
}
