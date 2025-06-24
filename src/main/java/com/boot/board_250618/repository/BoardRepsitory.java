package com.boot.board_250618.repository;

import com.boot.board_250618.model.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.stream.Stream;

public interface BoardRepsitory extends JpaRepository<Board, Long> {
//    제목과 내용 검색을 위해 + 페이징 위해
    Page<Board> findByTitleContainingOrContentContaining(String title, String content, Pageable pageable);

}
