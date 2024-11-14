package com.blog.inara.member;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Table(name="member")
@Data
public class Member {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String idMember;
	
	@Column(nullable=false)
	private String password;
	
	@Column(nullable=false, unique=true)
	private String name;
	
	@Column(nullable=false)
	private String role;
	
	@Column(nullable=false)
	private String regdate;
}
