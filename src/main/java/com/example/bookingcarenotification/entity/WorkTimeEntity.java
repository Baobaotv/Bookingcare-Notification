package com.example.bookingcarenotification.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "workTime")
public class WorkTimeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "time")
	private String time;
	
	@ManyToMany(mappedBy = "workTimeEntity")
	private Set<UserEntity> userEntities;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Set<UserEntity> getUserEntities() {
		return userEntities;
	}

	public void setUserEntities(Set<UserEntity> userEntities) {
		this.userEntities = userEntities;
	}
	
	
	
	
	
}
