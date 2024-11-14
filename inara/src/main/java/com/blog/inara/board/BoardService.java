package com.blog.inara.board;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.blog.inara.board.Board;
import com.blog.inara.board.BoardRepository;
import com.blog.inara.member.Member;
import com.blog.inara.member.MemberRepository;

@Service
public class BoardService {
	private final BoardRepository boardRepository;
	
	public BoardService(BoardRepository boardRepository) {
		this.boardRepository=boardRepository;
	}
	
	public Board createBoard(String title, String content, Member member) {
		Board board = new Board();
		board.setTitle(title);
		board.setContent(content);
		board.setMemer(member);
		board.setRegdate(LocalDateTime.now());
		return boardReopsitory.save(board);
	}
	
	public Board updateBoard(Long idBoard, String title, String content, Member member) {
		Board board = boardRepository.findById(idBoard)
				.orElseThrow(()-> new RuntimeException("게시글이 존재하지 않습니다."));
		if(!board.getMember().equals(member)) {
			throw new RuntimeException("본인이 작성한 게시글만 수정 가능합니다.");
		}
		board.setTitle(title);
		board.setContent(content);
		return boardReopsitory.save(board);
	}
	
	public Board getBoardDetail(Long idBoard) {
		return boardRepository.findById(idBoard)
				.orElseThrow(()-> new RuntimeException("게시글이 존재하지 않습니다."));
	}
	
	public Page<Board> getBoards(Pageable pageable) {
		return boardRepository.findAll(pageable);
	}
	
	public void deleteBoard(Long idBoard) {
		Board board = boardRepository.findById(idBoard)
				.orElseThrow(()-> new RuntimeException("게시글이 존재하지 않습니다."));
		if(!board.getMember().equals(member)) {
			throw new RuntimeException("본인이 작성한 게시글만 수정 가능합니다.");
		}
		boardRepository.delete(board);
	}
	
	public void incrementViewCount(Board board) {
		board.setViewCount(board.getViewCount() + 1);
		boardRepository.save(board);
	}
	
}
