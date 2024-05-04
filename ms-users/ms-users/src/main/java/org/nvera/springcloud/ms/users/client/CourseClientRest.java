package org.nvera.springcloud.ms.users.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-course", url="8002")
public interface CourseClientRest {

    @DeleteMapping("/deleteuserfromcourse/{userId}")
    void deleteUserFromCourseByUserId(@PathVariable Long userId);
}
