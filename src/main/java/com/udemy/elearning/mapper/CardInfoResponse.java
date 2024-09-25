package com.udemy.elearning.mapper;
import com.udemy.elearning.models.CardInfo;
import lombok.Data;

import java.util.Date;

@Data
public class CardInfoResponse {
    private Number CardNumber;
    private String CardHolderName;
    private String CardType;
    private Date CardExpiry;
    private String CardCVC;
    private long id;

    public CardInfoResponse(CardInfo cardInfo){
        this.setCardNumber(cardInfo.getCardNumber());
        this.setCardHolderName(cardInfo.getCardHolderName());
        this.setCardType(cardInfo.getCardType());
        this.setCardExpiry(cardInfo.getCardExpiry());
        this.setCardCVC(cardInfo.getCardCVC());
        this.setId(cardInfo.getId());
    }
}
