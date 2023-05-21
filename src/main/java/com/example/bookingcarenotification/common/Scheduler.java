package com.example.bookingcarenotification.common;

import com.example.bookingcarenotification.model.MedicalExaminationScheduleDTO;
import com.example.bookingcarenotification.service.MailService;
import com.example.bookingcarenotification.service.MedicalExaminationScheduleService;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class Scheduler {

    Logger log = LoggerFactory.getLogger(Scheduler.class);

    @Autowired
    private MedicalExaminationScheduleService medicalService;

    @Autowired
    private MailService mailService;

    @Scheduled(cron = "0 10 0 * * ?")
    public void sendMailAllMedicalStartInTomorrow() throws IOException {

        log.info("Start get all medical start in tomorrow");
        List<MedicalExaminationScheduleDTO> medicalExaminationScheduleDTOList =
                medicalService.findAllSchedulerTakePlaceTomorrow()
                        .stream()
                        .filter(item -> !Strings.isBlank(item.getMailScheduler()))
                        .collect(Collectors.toList());
        log.info("Start send mail all medical start in tomorrow");
        mailService.sendMail(medicalExaminationScheduleDTOList,
                CodeConstant.NOTIFICATION_TYPE_USER_BOOKING_SCHEDULE,
                null);
    }

    @Scheduled(cron = "0 0 1 * * ?")
//@Scheduled(cron = "0 56 16 * * ?")
    public void sendMailAllMedicalNotPaymentWhenStartTowDayAway() throws IOException {
        log.info("Start get all medical start 2 day away and not payment");
        List<MedicalExaminationScheduleDTO> medicalExaminationScheduleDTOList =
                medicalService.findAllSchedulerStartTwoDayAwayAndNotPayment()
                        .stream()
                        .filter(item -> !Strings.isBlank(item.getMailScheduler()))
                        .collect(Collectors.toList());
        log.info("Start send mail all medical start in 2 day");
        mailService.sendMail(medicalExaminationScheduleDTOList,
                CodeConstant.NOTIFICATION_TYPE_REMIND_USER_COMPLETE_PAYMENT,
                null);
    }

    @Scheduled(cron = "0 0 2 * * ?")
//@Scheduled(cron = "0 58 16 * * ?")
    public void sendMailAllMedicalNotPaymentAndCancelMedical() throws IOException {
        log.info("Start get all medical start 1 day away and not payment");
        List<MedicalExaminationScheduleDTO> medicals =
                medicalService.findAllSchedulerStartOneDayAwayAndNotPayment();
        medicalService.cancelMedicalByIds(medicals.stream()
                .map(MedicalExaminationScheduleDTO::getId)
                        .filter(Objects::nonNull)
                .collect(Collectors.toList()));

        log.info("Start send mail all medical start in 1 day");
        mailService.sendMail(medicals,
                CodeConstant.NOTIFICATION_TYPE_CANCEL_MEDICAL,
                null);
    }
}
