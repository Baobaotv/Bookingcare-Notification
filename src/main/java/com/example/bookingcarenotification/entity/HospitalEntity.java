package com.example.bookingcarenotification.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;



@Entity
@Table(name = "hospital")
public class HospitalEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	private String location;
	
	private String description;
	
	private String img;
	
	private Integer status;

	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	@OneToMany(mappedBy = "hospital",fetch = FetchType.LAZY)
	private List<UserEntity> lstuser=new ArrayList<UserEntity>();
	
	public List<UserEntity> getLstuser() {
		return lstuser;
	}

	public void setLstuser(List<UserEntity> lstuser) {
		this.lstuser = lstuser;
	}

	public String getDescription() {
		return description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public HospitalEntity() {
		super();
	}
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
}
