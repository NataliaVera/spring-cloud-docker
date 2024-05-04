package org.nvera.springcloud.mscourse.clients;

import org.nvera.springcloud.mscourse.models.UserDAO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "ms-users", url = "localhost:8001")
public interface UserClientRest {

    @GetMapping("/user/{userId}")
    UserDAO userDetails(@PathVariable Long userId);

    @PostMapping("/user/save")
    UserDAO createUser(@RequestBody UserDAO userPOJO);


}
