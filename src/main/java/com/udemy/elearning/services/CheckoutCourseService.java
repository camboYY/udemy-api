package com.udemy.elearning.services;


import com.udemy.elearning.dto.CheckoutCourseRequest;
import com.udemy.elearning.models.CheckoutCourse;
import com.udemy.elearning.repository.CheckoutCourseRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;

@Service
public class CheckoutCourseService {

    private static final Logger logger = LogManager.getLogger(CheckoutCourseService.class);

    private final CheckoutCourseRepository checkoutCourseRepository;

    public CheckoutCourseService(CheckoutCourseRepository checkoutCourseRepository) {
        this.checkoutCourseRepository = checkoutCourseRepository;
    }

    public CheckoutCourse create(CheckoutCourseRequest checkoutCourseRequest){
        CheckoutCourse checkoutCourse = new CheckoutCourse();
        checkoutCourse.setCheckoutId(checkoutCourseRequest.getCheckoutId());
        checkoutCourse.setCourseId(checkoutCourseRequest.getCourseId());
        checkoutCourse.setPrice(checkoutCourseRequest.getPrice());
        return  checkoutCourseRepository.save(checkoutCourse);
    }

    public List<CheckoutCourse> findAll(){
        return checkoutCourseRepository.findAll();
    }

    public List<CheckoutCourse> findByCheckoutId(Long id){
        List<CheckoutCourse> checkoutCourseList = checkoutCourseRepository.findByCheckoutId(id);
        logger.info("checkoutCourseList {}", checkoutCourseList);
        return checkoutCourseList;
    }
}
