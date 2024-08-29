package com.udemy.elearning.services;


import com.udemy.elearning.dto.CourseRequest;
import com.udemy.elearning.models.Course;
import com.udemy.elearning.repository.CourseRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
        course.setTitle(course.getTitle());
        return CourseRepository.save(Course);
    }

    public Course create(CourseRequest CourseRequest){
        Course Course = new Course();
        Course.setName(CourseRequest.getName());
        Course.setParentId(CourseRequest.getParentId());
        return  CourseRepository.save(Course);
    }

    public List<Course> findAll(){
        return CourseRepository.findAll();
    }

    public Course findById(Long id){
        Course Course = CourseRepository.findById(id).orElseThrow(()->new NotFoundException("Course not found"));
        logger.info("Course {}", Course);
        return Course;
    }

    public List<Course> findByParentId(Integer parentId){
        List<Course> CourseList = CourseRepository.findByParentId(parentId);
        logger.info("Course {}", CourseList);
        return CourseList;
    }
}
