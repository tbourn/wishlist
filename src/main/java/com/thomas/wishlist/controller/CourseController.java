package com.thomas.wishlist.controller;

import com.thomas.wishlist.exception.CourseNotFoundException;
import com.thomas.wishlist.entity.Course;
import com.thomas.wishlist.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CourseController {
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    // endpoint: create a new Course under a specific Technology
    @PostMapping("/courses")
    public ResponseEntity<Course> createCourse(@Valid @RequestBody Course course) {
        return new ResponseEntity(this.courseService.saveCourse(course), HttpStatus.CREATED);
    }

    // endpoint: update an existing Course
    @PutMapping("courses/{id}")
    public ResponseEntity<Course> updateCourse(@Valid @RequestBody Course course, @PathVariable Integer id)
            throws CourseNotFoundException {
        return new ResponseEntity(this.courseService.updateCourse(course, id), HttpStatus.OK);
    }

    // endpoint: Retrieve the list of all the Technologies and Courses records
    @GetMapping("/courses")
    public ResponseEntity<List> findAll() {
        return new ResponseEntity<List>(this.courseService.findAllCourse(), HttpStatus.OK);
    }

    // endpoint: Update a Course based on its Name
    @GetMapping("/courses/{id}")
    public ResponseEntity<Course> findCourseById(@PathVariable Integer id) throws CourseNotFoundException {
        return new ResponseEntity(this.courseService.findById(id), HttpStatus.OK);
    }

    // endpoint: Delete a Course based on its Name
    @DeleteMapping("/courses/{id}")
    public ResponseEntity<?> deleteCourse(@PathVariable Integer id) throws CourseNotFoundException {
        if (this.courseService.deleteCourse(id)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
