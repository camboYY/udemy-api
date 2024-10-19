package com.udemy.elearning.controllers;

import com.udemy.elearning.dto.UserCompleteLessonRequest;
import com.udemy.elearning.mapper.UserCompleteLessonResponse;
import com.udemy.elearning.models.UserCompleteLesson;
import com.udemy.elearning.services.UserCompleteLessonService;
import jakarta.validation.Valid;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/userCompleteLesson")
public class UserCompleteLessonController {

    private final UserCompleteLessonService userCompleteLessonService;

    public UserCompleteLessonController(UserCompleteLessonService userCompleteLessonService) {
        this.userCompleteLessonService = userCompleteLessonService;
    }

    @PostMapping()
    public ResponseEntity<UserCompleteLessonResponse> insertUserCompletedLesson(@Valid @RequestBody UserCompleteLessonRequest userCompleteLessonRequest) throws BadRequestException {
        UserCompleteLesson userCompleteLesson = userCompleteLessonService.create(userCompleteLessonRequest);
        UserCompleteLessonResponse userCompleteLessonResponse = new UserCompleteLessonResponse(userCompleteLesson);
        return ResponseEntity.ok(userCompleteLessonResponse);
    }

    @DeleteMapping("/{lessonId}/{userId}")
    public ResponseEntity<Void> RemoveUserCompletedLesson(@PathVariable(value = "lessonId") Long lessonId
            , @PathVariable(value = "userId") Long userId) {
        boolean isDeleted = userCompleteLessonService.deleteByUserIdAndLessonId(userId, lessonId);

        if (isDeleted) {
            return ResponseEntity.ok().build(); // Return 200 OK if deletion was successful
        } else {
            return ResponseEntity.notFound().build(); // Return 404 if the lesson was not found
        }
    }


}
