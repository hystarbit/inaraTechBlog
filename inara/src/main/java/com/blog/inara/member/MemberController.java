package com.blog.inara.member;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/blog/inara/members")
public class MemberController {
	private final MemberService memberService;
	
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}
	
	@PostMapping("/register")
	public ResponseEntity<?> registerMember(@RequestBody MemberRegistrationRequest request){
		memberService.registerMember(request.getIdMember(), request.getPassword(), request.getName());
		return ResponseEntity.ok("회원가입 완료");
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> loginMember(@RequestBody MemberLoginRequest request){
		memberService.registerMember(request.getIdMember(), request.getPassword());
		return ResponseEntity.ok(new JwtResponse(token));
	}
	
	@GetMapping("/{idMember}")
	public ResponseEntity<Member> getUserDetails(@PathVariable String idMember){
		return ResponseEntity.ok(memberService.getMemberDetail());
	}
	
	@DeleteMapping("/{idMember}")
	public ResponseEntity<String> deleteMember(@PathVariable String idMember){
		memberService.deleteMember(idMember);
		return ResponseEntity.ok("회원 탈퇴가 완료되었습니다.");
	}
	
}
