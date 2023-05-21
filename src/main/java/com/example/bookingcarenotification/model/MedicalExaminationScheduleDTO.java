package com.example.bookingcarenotification.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class MedicalExaminationScheduleDTO {

    private Long id;

    private String nameScheduler;

    private String mailScheduler;

    private String nameHospital;

    private String nameDoctor;

    private String medicalExaminationDay;

    private String medicalExaminationTime;

    private String emailDoctor;
}
