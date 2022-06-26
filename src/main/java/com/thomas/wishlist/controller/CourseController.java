package com.thomas.wishlist.controller;

import com.thomas.wishlist.entity.Course;
import com.thomas.wishlist.exception.CourseNotFoundException;
import com.thomas.wishlist.repository.CourseRepository;
import com.thomas.wishlist.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CourseController {
    private final CourseService courseService;

    private final CourseRepository courseRepository;

    public CourseController(CourseService courseService, CourseRepository courseRepository) {
        this.courseService = courseService;
        this.courseRepository = courseRepository;
    }

    // endpoint: create a new Course under a specific Technology
    @PostMapping("/courses")
    public ResponseEntity<Course> createCourse(@Valid @RequestBody Course course) {
        return new ResponseEntity<>(this.courseService.createCourse(course), HttpStatus.CREATED);
    }

    // endpoint: Retrieve the list of all the Courses records
    @GetMapping("/courses")
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(this.courseService.findAllCourse(), HttpStatus.OK);
    }

    // endpoint: Retrieve a Course based on its ID
    @GetMapping("/courses/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) throws CourseNotFoundException {
        return new ResponseEntity<>(this.courseService.findById(id), HttpStatus.OK);
    }

    // endpoint: Retrieve a Course based on its Name
    @GetMapping("/courses/name")
    public ResponseEntity<Optional<?>> getCourseByName(@RequestParam String name) {
        return new ResponseEntity<>(courseRepository.findByName(name), HttpStatus.OK);
    }

    // endpoint: update an existing Course
    @PutMapping("courses/{id}")
    public ResponseEntity<Course> updateCourse(@Valid @RequestBody Course course, @PathVariable Integer id)
            throws CourseNotFoundException {
        return new ResponseEntity<>(this.courseService.updateCourse(course, id), HttpStatus.OK);
    }

    // endpoint: Delete a Course based on its ID
    @DeleteMapping("/courses/{id}")
    public ResponseEntity<?> deleteCourse(@PathVariable Integer id) throws CourseNotFoundException {
        if (this.courseService.deleteCourseById(id)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // endpoint: Delete a Course based on its Name
    @DeleteMapping("/courses/name")
    public ResponseEntity<?> deleteCourseByName(@RequestParam String name) throws CourseNotFoundException {
        if (this.courseService.deleteCourseByName(name)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
