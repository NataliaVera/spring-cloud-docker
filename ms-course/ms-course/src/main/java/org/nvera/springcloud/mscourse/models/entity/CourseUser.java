package org.nvera.springcloud.mscourse.models.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "course_users")
public class CourseUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_id", unique = true)
    private Long userid;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof  CourseUser)) return false;
        CourseUser that = (CourseUser) o;
        return Objects.equals(id, that.id) && Objects.equals(userid, that.userid);
    }

}
