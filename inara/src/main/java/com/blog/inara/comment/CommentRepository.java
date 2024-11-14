package com.blog.inara.comment;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.inara.board.Board;

public interface CommentRepository extends JpaRepository<Comment, Long> {
	List<Comment> findByBoard(Board board);
}
