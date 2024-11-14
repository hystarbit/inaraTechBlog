package com.blog.inara.comment;

import java.util.List;

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

import com.blog.inara.board.Board;
import com.blog.inara.board.BoardService;
import com.blog.inara.board.PostRequest;
import com.blog.inara.member.Member;
import com.blog.inara.member.MemberService;

@RestController
@RequestMapping("/blog/inara/comments")
public class CommentController {
	private final CommentService commentService;
	private final MemberService memberService;
	
	public CommentController(CommentService commentService, MemberService memberService) {
		this.commentService = commentService;
		this.memberService = memberService;
	}
	
	@PostMapping("/{idBoard}")
	public ResponseEntity<Comment> createComment(@PathVariable Long idBoard, @RequestBody CommentRequest request, @RequestHeader("Authorization") String token){
		Member member = memberService.getMemberFromToken(token);
		return ResponseEntity.ok(commentService.createComment(idBoard, request.getContent(), member, request.getParentId()));
	}
	
	@GetMapping("/{idBoard}")
	public ResponseEntity<List<Comment>> getCommentsByBoard(@PathVariable Long idBoard){
		return ResponseEntity.ok(commentService.getCommentsByBoard(idBoard));
	}
	
	
	@DeleteMapping("/{idComment}")
	public ResponseEntity<?> deleteComment(@PathVariable Long idComment, @RequestHeader("Authorization") String token){
		Member member = memberService.getMemberFromToken(token);
		commentService.deleteComment(idComment, member);
		return ResponseEntity.ok("댓글이 삭제되었습니다.");
	}
}