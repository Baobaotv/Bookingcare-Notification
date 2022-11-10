package com.example.bookingcarenotification.service.impl;

import com.example.bookingcarenotification.common.MailUtil;
import com.example.bookingcarenotification.model.MedicalExaminationScheduleDTO;
import com.example.bookingcarenotification.notification.KafkaListen;
import com.example.bookingcarenotification.service.MailService;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

@Service
public class MailServiceImpl implements MailService {

    private final Logger log = LoggerFactory.getLogger(KafkaListen.class);

    @Autowired
    private JavaMailSender emailSender;

    @Override
    public void sendMail(List<MedicalExaminationScheduleDTO> medicalExaminationScheduleDTOList, String typeNotification, String file) throws IOException {
        String body = MailUtil.readBodyFromMailTemplate(typeNotification);
        for (MedicalExaminationScheduleDTO dto : medicalExaminationScheduleDTOList) {
            if (Strings.isBlank(dto.getMailScheduler())) continue;
            String newBody = handleBodyBeforeSendMail(dto, body, file);
            try {
                MailUtil.sendMail(dto.getMailScheduler(), newBody, emailSender, true);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
    }

    private String handleBodyBeforeSendMail(MedicalExaminationScheduleDTO scheduleDTO,String body, String file) {
        String newBody = body.replace("__FULLNAME_SCHEDULE__", scheduleDTO.getNameScheduler())
                .replace("__DATE_SCHEDULE__", scheduleDTO.getMedicalExaminationDay())
                .replace("__TIME_SCHEDULE__", scheduleDTO.getMedicalExaminationTime())
                .replace("__DOCTOR_SCHEDULE__", scheduleDTO.getNameDoctor())
                .replace("__HOSPITAL_SCHEDULE__", scheduleDTO.getNameHospital());
        return Strings.isBlank(file) ? newBody : newBody.replace("__FILE_SCHEDULE__", file);
    }
}
