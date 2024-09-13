package com.udemy.elearning.services;


import com.udemy.elearning.dto.CourseOverViewRequest;
import com.udemy.elearning.models.CourseOverView;
import com.udemy.elearning.repository.CourseOverViewRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;

@Service
public class CourseOverViewService {

    private static final Logger logger = LogManager.getLogger(CourseOverViewService.class);

    private final CourseOverViewRepository courseOverViewRepository;

    public CourseOverViewService(CourseOverViewRepository courseOverViewRepository) {
        this.courseOverViewRepository = courseOverViewRepository;
    }

    public CourseOverView updateCourseOverViews(long id, CourseOverViewRequest courseOverViewRequest) {
        CourseOverView courseOverViews = courseOverViewRepository.findById(id).orElseThrow(()->new NotFoundException("Course not found") );
        logger.info("course_tag {}", courseOverViewRequest);
        courseOverViews.setComments(courseOverViewRequest.getComment());
        courseOverViews.setCourseId(courseOverViewRequest.getCourseId());
        courseOverViews.setUserId(courseOverViewRequest.getUserId());
        courseOverViews.setRating(courseOverViewRequest.getRating());
        return courseOverViewRepository.save(courseOverViews);
    }

    public CourseOverView create(CourseOverViewRequest courseOverViewRequest){
        CourseOverView courseOverViews = new CourseOverView();
        courseOverViews.setComments(courseOverViewRequest.getComment());
        courseOverViews.setCourseId(courseOverViewRequest.getCourseId());
        courseOverViews.setUserId(courseOverViewRequest.getUserId());
        courseOverViews.setRating(courseOverViewRequest.getRating());
        return  courseOverViewRepository.save(courseOverViews);
    }

    public List<CourseOverView> findAll(int page){
        PageRequest pageRequest = PageRequest.of((page-1), 10);
        Page<CourseOverView> resultPage = courseOverViewRepository.findAll(pageRequest);
        return resultPage.getContent();
    }

    public List<CourseOverView> findByCourseId(Long courseId){
        List<CourseOverView> courseOverViewsList = courseOverViewRepository.findByCourseId(courseId);
        logger.info("CourseOverView {}", courseOverViewsList);
        return courseOverViewsList;
    }

    public CourseOverView findById(Long id){
        CourseOverView courseOverViews = courseOverViewRepository.findById(id).orElseThrow(()->new NotFoundException("Course not found"));
        logger.info("CourseOverView {}", courseOverViews);
        return courseOverViews;
    }

}
