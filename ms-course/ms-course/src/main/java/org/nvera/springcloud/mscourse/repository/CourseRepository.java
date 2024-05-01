package org.nvera.springcloud.mscourse.repository;

import org.nvera.springcloud.mscourse.entity.Course;
import org.springframework.data.repository.CrudRepository;

public interface CourseRepository extends CrudRepository<Course, Long> {
}
