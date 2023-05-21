package com.example.bookingcarenotification.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "medicalExaminationSchedule")
public class MedicalExaminationScheduleEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "date")
	private String date;

	@Column(name = "workTimeId")
	private Long workTimeID;

	@ManyToOne
	@JoinColumn(name = "doctorId")
	private UserEntity doctor;

	private String nameScheduler;
	private String phoneScheduer;
	private String namePatient;
	private String sex;
	private String phonePatient;
	private String location;
	private String reason;
	private String yearOfBirth;
	private Integer status;// 0 đã xoá, 1 chưa xử lý(chưa khám), 2 đã khám
	private String type; //on khám online, off là khám offline
	private String hospitalName;

	private Integer statusPayment;
	private Date createdDate;

	private Long examinationPrice;

	@ManyToOne
	@JoinColumn(name = "userId")
	private UserEntity user;
	


}
