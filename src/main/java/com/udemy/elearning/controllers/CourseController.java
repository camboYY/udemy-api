package com.udemy.elearning.controllers;

import com.udemy.elearning.dto.CourseRequest;
import com.udemy.elearning.mapper.CourseResponse;
import com.udemy.elearning.models.Category;
import com.udemy.elearning.models.Course;
import com.udemy.elearning.models.CourseLesson;
import com.udemy.elearning.models.CourseTags;
import com.udemy.elearning.services.CategoryService;
import com.udemy.elearning.services.CourseLessonService;
import com.udemy.elearning.services.CourseService;
import com.udemy.elearning.services.CourseTagService;
import jakarta.validation.Valid;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;
    private final CategoryService categoryService;
    private final CourseTagService courseTagService;
    private final CourseLessonService courseLessonService;

    public CourseController(CourseService courseService, CategoryService categoryService, CourseTagService courseTagService, CourseLessonService courseLessonService) {
        this.courseService = courseService;
        this.categoryService = categoryService;
        this.courseTagService = courseTagService;
        this.courseLessonService = courseLessonService;
    }

    @PostMapping()
    public ResponseEntity<CourseResponse> create(@Valid @RequestBody CourseRequest courseRequest) throws BadRequestException {
        Course courseCreate = courseService.create(courseRequest);
        Category category = categoryService.findById(courseCreate.getCategoryId());
        List<CourseTags> courseTagsList = courseTagService.findByCourseId(courseCreate.getId());
        List<CourseLesson> courseLessonList = courseLessonService.findByCourseId(courseCreate.getId());
        CourseResponse courseResponse = new CourseResponse(courseCreate,category, courseTagsList,courseLessonList);
        return ResponseEntity.ok(courseResponse);
    }

    @GetMapping("/page/{page}")
    public ResponseEntity<List<CourseResponse>> getAll(@PathVariable(value = "page") int page) {
        List<Course> courseList = courseService.findAll(page);
        List<CourseResponse> courseResponseList = new ArrayList<>();
        for (Course course : courseList) {
            Category category = categoryService.findById(course.getCategoryId());
            List<CourseTags> courseTagsList = courseTagService.findByCourseId(course.getId());
            List<CourseLesson> courseLessonList = courseLessonService.findByCourseId(course.getId());
            courseResponseList.add(new CourseResponse(course,category,courseTagsList,courseLessonList));
        }
        return ResponseEntity.ok(courseResponseList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseResponse> getById(@PathVariable(value = "id") Long id) {
        Course course = courseService.findById(id);
        Category category = categoryService.findById(course.getCategoryId());
        List<CourseTags> courseTagsList = courseTagService.findByCourseId(course.getId());
        List<CourseLesson> courseLessonList = courseLessonService.findByCourseId(course.getId());
        CourseResponse courseResponse = new CourseResponse(course,category,courseTagsList,courseLessonList);
        return ResponseEntity.ok(courseResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseResponse> updateCourse(@PathVariable Long id, @RequestBody CourseRequest courseRequest) {
        Course course = courseService.updateCourse(id,courseRequest);
        Category category = categoryService.findById(course.getCategoryId());
        List<CourseTags> courseTagsList = courseTagService.findByCourseId(course.getId());
        List<CourseLesson> courseLessonList = courseLessonService.findByCourseId(course.getId());
        CourseResponse courseResponse = new CourseResponse(course,category,courseTagsList,courseLessonList);
        return ResponseEntity.ok(courseResponse);
    }
    @GetMapping("/getByCategoryId/{id}")
    public ResponseEntity<List<CourseResponse>> getByCategoryId(@PathVariable(value = "id") Long id) {
        List<Course> courseList = courseService.findByCategoryId(id);
        List<CourseResponse> courseResponseList = new ArrayList<>();
        for (Course course : courseList) {
            Category category = categoryService.findById(course.getCategoryId());
            List<CourseTags> courseTagsList = courseTagService.findByCourseId(course.getId());
            List<CourseLesson> courseLessonList = courseLessonService.findByCourseId(course.getId());
            courseResponseList.add(new CourseResponse(course,category,courseTagsList,courseLessonList));
        }
        return ResponseEntity.ok(courseResponseList);
    }
}
