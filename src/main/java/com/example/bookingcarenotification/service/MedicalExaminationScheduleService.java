package com.example.bookingcarenotification.service;

import com.example.bookingcarenotification.model.MedicalExaminationScheduleDTO;

import java.util.List;

public interface MedicalExaminationScheduleService {

    List<MedicalExaminationScheduleDTO> findAllSchedulerTakePlaceTomorrow();

    List<MedicalExaminationScheduleDTO> findAllSchedulerStartTwoDayAwayAndNotPayment();

    List<MedicalExaminationScheduleDTO> findAllSchedulerStartOneDayAwayAndNotPayment();

    List<MedicalExaminationScheduleDTO> findAllSchedulerByIds(List<Long> ids);

    void cancelMedicalByIds(List<Long> ids);

}
