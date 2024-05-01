package org.nvera.springcloud.mscourse.service.impl;

import org.nvera.springcloud.mscourse.entity.Course;
import org.nvera.springcloud.mscourse.repository.CourseRepository;
import org.nvera.springcloud.mscourse.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Course> findAllCourses() {
        return (List<Course>) courseRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Course> findByCourseId(Long courseId) {
        return courseRepository.findById(courseId);
    }

    @Override
    @Transactional
    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    @Transactional
    public Course updateCourse(Long courseId, Course course) {
        Optional<Course> optionalCourse = courseRepository.findById(courseId);
        if(optionalCourse.isPresent()){
            Course courseDB = optionalCourse.get();
            courseDB.setCourseName(course.getCourseName());
            return courseRepository.save(courseDB);
        }
        return null;
    }

    @Override
    @Transactional
    public Boolean deleteCourse(Long courseId) {
        if(!existsCourse(courseId)){
            return Boolean.FALSE;
        }
        courseRepository.deleteById(courseId);
        return Boolean.TRUE;
    }

    @Override
    public Boolean existsCourse(Long courseId) {
        return courseRepository.existsById(courseId);
    }
}
