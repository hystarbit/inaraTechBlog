package com.blog.inara.comment;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.blog.inara.board.Board;
import com.blog.inara.board.BoardRepository;
import com.blog.inara.member.Member;

@Service
public class CommentService {
	private final CommentRepository commentRepository;
	private final BoardRepository boardRepository;
	
	public CommentService(CommentRepository commentRepository, BoardRepository boardRepository) {
		this.commentRepository=commentRepository;
		this.boardRepository=boardRepository;
	}
	
	public Comment createComment(Long idBoard, String content, Member member, Long parentId) {
		Board board = boardRepository.findById(idBoard)
				.orElseThrow(()-> new RuntimeException("게시글이 존재하지 않습니다."));
		Comment comment = new Comment();
		comment.setContent(content);
		comment.setMemer(member);
		comment.setBoard(board);
		
		if(parentId != null) {
			Comment parentComment = commentRepository.findById(parentId)
					.orElseThrow(()-> new RuntimeException("답글의 부모 댓글이 존재하지 않습니다."));
		}
		return commentReopsitory.save(comment);
	}
	
	public Board updateComment(Long idComment, Long idBoard, String title, String content, Member member) {
		Board board = boardRepository.findById(idBoard)
				.orElseThrow(()-> new RuntimeException("해당 게시글이 존재하지 않습니다."));
		Comment comment = commentRepository.findById(idComment)
				.orElseThrow(()-> new RuntimeException("해당 댓글이 존재하지 않습니다."));
		if(!comment.getMember().equals(member)) {
			throw new RuntimeException("본인이 작성한 댓글만 수정 가능합니다.");
		}
		comment.setContent(content);
		return commentReopsitory.save(comment);
	}
	
	public List<Comment> getCommentsByBoard(Long idBoard) {
		Board board = boardRepository.findById(idBoard)
				.orElseThrow(()-> new RuntimeException("해당 게시글이 존재하지 않습니다."));
		return commentRepository.findByBoard(board);
	}
	
	public void deleteBoard(Long idComment, Member member) {
		Comment comment = commentRepository.findById(idComment)
				.orElseThrow(()-> new RuntimeException("해당 댓글이 존재하지 않습니다."));
		if(!comment.getMember().equals(member)) {
			throw new RuntimeException("본인이 작성한 댓글만 삭제 가능합니다.");
		}
		commentRepository.delete(comment);
	}
}
