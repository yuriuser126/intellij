package com.boot.board_250618.controller;

import com.boot.board_250618.model.Board;
import com.boot.board_250618.repository.BoardRepository;
import com.boot.board_250618.service.BoardService;
import com.boot.board_250618.validator.BoardValidator;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/board")
public class BoardController {
    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private BoardValidator boardValidator;

    @Autowired
    private BoardService boardService;


    @GetMapping("/list")
//    public String list(Model model){
    public String list(Model model, @PageableDefault(size = 2) Pageable pageable,
                       @RequestParam(required = false) String searchText){
//        List<Board> boards = boardRepository.findAll();

        Page<Board> boards = boardRepository.findAll(pageable);

//        boards.getTotalElements();//총 건수
//        boards.getTotalPages();//총 페이지수

        if (searchText != null && !searchText.isEmpty()) {
            // 제목에 searchText가 포함된 게시글을 페이징 처리해서 조회
            boards = boardRepository.findByTitleContainingOrContentContaining(searchText, searchText, pageable);
        } else {
            // 검색어가 없으면 전체 리스트 조회
            boards = boardRepository.findAll(pageable);
        }

        //4개씩 끊어서 볼거다 페 이 징
        int startPage = Math.max(1,boards.getPageable().getPageNumber() - 4);
        //1부터 시작해야 0부터나오지않음
        int endPage = Math.min(boards.getTotalPages(),boards.getPageable().getPageNumber() + 4);

        model.addAttribute("startPage",startPage);
        model.addAttribute("endPage",endPage);
        model.addAttribute("boards",boards);
        model.addAttribute("searchText", searchText); // 뷰에서 검색어 유지용
        //모델에 담아야 list에서가져오지.

        return "board/list";
//        보드에있는리스트 찾아감 + //디버깅을해서 board에 값 뭐들고오는지 확인하느거임

    }

    //(required = false) 아이디가 있을수도 없을수도있을때임
    @GetMapping("/form")
    public String form(@RequestParam(required = false) Long id, Model model){
        log.info("getmapping form()");
        log.info("id ="+id);


//        Optional<Board> board = boardRepository.findById(id);
        if (id != null) {// 글수정
            Board board = boardRepository.findById(id)
                    .orElse(new Board());  // 없으면 빈 board 객체
            model.addAttribute("board", board);
        } else { // 글 작성
            model.addAttribute("board", new Board());
        }
//        필드 담으려고 객체생성해놓음
        return "board/form";
//        return "board/list";
//        보드에있는리스트 찾아감 + //디버깅을해서 board에 값 뭐들고오는지 확인하느거임
    }



    //저장메소드
    //보드레파지토리에 crud다 있으니
    @PostMapping("/save")
//    public String save(@ModelAttribute Board board) {
        public String save(@Valid Board board, BindingResult bindingResult) {
        boardValidator.validate(board, bindingResult);

        if (bindingResult.hasErrors()) {
            return "board/form";
        }

//        authentication : 스프링 시큐리티의 인증정보임
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

//        세이브 메서드에 유저네임이랑 볻드가져옴 보드는 여기서 유저네임은 서비스에서
        boardService.save(username,board);



//        boardRepository.save(board);

        return "redirect:/board/list";//redirect걸어줘야함.
    }



//    삭제
    @PostMapping("/delete")
    public String delete(@RequestParam Long id) {

//        boardRepository.deleteAll(); // 다날아감
        boardRepository.deleteById(id); //id별
        return "redirect:/board/list";
    }
}
