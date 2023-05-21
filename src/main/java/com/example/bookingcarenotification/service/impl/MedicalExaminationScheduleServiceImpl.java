package com.example.bookingcarenotification.service.impl;

import com.example.bookingcarenotification.common.CodeConstant;
import com.example.bookingcarenotification.model.MedicalExaminationScheduleDTO;
import com.example.bookingcarenotification.repository.MedicalExaminationScheduleRepository;
import com.example.bookingcarenotification.service.MedicalExaminationScheduleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class MedicalExaminationScheduleServiceImpl implements MedicalExaminationScheduleService {

    private final Logger log = LoggerFactory.getLogger(MedicalExaminationScheduleServiceImpl.class);

    @Autowired
    private MedicalExaminationScheduleRepository repository;

    @Override
    public List<MedicalExaminationScheduleDTO> findAllSchedulerTakePlaceTomorrow() {
        log.debug("Request to findAllSchedulerTakePlaceTomorrow");
        Date date = addDate(new Date(), 1);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        List<MedicalExaminationScheduleDTO> medicalExaminationScheduleDTOList = repository.findAllSchedulerByDateAndStatus(formatter.format(date), CodeConstant.MEDICAL_SCHEDULE_IS_NOT_COMPLETE);
        return medicalExaminationScheduleDTOList;
    }

    @Override
    public List<MedicalExaminationScheduleDTO> findAllSchedulerStartTwoDayAwayAndNotPayment() {
        log.debug("Request to findAllSchedulerStartTwoDayAway");
        Date date = addDate(new Date(), 2);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        List<MedicalExaminationScheduleDTO> medicalExaminationScheduleDTOs =
                repository.findAllSchedulerByDateAndStatusAndStatusPayment(formatter.format(date), CodeConstant.MEDICAL_SCHEDULE_IS_NOT_COMPLETE, CodeConstant.payment_unPaid, "on");
        return medicalExaminationScheduleDTOs;
    }

    @Override
    public List<MedicalExaminationScheduleDTO> findAllSchedulerStartOneDayAwayAndNotPayment() {
        log.debug("Request to findAllSchedulerStartOneDayAway And Not Payment");
        Date date = addDate(new Date(), 1);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        List<MedicalExaminationScheduleDTO> medicalExaminationScheduleDTOs =
                repository.findAllSchedulerByDateAndStatusAndStatusPayment(formatter.format(date), CodeConstant.MEDICAL_SCHEDULE_IS_NOT_COMPLETE, CodeConstant.payment_unPaid, "on");
        return medicalExaminationScheduleDTOs;
    }

    @Override
    public List<MedicalExaminationScheduleDTO> findAllSchedulerByIds(List<Long> ids) {
        log.debug("Request to findAllSchedulerByIds");
        List<MedicalExaminationScheduleDTO> medicalExaminationScheduleDTOList = repository.findAllSchedulerByIds(ids);
        return medicalExaminationScheduleDTOList;
    }

    @Override
    public void cancelMedicalByIds(List<Long> ids) {
        log.debug("Request to cancelMedicalByIds");
        repository.updateByStatusPayment(CodeConstant.MEDICAL_SCHEDULE_IS_CANCEL, ids);
    }

    private Date addDate(Date date, Integer totalDate) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, totalDate);
        return c.getTime();
    }
}
