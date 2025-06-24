package com.boot.board_250618.controller;

import com.boot.board_250618.model.Board;
import com.boot.board_250618.repository.BoardRepsitory;
import com.boot.board_250618.validator.BoardValidator;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/board")
@Slf4j
public class BoardController {
    @Autowired
    private BoardRepsitory boardRepsitory;
    @Autowired
    private BoardValidator boardValidator;

    @GetMapping("/list")
//    public String list(Model model, Pageable pageable){
//    public String list(Model model, @PageableDefault(size = 2) Pageable pageable){
    public String list(Model model, @PageableDefault(size = 2) Pageable pageable, @RequestParam(required = false, defaultValue = "") String searchText){

        log.info("@# GetMapping list()");
        log.info("@# searchText=>"+searchText);
//        List<Board> boards = boardRepsitory.findAll();
//        Page<Board> boards = boardRepsitory.findAll(PageRequest.of(1, 20));

//        Page<Board> boards = boardRepsitory.findAll(pageable);
//        searchText 제목 searchText 내용 pageable 페이징
        Page<Board> boards = boardRepsitory.findByTitleContainingOrContentContaining(searchText,searchText, pageable);
//
//        boards.getTotalElements();//총 건수
//        boards.getTotalPages();//총 페이지수

        //4개씩 끊어서 볼거다 페 이 징
        int startPage = Math.max(1,boards.getPageable().getPageNumber() - 4);
        //1부터 시작해야 0부터나오지않음
        int endPage = Math.min(boards.getTotalPages(),boards.getPageable().getPageNumber() + 4);
        model.addAttribute("startPage",startPage);
        model.addAttribute("endPage",endPage);
        model.addAttribute("boards",boards);
        //모델에 담아야 list에서가져오지.
        return "board/list";
    }
    @GetMapping("/form")
//    public String form(Model model){
//    public String form(Model model, @RequestParam Long id){
    public String form(Model model, @RequestParam(required = false) Long id){
        log.info("@# GetMapping form()");
        log.info("@# id=>"+id);

//        글작성, 글수정
        if(id == null){//글작성
            model.addAttribute("board", new Board());
        }else {//글수정
//        Optional<Board> board = boardRepsitory.findById(id);
            Board board = boardRepsitory.findById(id).orElse(null);
            model.addAttribute("board", board);
        }

        return "board/form";
    }

    @PostMapping("/form")
//    public String form(@ModelAttribute Board board, Model model) {
    public String form(@Valid Board board, BindingResult bindingResult) {
        boardValidator.validate(board, bindingResult);

        if (bindingResult.hasErrors()) {
            return "board/form";
        }

        boardRepsitory.save(board);

//        return "board/list";
        return "redirect:/board/list";
    }
}
