package com.udemy.elearning.controllers;

import com.udemy.elearning.dto.CourseLessonRequest;
import com.udemy.elearning.dto.CourseTagRequest;
import com.udemy.elearning.mapper.CourseLessonResponse;
import com.udemy.elearning.mapper.CourseTagResponse;
import com.udemy.elearning.models.CourseLesson;
import com.udemy.elearning.models.CourseTags;
import com.udemy.elearning.services.CourseLessonService;
import com.udemy.elearning.services.CourseTagService;
import jakarta.validation.Valid;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/courseLessons")
public class CourseLessonController {

    private final CourseLessonService courseLessonService;

    public CourseLessonController(CourseLessonService courseLessonService) {
        this.courseLessonService = courseLessonService;
    }

    @GetMapping("/watchLessonProgressing/{lessonId}")
    public boolean watchLessonProgressing (@PathVariable(value = "lessonId") Long lessonId) {
        return courseLessonService.watchLessonProgressing(lessonId);
    }

    @GetMapping("/getWatchLessonProgressing/{lessonId}")
    public boolean getWatchLessonProgressing (@PathVariable(value = "lessonId") Long lessonId) {
        return courseLessonService.getWatchLessonProgressing(lessonId);
    }

    @PostMapping()
    public ResponseEntity<CourseLessonResponse> create(@Valid @RequestBody CourseLessonRequest courseLessonRequest) throws BadRequestException {
        CourseLesson courseLessonCreate = courseLessonService.create(courseLessonRequest);
        CourseLessonResponse courseLessonResponse = new CourseLessonResponse(courseLessonCreate);
        return ResponseEntity.ok(courseLessonResponse);
    }

    @GetMapping("/getByCourseId/{courseId}")
    public ResponseEntity<List<CourseLesson>> getByCourseId(@PathVariable(value = "courseId") Long courseId) {
        List<CourseLesson> courseLessonsList = courseLessonService.findByCourseId(courseId);
        return ResponseEntity.ok(courseLessonsList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseLesson> getById(@PathVariable(value = "id") Long id) {
        CourseLesson courseLessons = courseLessonService.findById(id);
        return ResponseEntity.ok(courseLessons);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseLessonResponse> updateCourseLessons(@PathVariable Long id, @RequestBody CourseLessonRequest courseLessonRequest) {
        CourseLesson courseLessons = courseLessonService.updateCourseLessons(id,courseLessonRequest);
        CourseLessonResponse courseLessonResponse = new CourseLessonResponse(courseLessons);
        return ResponseEntity.ok(courseLessonResponse);
    }

}
