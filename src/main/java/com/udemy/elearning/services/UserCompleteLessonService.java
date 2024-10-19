package com.udemy.elearning.services;


import com.udemy.elearning.dto.UserCompleteLessonRequest;
import com.udemy.elearning.models.UserCompleteLesson;
import com.udemy.elearning.repository.UserCompleteLessonRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserCompleteLessonService {

    private static final Logger logger = LogManager.getLogger(UserCompleteLessonService.class);

    private final UserCompleteLessonRepository userCompleteLessonRepository;

    public UserCompleteLessonService(UserCompleteLessonRepository userCompleteLessonRepository) {
        this.userCompleteLessonRepository = userCompleteLessonRepository;
    }

    public UserCompleteLesson create(UserCompleteLessonRequest userCompleteLessonRequest){
        UserCompleteLesson userCompleteLesson = new UserCompleteLesson();
        userCompleteLesson.setUserId(userCompleteLessonRequest.getUserId());
        userCompleteLesson.setLessonId(userCompleteLessonRequest.getLessonId());
        return  userCompleteLessonRepository.save(userCompleteLesson);
    }

    @Transactional
    public Boolean deleteByUserIdAndLessonId(Long userId, Long lessonId) {
        logger.info("Attempting to delete UserCompleteLesson for userId: {} and lessonId: {}", userId, lessonId);
        try {
            userCompleteLessonRepository.deleteByUserIdAndLessonId(userId, lessonId);
            logger.info("Deletion successful for userId: {} and lessonId: {}", userId, lessonId);
            return true;
        } catch (EmptyResultDataAccessException e) {
            logger.warn("No UserCompleteLesson found for userId: {} and lessonId: {}", userId, lessonId);
            return false;
        } catch (Exception e) {
            logger.error("Error occurred while deleting UserCompleteLesson for userId: {} and lessonId: {}", userId, lessonId, e);
            return false;
        }
    }
}
