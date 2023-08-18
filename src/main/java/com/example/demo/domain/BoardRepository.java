package com.example.demo.domain;

import com.example.demo.domain.BoardDTO;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface BoardRepository extends JpaRepository<BoardDTO, String> {
}
