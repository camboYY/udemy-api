package com.udemy.elearning.services;


import com.udemy.elearning.dto.CourseReviewRequest;
import com.udemy.elearning.models.CourseReview;
import com.udemy.elearning.repository.CourseReviewRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;

@Service
public class CourseReviewService {

    private static final Logger logger = LogManager.getLogger(CourseReviewService.class);

    private final CourseReviewRepository courseReviewRepository;

    public CourseReviewService(CourseReviewRepository courseReviewRepository) {
        this.courseReviewRepository = courseReviewRepository;
    }

    public CourseReview updateCourseReviews(long id, CourseReviewRequest courseReviewRequest) {
        CourseReview courseReviews = courseReviewRepository.findById(id).orElseThrow(()->new NotFoundException("Course not found") );
        logger.info("course_tag {}", courseReviewRequest);
        courseReviews.setComments(courseReviewRequest.getComment());
        courseReviews.setCourseId(courseReviewRequest.getCourseId());
        courseReviews.setUserId(courseReviewRequest.getUserId());
        courseReviews.setRating(courseReviewRequest.getRating());
        return courseReviewRepository.save(courseReviews);
    }

    public CourseReview create(CourseReviewRequest courseReviewRequest){
        CourseReview courseReviews = new CourseReview();
        courseReviews.setComments(courseReviewRequest.getComment());
        courseReviews.setCourseId(courseReviewRequest.getCourseId());
        courseReviews.setUserId(courseReviewRequest.getUserId());
        courseReviews.setRating(courseReviewRequest.getRating());
        return  courseReviewRepository.save(courseReviews);
    }

    public List<CourseReview> findAll(int page){
        PageRequest pageRequest = PageRequest.of((page-1), 10);
        Page<CourseReview> resultPage = courseReviewRepository.findAll(pageRequest);
        return resultPage.getContent();
    }

    public List<CourseReview> findByCourseId(Long courseId){
        List<CourseReview> courseReviewsList = courseReviewRepository.findByCourseId(courseId);
        logger.info("CourseReview {}", courseReviewsList);
        return courseReviewsList;
    }

    public CourseReview findById(Long id){
        CourseReview courseReviews = courseReviewRepository.findById(id).orElseThrow(()->new NotFoundException("Course not found"));
        logger.info("CourseReview {}", courseReviews);
        return courseReviews;
    }

}
