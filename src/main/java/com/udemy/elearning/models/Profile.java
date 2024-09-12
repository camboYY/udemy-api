package com.udemy.elearning.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Profiles")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = true)
    private String avatar;

    @Column()
    private String nickName;

    @Column()
    private String workExperience;

    @Column()
    private String currentWorkPlace;

    @OneToOne(mappedBy = "profile")
    private User user;

}
