package com.udemy.elearning.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "CardInfos")
@Entity
public class CardInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long id;

    private String CardNumber;
    private String CardHolderName;
    private String CardType;
    private Date CardExpiry;
    private long CardCVC;
}
