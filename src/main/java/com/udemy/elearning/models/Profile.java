package com.udemy.elearning.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


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

    @JsonBackReference
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column()
    private UpgradeRoleStatus upgradeRoleStatus;

}
