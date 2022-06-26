package com.thomas.wishlist.service.impl;

import com.thomas.wishlist.entity.Course;
import com.thomas.wishlist.entity.Technology;
import com.thomas.wishlist.exception.CourseNotFoundException;
import com.thomas.wishlist.repository.CourseRepository;
import com.thomas.wishlist.repository.TechnologyRepository;
import com.thomas.wishlist.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private TechnologyRepository technologyRepository;

    @Override
    public Course saveCourse(Course course) {
        if (course.getTechnologyId() != null) {
            Optional<Technology> technology = this.technologyRepository
                    .findById(course.getTechnologyId().getTechnologyId());
            if (technology.isPresent()) {
                course.setTechnologyId(technology.get());
            }
        }
        return this.courseRepository.save(course);
    }

    @Override
    public Course updateCourse(Course course, Integer courseId) throws CourseNotFoundException {
        return this.courseRepository.findById(courseId).map(tempCourse -> {
            tempCourse.setName(course.getName());
            tempCourse.setCompletionPercentage(course.getCompletionPercentage());
            tempCourse.setTechnologyId(course.getTechnologyId());
            return this.courseRepository.save(tempCourse);
        }).orElseThrow(() -> new CourseNotFoundException(course.getCourseId()));
    }

    @Override
    public List<Course> findAllCourse() {
        return courseRepository.findAll();
    }

    @Override
    public Course findById(Integer courseId) throws CourseNotFoundException {
        return this.courseRepository.findById(courseId)
                .orElseThrow(() -> new CourseNotFoundException(courseId));
    }

    @Override
    public boolean deleteCourse(Integer courseId) throws CourseNotFoundException {
        return this.courseRepository.findById(courseId).map(tempCourse -> {
            this.courseRepository.delete(tempCourse);
            return true;
        }).orElseThrow(() -> new CourseNotFoundException(courseId));
    }
}
