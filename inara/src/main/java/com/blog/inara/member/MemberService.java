package com.blog.inara.member;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
	private final MemberRepository memberRepository;
	private final PasswordEncoder passwordEncoder;
	
	public MemberService(MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
		this.memberRepository=memberRepository;
		this.passwordEncoder=passwordEncoder;
	}
	
	public Member registerMember(String idMember, String password, String name) {
		if(memberRepository.findByMemberId(idMember).isPresent()) {
			throw new RuntimeException("중복된 아이디 사용 불가");
		}
		
		String encodedPassword = passwordEncoder.encode(password);
		Member member = new Member();
		member.setIdMember(idMember);
		member.setPassword(encodedPassword);
		member.setName(name);
		member.setRole("NORMAL");
		return memberRepository.save(member);
	}
	
	public String loginMember(String idMember, String password) {
		Member member = memberRepository.findByMemberId(idMember)
				.orElseThrow(()-> new RuntimeException("아이디 혹은 비밀번호가 틀렸습니다."));
		if(!passwordEncoder.matches(password, member.getPassword())) {
			throw new RuntimeException("아이디 혹은 비밀번호가 틀렸습니다. ");
		}
		
		return jwtTokenProvider.createToken(member.getIdMember(), member.getRole());
	}
	
	public Member getMemberDetail(String idMember) {
		return memberRepository.findByMemberId(idMember)
				.orElseThrow(()-> new RuntimeException("해당 사용자가 존재하지 않습니다."));
	}
	
	public void deleteMember(String idMember) {
		Member member =  memberRepository.findByMemberId(idMember)
				.orElseThrow(()-> new RuntimeException("해당 사용자가 존재하지 않습니다."));
		memberRepository.delete(member);
	}
}