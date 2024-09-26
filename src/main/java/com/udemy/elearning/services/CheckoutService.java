package com.udemy.elearning.services;


import com.udemy.elearning.dto.CheckoutRequest;
import com.udemy.elearning.mapper.CheckoutResponse;
import com.udemy.elearning.models.CardInfo;
import com.udemy.elearning.models.Checkout;
import com.udemy.elearning.models.CheckoutCourse;
import com.udemy.elearning.models.Course;
import com.udemy.elearning.repository.CardInfoRepository;
import com.udemy.elearning.repository.CheckoutCourseRepository;
import com.udemy.elearning.repository.CheckoutRepository;
import com.udemy.elearning.repository.CourseRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Service
public class CheckoutService {

    private static final Logger logger = LogManager.getLogger(CheckoutService.class);

    private final CheckoutRepository checkoutRepository;
    private final CheckoutCourseRepository checkoutCourseRepository;
    private final CourseRepository courseRepository;
    private final CardInfoRepository cardInfoRepository;
    public CheckoutService(CheckoutRepository checkoutRepository, CheckoutCourseRepository checkoutCourseRepository, CourseRepository courseRepository, CardInfoRepository cardInfoRepository) {
        this.checkoutRepository = checkoutRepository;
        this.checkoutCourseRepository = checkoutCourseRepository;
        this.courseRepository = courseRepository;
        this.cardInfoRepository = cardInfoRepository;
    }

    public Checkout create(CheckoutRequest checkoutRequest){

        if (isCardExpired(checkoutRequest.getCardExpiry())) {
            throw new IllegalArgumentException("Card is expired");
        }

        double total_amount = 0.00;
        for (long courseId: checkoutRequest.getCourseId()){
            CheckoutCourse checkoutCourse = new CheckoutCourse();
            checkoutCourse.setCourseId(courseId);
            checkoutCourse.setCheckoutId(courseId);
            Course course = courseRepository.findById(courseId).orElseThrow(()->new NotFoundException("Course not found"));
            checkoutCourse.setPrice(course.getPrice());
            total_amount += course.getPrice();
            CheckoutCourse checkoutCourseResult = checkoutCourseRepository.save(checkoutCourse);
        }
        Checkout checkout = new Checkout();
        checkout.setUserId(checkoutRequest.getUserId());
        checkout.setTotalAmount(total_amount);

        CardInfo cardInfo = new CardInfo();
        cardInfo.setCardNumber(checkoutRequest.getCardNumber());
        cardInfo.setCardType(checkoutRequest.getCardType());
        cardInfo.setCardHolderName(checkoutRequest.getCardHolderName());
        cardInfo.setCardExpiry(checkoutRequest.getCardExpiry());
        cardInfo.setCardCVC(checkoutRequest.getCardCVC());
        CardInfo cardInfoResult = cardInfoRepository.save(cardInfo);

        checkout.setCardInfoId(cardInfoResult.getId());
        return checkoutRepository.save(checkout);
    }

    public List<Checkout> findAll(){
        return checkoutRepository.findAll();
    }

    public Checkout findById(Long id){
        Checkout checkout = checkoutRepository.findById(id).orElseThrow(()->new NotFoundException("Checkout not found"));
        logger.info("checkout {}", checkout);
        return checkout;
    }
    private boolean isCardExpired(String cardExpiry) {
        // Assuming cardExpiry is in the format "MM/YY"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yy");
        YearMonth expiryDate = YearMonth.parse(cardExpiry, formatter);
        YearMonth currentDate = YearMonth.now();

        return expiryDate.isBefore(currentDate);  // Return true if the card is expired
    }
}
