package com.ohgiraffers.springdatajpa.pet.controller;

import com.ohgiraffers.springdatajpa.common.Pagenation;
import com.ohgiraffers.springdatajpa.common.PagingButton;
import com.ohgiraffers.springdatajpa.pet.dto.CategoryDTO;
import com.ohgiraffers.springdatajpa.pet.dto.PetDTO;
import com.ohgiraffers.springdatajpa.pet.service.PetService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/pet")
@RequiredArgsConstructor
public class PetController {

    private final PetService petService;

    // 특정 펫 목록 보기
    @GetMapping("/{petCode}")
    public String findPetByCode(@PathVariable int petCode, Model model) {

        PetDTO pet = petService.findPetByPetCode(petCode);
        model.addAttribute("pet", pet);

        return "pet/detail";
    }

    // 펫 전체 목록 보기
    @GetMapping("/list")
    public String findPetList(Model model, @PageableDefault Pageable pageable) {

        log.info("pageable: {}", pageable);

        Page<PetDTO> petList = petService.findPetList(pageable);

        PagingButton paging = Pagenation.getPagingButtonInfo(petList);

        model.addAttribute("petList", petList);
        model.addAttribute("paging", paging);

        return "pet/list";
    }

    // 펫 메소드 테스트 페이지
    @GetMapping("/querymethod")
    public void querymethodPage() {
    }

    // 펫 메소드 테스트
    @GetMapping("/search")
    public String findByPetPrice(@RequestParam Integer petPrice, Model model) {

        List<PetDTO> petList = petService.findByPetPrice(petPrice);

        model.addAttribute("petList", petList);
        model.addAttribute("petPrice", petPrice);

        return "pet/searchResult";
    }

    // 펫 등록하기 페이지
    @GetMapping("/regist")
    public void registPage() {
    }

    // 펫 등록하기 페이지 카테고리
    @GetMapping("/category")
    @ResponseBody
    public List<CategoryDTO> findCategoryList() {
        return petService.findAllCategory();
    }

    // 펫 등록하기
    @PostMapping("/regist")
    public String registNewPet(@ModelAttribute PetDTO petDTO) {

        petService.registPet(petDTO);

        return "redirect:/pet/list";
    }

    // 펫 수정하기 페이지
    @GetMapping("/modify")
    public void modifyPage() {
    }

    // 펫 수정하기
    @PostMapping("/modify")
    public String modifyPet(@ModelAttribute PetDTO petDTO) {
        petService.modifyPet(petDTO);
        return "redirect:/pet/" + petDTO.getPetCode();
    }

    // 펫 삭제하기 페이지
    @GetMapping("/delete")
    public void deletePage() {
    }

    // 펫 삭제하기
    @PostMapping("/delete")
    public String deletePet(@RequestParam Integer petCode) {
        petService.deletePet(petCode);
        return "redirect:/pet/list";
    }
}
