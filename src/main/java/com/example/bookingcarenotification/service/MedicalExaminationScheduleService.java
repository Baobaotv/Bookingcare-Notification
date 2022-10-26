package com.example.bookingcarenotification.service;

import com.example.bookingcarenotification.model.MedicalExaminationScheduleDTO;

import java.util.List;

public interface MedicalExaminationScheduleService {

    List<MedicalExaminationScheduleDTO> findAllSchedulerTakePlaceTomorrow();

    List<MedicalExaminationScheduleDTO> findAllSchedulerByIds(List<Long> ids);

}
