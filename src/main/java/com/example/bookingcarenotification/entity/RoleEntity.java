package com.example.bookingcarenotification.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "role")
public class RoleEntity {
	
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

	public Set<UserEntity> getUserEntities() {
		return userEntities;
	}

	public void setUserEntities(Set<UserEntity> userEntities) {
		this.userEntities = userEntities;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	
	@ManyToMany(mappedBy = "roles")
	private Set<UserEntity> userEntities;
	
	
	
	

}
