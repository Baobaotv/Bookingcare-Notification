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
        Date date = addDate(new Date());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        List<MedicalExaminationScheduleDTO> medicalExaminationScheduleDTOList = repository.findAllSchedulerByDateAndStatus(formatter.format(date), CodeConstant.MEDICAL_SCHEDULE_IS_NOT_COMPLETE);
        return medicalExaminationScheduleDTOList;
    }

    @Override
    public List<MedicalExaminationScheduleDTO> findAllSchedulerByIds(List<Long> ids) {
        log.debug("Request to findAllSchedulerByIds");
        List<MedicalExaminationScheduleDTO> medicalExaminationScheduleDTOList = repository.findAllSchedulerByIds(ids);
        return medicalExaminationScheduleDTOList;
    }

    private Date addDate(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, 1);
        return c.getTime();
    }
}
