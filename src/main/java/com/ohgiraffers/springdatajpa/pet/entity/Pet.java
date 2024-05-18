package com.ohgiraffers.springdatajpa.pet.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tbl_pet")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int petCode;
    private String petName;
    private int petPrice;
    private int categoryCode;
    private String adoptionStatus;

    public void modifyPetName(String petName) {
        this.petName = petName;
    }

}
