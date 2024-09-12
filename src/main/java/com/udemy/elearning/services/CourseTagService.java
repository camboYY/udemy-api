package com.udemy.elearning.services;


import com.udemy.elearning.dto.CourseRequest;
import com.udemy.elearning.dto.CourseTagRequest;
import com.udemy.elearning.models.CourseTags;
import com.udemy.elearning.repository.CourseTagRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;

@Service
public class CourseTagService {

    private static final Logger logger = LogManager.getLogger(CourseTagService.class);

    private final CourseTagRepository courseTagRepository;

    public CourseTagService(CourseTagRepository CourseTagRepository) {
        this.courseTagRepository = CourseTagRepository;
    }

    public CourseTags updateCourseTags(long id, CourseTagRequest courseTagRequest) {
        CourseTags courseTags = courseTagRepository.findById(id).orElseThrow(()->new NotFoundException("Course not found") );
        logger.info("course_tag {}", courseTagRequest);
        courseTags.setTitle(courseTagRequest.getTitle());
        courseTags.setCourseId(courseTagRequest.getCourseId());
        return courseTagRepository.save(courseTags);
    }

    public CourseTags create(CourseTagRequest courseTagRequest){
        CourseTags courseTag = new CourseTags();
        courseTag.setTitle(courseTagRequest.getTitle());
        courseTag.setCourseId(courseTagRequest.getCourseId());
        return  courseTagRepository.save(courseTag);
    }

    public List<CourseTags> findAll(int page){
        PageRequest pageRequest = PageRequest.of((page-1), 10);
        Page<CourseTags> resultPage = courseTagRepository.findAll(pageRequest);
        return resultPage.getContent();
    }

    public List<CourseTags> findByCourseId(Long courseId){
        List<CourseTags> courseTagsList = courseTagRepository.findByCourseId(courseId);
        logger.info("CourseTag {}", courseTagsList);
        return courseTagsList;
    }

    public CourseTags findById(Long id){
        CourseTags courseTags = courseTagRepository.findById(id).orElseThrow(()->new NotFoundException("Course not found"));
        logger.info("CourseTag {}", courseTags);
        return courseTags;
    }

}
