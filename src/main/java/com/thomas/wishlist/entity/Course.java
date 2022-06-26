package com.thomas.wishlist.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "course")
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@ToString
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private Integer courseId;

    @NonNull
    @Column(name = "name")
    private String name;

    @NonNull
    @Column(name = "completion_percentage")
    double completionPercentage;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "technology_id")
    private Technology technologyId;

//    public Course(@NonNull String name, @NonNull double completionPercentage) {
//        this.name = name;
//        this.completionPercentage = completionPercentage;
//    }
}
