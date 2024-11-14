package com.blog.inara.member;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String>{
	Optional<Member> findByMemberId(String idMember);
}
