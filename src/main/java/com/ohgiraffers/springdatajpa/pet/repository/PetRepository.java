package com.ohgiraffers.springdatajpa.pet.repository;

import com.ohgiraffers.springdatajpa.pet.entity.Pet;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PetRepository extends JpaRepository<Pet, Integer> {

    /* 파라미터로 전달 받은 가격을 초과하는 펫 목록 조회 */
    List<Pet> findByPetPriceGreaterThan(Integer petPrice);

    /* 파라미터로 전달 받은 가격을 초과하는 펫 목록 가격순으로 조회 */
    List<Pet> findByPetPriceGreaterThanOrderByPetPrice(Integer petPrice);

    /* 파라미터로 전달 받은 가격을 초과하는 펫 목록 전달 받은 정렬 기준으로 조회 */
    List<Pet> findBypetPriceGreaterThan(Integer petPrice, Sort sort);

    List<Pet> findByPetPriceGreaterThan(Integer petPrice, Sort petPrice1);
}
