package com.blog.inara.board;

import java.time.LocalDateTime;

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
@Table(name="board")
@Data
public class Board {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idboard;
	
	@Column(nullable=false)
	private String title;
	
	@Column(nullable=false, columnDefinition= "LONGTEXT")
	private String content;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idmember",nullable=false)
	private Member member;
	
	@Column
	private LocalDateTime regdate;
	
	private int viewCount = 0;
}