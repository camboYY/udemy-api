package com.udemy.elearning.services;


import com.udemy.elearning.dto.CheckoutCourseRequest;
import com.udemy.elearning.models.CheckoutCourse;
import com.udemy.elearning.repository.CheckoutCourseRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;

import java.util.List;

@Service
public class CheckoutCourseService {

    private static final Logger logger = LogManager.getLogger(CheckoutCourseService.class);

    private final CheckoutCourseRepository checkoutCourseRepository;

    public CheckoutCourseService(CheckoutCourseRepository checkoutCourseRepository) {
        this.checkoutCourseRepository = checkoutCourseRepository;
    }

    @Transactional(rollbackFor = {Exception.class})
    public CheckoutCourse create(CheckoutCourseRequest checkoutCourseRequest){
        CheckoutCourse checkoutCourse = new CheckoutCourse();
        checkoutCourse.setCourseId(checkoutCourse.getCourseId());
        checkoutCourse.setPrice(checkoutCourse.getPrice());
        return  checkoutCourseRepository.save(checkoutCourse);
    }

    public List<CheckoutCourse> findAll(){
        return checkoutCourseRepository.findAll();
    }

    public CheckoutCourse findById(Long id){
        CheckoutCourse checkoutCourse = checkoutCourseRepository.findById(id).orElseThrow(()->new NotFoundException("CheckoutCourse not found"));
        logger.info("checkoutCourse {}", checkoutCourse);
        return checkoutCourse;
    }
}
