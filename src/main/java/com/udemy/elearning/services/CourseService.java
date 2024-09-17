package com.udemy.elearning.services;


import com.udemy.elearning.dto.CourseRequest;
import com.udemy.elearning.dto.CourseSearchRequest;
import com.udemy.elearning.models.Course;
import com.udemy.elearning.repository.CourseRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
        course.setThumbnailUrl(courseRequest.getThumbnailUrl());
        course.setStatus(courseRequest.getStatus());
        course.setCategoryId(courseRequest.getCategoryId());
        course.setCreatedBy(courseRequest.getCreatedBy());
        return courseRepository.save(course);
    }

    public Course create(CourseRequest courseRequest){
        Course course = new Course();
        course.setTitle(courseRequest.getTitle());
        course.setPrice(courseRequest.getPrice());
        course.setCourseBy(courseRequest.getCourseBy());
        course.setCourseInclude(courseRequest.getCourseInclude());
        course.setCourseLearning(courseRequest.getCourseLearning());
        course.setCourseLearning(courseRequest.getCourseLearning());
        course.setStatus(courseRequest.getStatus());
        course.setCategoryId(courseRequest.getCategoryId());
        course.setCreatedBy(courseRequest.getCreatedBy());
        return  courseRepository.save(course);
    }

    public List<Course> findAll(int page){
        PageRequest pageRequest = PageRequest.of((page-1), 10);
        Page<Course> resultPage = courseRepository.findAll(pageRequest);
        return resultPage.getContent();
    }

    public List<Course> searchByString(String keyword,PageRequest pageRequest){
        Page<Course> resultPage = courseRepository.searchByString(keyword, pageRequest);
        return resultPage.getContent();
    }

    public Course findById(Long id){
        Course course = courseRepository.findById(id).orElseThrow(()->new NotFoundException("Course not found"));
        logger.info("Course {}", course);
        return course;
    }

    public List<Course> findByCategoryId(Long categoryId){
        List<Course> courseList = courseRepository.findByCategoryId(categoryId);;
        return courseList;
    }

}
