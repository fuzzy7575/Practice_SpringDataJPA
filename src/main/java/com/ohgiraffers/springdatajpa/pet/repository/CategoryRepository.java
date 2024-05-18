package com.ohgiraffers.springdatajpa.pet.repository;


import com.ohgiraffers.springdatajpa.pet.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    @Query(
            value = "SELECT category_code, category_name, ref_category_code FROM tbl_category ORDER BY category_code",
            nativeQuery = true
    )
    List<Category> findAllCategory();
}
