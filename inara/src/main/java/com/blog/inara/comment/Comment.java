package com.blog.inara.comment;

import java.time.LocalDateTime;

import com.blog.inara.board.Board;
import com.blog.inara.member.Member;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="member")
@Data
public class Comment {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idcomment;

	@Column(nullable=false)
	private String content;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idmember",nullable=false)
	private Member member;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idboard",nullable=false)
	private Board board;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idparent")
	private Comment parent; // 대댓글
	
	@Column
	private LocalDateTime regdate;

}
