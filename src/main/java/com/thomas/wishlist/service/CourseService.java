package com.thomas.wishlist.service;

import com.thomas.wishlist.entity.Course;
import com.thomas.wishlist.exception.CourseNotFoundException;

import java.util.List;

public interface CourseService {
    Course saveCourse(Course course);

    Course updateCourse(Course course, Integer courseId) throws CourseNotFoundException;

    List<Course> findAllCourse();

    Course findById(Integer courseId) throws CourseNotFoundException;

    boolean deleteCourse(Integer courseId) throws CourseNotFoundException;
}
