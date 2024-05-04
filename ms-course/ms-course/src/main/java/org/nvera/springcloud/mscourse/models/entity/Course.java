package org.nvera.springcloud.mscourse.models.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.nvera.springcloud.mscourse.models.UserDAO;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;
    @NotEmpty
    private String courseName;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true) //cada que se elimina el curso se eliminan los usuarios
    @JoinColumn(name = "course_id") //foreign key
    private List<CourseUser> courseUsers;
    @Transient
    private List<UserDAO> userDAOList;

    public void addCourseUser(CourseUser courseUser){
        courseUsers.add(courseUser);
    }

    public void removeCourseUser(CourseUser courseUser){
        courseUsers.remove(courseUser);
    }

    public Course() {
        courseUsers = new ArrayList<>();
        userDAOList = new ArrayList<>();
    }
}
