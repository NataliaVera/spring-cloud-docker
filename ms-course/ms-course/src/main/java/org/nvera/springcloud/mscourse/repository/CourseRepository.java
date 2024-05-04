package org.nvera.springcloud.mscourse.repository;

import org.nvera.springcloud.mscourse.models.entity.Course;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CourseRepository extends CrudRepository<Course, Long> {

    @Modifying
    @Query("delete from CourseUser cu where cu.userid=?1")
    void deleteUserFromCourse(Long userId);
}
