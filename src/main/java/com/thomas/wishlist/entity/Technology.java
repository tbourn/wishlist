package com.thomas.wishlist.entity;

import lombok.*;

import javax.annotation.PostConstruct;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "technology")
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@ToString
public class Technology {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "technology_id")
    private Integer technologyId;

    @NonNull
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "technologyId",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @ToString.Exclude
    private List<Course> courses;

    @Transient
    private double avg = 0.0;

    @PostConstruct
    public double getTechnologyAvg() {
        for (Course course : this.courses) {
            this.avg += course.getCompletionPercentage();
        }

        return this.avg / this.courses.size();
    }

//    public void addCourse(Course course) {
//        courses.add(course);
//        course.setTechnologyId(this);
//    }
//
//    public void removeCourse(Course course) {
//        courses.remove(course);
//        course.setTechnologyId(null);
//    }

}
