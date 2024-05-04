package org.nvera.springcloud.mscourse.controller;

import feign.FeignException;
import jakarta.validation.Valid;
import org.nvera.springcloud.mscourse.models.UserDAO;
import org.nvera.springcloud.mscourse.models.entity.Course;
import org.nvera.springcloud.mscourse.service.CourseService;
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
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/findall")
    public List<Course> fidAllCourses(){
        return courseService.findAllCourses();
    }

    @GetMapping("/{courseId}")
    public ResponseEntity<Optional<Course>> findById(@PathVariable Long courseId){
        Optional<Course> optionalCourse = courseService.findByCourseId(courseId);
        if(optionalCourse.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok().body(optionalCourse);
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveCourse(@Valid  @RequestBody Course course, BindingResult result){
        if(result.hasErrors()){
            return validException(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(courseService.saveCourse(course));
    }

    @PutMapping("/update/{courseId}")
    public ResponseEntity<?> updateCourse(@PathVariable Long courseId, @Valid @RequestBody Course course,
                                          BindingResult result){
        if(result.hasErrors()){
            return validException(result);
        }
        if(!courseService.existsCourse(courseId)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(courseService.updateCourse(courseId, course));
    }

    @DeleteMapping("/delete/{courseId}")
    public ResponseEntity<Boolean> deleteCourse(@PathVariable Long courseId){
        if(!courseService.existsCourse(courseId)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        courseService.deleteCourse(courseId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/assignuser/{courseId}")
    public ResponseEntity<?> assignUser(@RequestBody UserDAO user, @PathVariable Long courseId){
        Optional<UserDAO> optionalUserDAO;
        try{
            optionalUserDAO = courseService.assignUser(user, courseId);
        }catch (FeignException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("message", "User doesn't exist by id"));
        }
        if(optionalUserDAO.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(optionalUserDAO.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/createuser/{courseId}")
    public ResponseEntity<?> createUser(@RequestBody UserDAO user, @PathVariable Long courseId){
        Optional<UserDAO> optionalUserDAO;
        try{
            optionalUserDAO = courseService.createUser(user, courseId);
        }catch (FeignException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("message", "Error create user"));
        }
        if(optionalUserDAO.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(optionalUserDAO.get());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/removeuser/{courseId}")
    public ResponseEntity<?> removeUser(@RequestBody UserDAO user, @PathVariable Long courseId){
        Optional<UserDAO> optionalUserDAO;
        try{
            optionalUserDAO = courseService.removeUser(user, courseId);
        }catch (FeignException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("message", "User doesn't exist by id"));
        }
        if(optionalUserDAO.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(optionalUserDAO.get());
        }
        return ResponseEntity.notFound().build();
    }

    private static ResponseEntity<Map<String, String>> validException(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "The field " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }
    
}
