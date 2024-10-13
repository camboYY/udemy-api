package com.udemy.elearning.services;


import com.udemy.elearning.dto.CourseLessonRequest;
import com.udemy.elearning.models.CourseLesson;
import com.udemy.elearning.repository.CourseLessonRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class CourseLessonService {

    private static final Logger logger = LogManager.getLogger(CourseLessonService.class);

    private final CourseLessonRepository courseLessonRepository;

    public CourseLessonService(CourseLessonRepository courseLessonRepository) {
        this.courseLessonRepository = courseLessonRepository;
    }

    public CourseLesson updateCourseLessons(long id, CourseLessonRequest courseLessonRequest) {
        CourseLesson courseLessons = courseLessonRepository.findById(id).orElseThrow(()->new NotFoundException("Course not found") );
        logger.info("course_tag {}", courseLessonRequest);
        courseLessons.setTitle(courseLessonRequest.getTitle());
        courseLessons.setDescription(courseLessonRequest.getDescription());
        courseLessons.setThumbnailUrl(courseLessonRequest.getThumbnailUrl());
        courseLessons.setVideoUrl(courseLessonRequest.getVideoUrl());
        courseLessons.setStatus(courseLessonRequest.getStatus());
        courseLessons.setCreatedBy(courseLessonRequest.getCreatedBy());
        courseLessons.setCourseId(courseLessonRequest.getCourseId());
        return courseLessonRepository.save(courseLessons);
    }

    public CourseLesson create(CourseLessonRequest courseLessonRequest){
        CourseLesson courseLessons = new CourseLesson();
        courseLessons.setTitle(courseLessonRequest.getTitle());
        courseLessons.setDescription(courseLessonRequest.getDescription());
        courseLessons.setThumbnailUrl(courseLessonRequest.getThumbnailUrl());
        courseLessons.setVideoUrl(courseLessonRequest.getVideoUrl());
        courseLessons.setStatus(courseLessonRequest.getStatus());
        courseLessons.setCreatedBy(courseLessonRequest.getCreatedBy());
        courseLessons.setCourseId(courseLessonRequest.getCourseId());
        return  courseLessonRepository.save(courseLessons);
    }

    public List<CourseLesson> findAll(int page){
        PageRequest pageRequest = PageRequest.of((page-1), 10);
        Page<CourseLesson> resultPage = courseLessonRepository.findAll(pageRequest);
        return resultPage.getContent();
    }

    public List<CourseLesson> findByCourseId(Long courseId){
        List<CourseLesson> courseLessonsList = courseLessonRepository.findByCourseId(courseId);
        logger.info("CourseLesson {}", courseLessonsList);
        return courseLessonsList;
    }

    public CourseLesson findById(Long id){
        CourseLesson courseLessons = courseLessonRepository.findById(id).orElseThrow(()->new NotFoundException("Course not found"));
        logger.info("CourseLesson {}", courseLessons);
        return courseLessons;
    }

    public boolean watchLessonProgressing(Long lessonId) {
        Optional<CourseLesson> courseLesson = courseLessonRepository.findById(lessonId);
        if(courseLesson.isPresent()){
            courseLesson.get().setProgressing(true);
            courseLessonRepository.save(courseLesson.get());
            return true;
        }
        return false;
    }

    public boolean getWatchLessonProgressing(Long lessonId) {
        Optional<CourseLesson> courseLesson = courseLessonRepository.findById(lessonId);
        if(courseLesson.isPresent()){
            return courseLesson.get().getProgressing();
        }

        return false;

    }
}
