package com.example.bookingcarenotification.common;

import com.example.bookingcarenotification.model.MedicalExaminationScheduleDTO;
import com.example.bookingcarenotification.service.MailService;
import com.example.bookingcarenotification.service.MedicalExaminationScheduleService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class Scheduler {

    @Autowired
    private MedicalExaminationScheduleService medicalService;

    @Autowired
    private MailService mailService;

    @Scheduled(cron = "0 0 0 * * ?")
    public void refreshPricingParameters() throws IOException {
        List<MedicalExaminationScheduleDTO> medicalExaminationScheduleDTOList =
                medicalService.findAllSchedulerTakePlaceTomorrow()
                        .stream()
                        .filter(item -> !Strings.isBlank(item.getMailScheduler()))
                        .collect(Collectors.toList());
        mailService.sendMail(medicalExaminationScheduleDTOList);
    }
}
