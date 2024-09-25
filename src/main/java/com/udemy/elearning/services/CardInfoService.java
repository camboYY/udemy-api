package com.udemy.elearning.services;

import com.udemy.elearning.dto.CardInfoRequest;
import com.udemy.elearning.models.CardInfo;
import com.udemy.elearning.repository.CardInfoRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;

@Service
public class CardInfoService {

    private static final Logger logger = LogManager.getLogger(CardInfoService.class);

    private final CardInfoRepository cardInfoRepository;

    public CardInfoService(CardInfoRepository cardInfoRepository) {
        this.cardInfoRepository = cardInfoRepository;
    }

    public CardInfo create(CardInfoRequest cardInfoRequest) {
        CardInfo cardInfo = new CardInfo();
        cardInfo.setCardNumber(cardInfoRequest.getCardNumber());
        cardInfo.setCardHolderName(cardInfoRequest.getCardHolderName());
        cardInfo.setCardType(cardInfoRequest.getCardType());
        cardInfo.setCardExpiry(cardInfoRequest.getCardExpiry());
        cardInfo.setCardCVC(cardInfoRequest.getCardCVC());
        return cardInfoRepository.save(cardInfo);
    }

    public List<CardInfo> findAll() {
        return cardInfoRepository.findAll();
    }

    public CardInfo findById(Long id) {
        CardInfo cardInfo = cardInfoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("CardInfo not found"));
        logger.info("Found cardInfo: {}", cardInfo);
        return cardInfo;
    }

}
