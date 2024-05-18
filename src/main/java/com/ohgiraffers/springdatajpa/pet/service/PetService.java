package com.ohgiraffers.springdatajpa.pet.service;

import com.ohgiraffers.springdatajpa.pet.dto.CategoryDTO;
import com.ohgiraffers.springdatajpa.pet.dto.PetDTO;
import com.ohgiraffers.springdatajpa.pet.entity.Category;
import com.ohgiraffers.springdatajpa.pet.entity.Pet;
import com.ohgiraffers.springdatajpa.pet.repository.CategoryRepository;
import com.ohgiraffers.springdatajpa.pet.repository.PetRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PetService {

    private final PetRepository petRepository;
    private final ModelMapper modelMapper;
    private final CategoryRepository categoryRepository;

    /* 1. findById */
    public PetDTO findPetByPetCode(int petCode) {
        Pet foundPet = petRepository.findById(petCode).orElseThrow(IllegalArgumentException::new);
        return modelMapper.map(foundPet, PetDTO.class);
    }

    /* 2. findAll : Sort */
    public List<PetDTO> findPetList() {
        List<Pet> petList = petRepository.findAll(Sort.by("petCode").descending());
        return petList.stream()
                .map(pet -> modelMapper.map(pet, PetDTO.class))
                .toList();
    }

    /* 3. findAll : Pageable */

    public Page<PetDTO> findPetList(Pageable pageable){
        pageable = PageRequest.of(
                pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1,
                pageable.getPageSize(),
                Sort.by("petCode").descending()
        );
        Page<Pet> petList = petRepository.findAll(pageable);
        return petList.map(pet -> modelMapper.map(pet, PetDTO.class));

    }

    /* 4. Query Method */
    public List<PetDTO> findByPetPrice(Integer petPrice) {

        List<Pet> petList = petRepository.findByPetPriceGreaterThan(
                petPrice,
                Sort.by("petPrice").descending()
        );

        return petList.stream()
                .map(pet -> modelMapper.map(pet, PetDTO.class))
                .toList();
    }

    /* 5. JPQL or Native Query */
    public List<CategoryDTO> findAllCategory() {

        List<Category> categoryList = categoryRepository.findAllCategory();
        return categoryList.stream()
                .map(category -> modelMapper.map(category, CategoryDTO.class))
                .toList();
    }

    /* 6. save */
    @Transactional
    public void registPet(PetDTO petDTO) {

        petRepository.save(modelMapper.map(petDTO, Pet.class));
    }

    /* 7. modify ( 엔티티 객체 필드 값 변경) */
    @Transactional
    public void modifyPet(PetDTO petDTO) {

        Pet foundPet = petRepository.findById(petDTO.getPetCode()).orElseThrow(IllegalArgumentException::new);
        foundPet.modifyPetName(petDTO.getPetName());
    }

    /* 8. deleteById */
    @Transactional
    public void deletePet(Integer petCode) {

        petRepository.deleteById(petCode);
    }

}
