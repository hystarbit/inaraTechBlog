package com.blog.inara.board;

import org.hibernate.query.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.inara.member.JwtResponse;
import com.blog.inara.member.Member;
import com.blog.inara.member.MemberLoginRequest;
import com.blog.inara.member.MemberRegistrationRequest;
import com.blog.inara.member.MemberService;

@RestController
@RequestMapping("/blog/inara/boards")
public class BoardController {
	private final BoardService boardService;
	private final MemberService memberService;
	
	public BoardController(BoardService boardService, MemberService memberService) {
		this.boardService = boardService;
		this.memberService = memberService;
	}
	
	@PostMapping
	public ResponseEntity<Board> createBoard(@RequestBody PostRequest request, @RequestHeader("Authorization") String token){
		Member member = memberService.getMemberFromToken(token);
		return ResponseEntity.ok(boardService.createBoard(request.getTitle(), request.getContent(), member));
	}
	
	@PutMapping("/{idBoard}")
	public ResponseEntity<Board> updateBoard(@PathVariable Long idBoard, @RequestBody PostRequest request, @RequestHeader("Authorization") String token){
		Member member = memberService.getMemberFromToken(token);
		return ResponseEntity.ok(boardService.updateBoard(idBoard, request.getTitle(), request.getContent(), member));
	}
	
	@DeleteMapping("/{idBoard}")
	public ResponseEntity<?> deleteBoard(@PathVariable String idMember, @RequestHeader("Authorization") String token){
		Member member = memberService.getMemberFromToken(token);
		boardService.deleteMember(idMember);
		return ResponseEntity.ok("게시글이 삭제되었습니다.");
	}
	
	@GetMapping
	public ResponseEntity<Page<Board>> getBoards(@PageableDefault(size=5) Pageable pageable){
		return ResponseEntity.ok(boardService.getBoards(pageable));
	}
	
	@GetMapping("/{idBoard}")
	public ResponseEntity<Board> getBoard(@PathVariable Long idBoard, @RequestHeader("Authorization") String token){
		Board board = boardService.getBoardDetail(idBoard);
		boardService.incrementViewCount(board);
		return ResponseEntity.ok(board);
	}
	
}
