package com.udemy.elearning.services;


import com.udemy.elearning.dto.CourseRequest;
import com.udemy.elearning.models.Course;
import com.udemy.elearning.repository.CourseRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;

@Service
public class CourseService {

    private static final Logger logger = LogManager.getLogger(CourseService.class);

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository CourseRepository) {
        this.courseRepository = CourseRepository;
    }

    public Course updateCourse(long id, CourseRequest courseRequest) {
        Course course = courseRepository.findById(id).orElseThrow(()->new NotFoundException("Course not found") );
        logger.info("course {}", courseRequest);
        course.setTitle(courseRequest.getTitle());
        course.setPrice(courseRequest.getPrice());
        course.setCourseBy(courseRequest.getCourseBy());
        course.setCourseInclude(courseRequest.getCourseInclude());
        course.setCourseLearning(courseRequest.getCourseLearning());
        course.setStatus(courseRequest.getStatus());
        course.setCategoryId(courseRequest.getCategoryId());
        course.setCreatedBy(courseRequest.getCreatedBy());
        course.setCreatedAt(courseRequest.getCreatedAt());
        course.setUpdatedAt(courseRequest.getUpdatedAt());
        return courseRepository.save(course);
    }

    public Course create(CourseRequest courseRequest){
        Course course = new Course();
        course.setTitle(courseRequest.getTitle());
        course.setPrice(courseRequest.getPrice());
        course.setCourseBy(courseRequest.getCourseBy());
        course.setCourseInclude(courseRequest.getCourseInclude());
        course.setCourseLearning(courseRequest.getCourseLearning());
        course.setStatus(courseRequest.getStatus());
        course.setCategoryId(courseRequest.getCategoryId());
        course.setCreatedBy(courseRequest.getCreatedBy());
        course.setCreatedAt(courseRequest.getCreatedAt());
        course.setUpdatedAt(courseRequest.getUpdatedAt());
        return  courseRepository.save(course);
    }

    public List<Course> findAll(){
        return courseRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    public Course findById(Long id){
        Course course = courseRepository.findById(id).orElseThrow(()->new NotFoundException("Course not found"));
        logger.info("Course {}", course);
        return course;
    }

}
