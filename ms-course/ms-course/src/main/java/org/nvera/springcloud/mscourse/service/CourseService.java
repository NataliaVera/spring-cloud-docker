package org.nvera.springcloud.mscourse.service;

import org.nvera.springcloud.mscourse.entity.Course;

import java.util.List;
import java.util.Optional;

public interface CourseService {

    List<Course> findAllCourses();
    Optional<Course> findByCourseId(Long courseId);
    Course saveCourse(Course course);
    Course updateCourse(Long courseId, Course course);
    Boolean deleteCourse(Long courseId);
    Boolean existsCourse(Long courseId);
}
