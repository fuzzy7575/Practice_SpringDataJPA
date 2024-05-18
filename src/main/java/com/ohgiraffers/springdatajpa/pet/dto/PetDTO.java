package com.ohgiraffers.springdatajpa.pet.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PetDTO {

        private int petCode;
        private String petName;
        private int petPrice;
        private int categoryCode;
        private String adoptionStatus;

}
