package com.example.bookingcarenotification.entity;

import javax.persistence.*;

@Entity
@Table(name="comment")
public class CommentEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String content;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}


	@ManyToOne
	@JoinColumn(name = "userId")
	private UserEntity user;
	
	@ManyToOne
	@JoinColumn(name = "handbookId")
	private HandbookEntity handbook;
	public UserEntity getUser() {
		return user;
	}
	public void setUser(UserEntity user) {
		this.user = user;
	}
	public HandbookEntity getHandbook() {
		return handbook;
	}
	public void setHandbook(HandbookEntity handbook) {
		this.handbook = handbook;
	}
	
	
	
	
	
}
