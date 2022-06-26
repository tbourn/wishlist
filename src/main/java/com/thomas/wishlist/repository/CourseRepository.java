package com.thomas.wishlist.repository;

import com.thomas.wishlist.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Integer> {
}
