package com.example.bookingcarenotification.service.impl;

import com.example.bookingcarenotification.common.CodeConstant;
import com.example.bookingcarenotification.common.MailUtil;
import com.example.bookingcarenotification.model.MedicalExaminationScheduleDTO;
import com.example.bookingcarenotification.model.NotificationChangeTimeDTO;
import com.example.bookingcarenotification.model.NotificationResetPasswordDTO;
import com.example.bookingcarenotification.notification.KafkaListen;
import com.example.bookingcarenotification.service.MailService;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

        if (typeNotification.equals(CodeConstant.NOTIFICATION_TYPE_CANCEL_MEDICAL)) {
            sendMailToDoctorAndSchedule(medicalExaminationScheduleDTOList, file, body);
        } else {
            sendMailToSchedule(medicalExaminationScheduleDTOList, file, body);
        }
    }


    @Override
    public void sendMailResetPassword(NotificationResetPasswordDTO notificationResetPasswordDTO) throws IOException {
        String body = MailUtil.readBodyFromMailTemplate(CodeConstant.NOTIFICATION_RESET_PASS_TOPIC);
        String newBody = handleBodyBeforeSendMailResetPass(notificationResetPasswordDTO, body);
        try {
            MailUtil.sendMail(notificationResetPasswordDTO.getEmail(), newBody, emailSender, true);
        } catch (MessagingException e) {
            log.error(e.getMessage(), e.getStackTrace());
        }
    }

    @Override
    public void sendMailChangeTime(NotificationChangeTimeDTO notificationChangeTimeDTO) throws IOException {
        String body = MailUtil.readBodyFromMailTemplate(CodeConstant.NOTIFICATION_CHANGE_TIME_TOPIC);
        String newBody = handleBodyBeforeSendMailChangeTime(notificationChangeTimeDTO, body);
        try {
            MailUtil.sendMail(notificationChangeTimeDTO.getUserEmail(), newBody, emailSender, true);
            MailUtil.sendMail(notificationChangeTimeDTO.getNewDoctorEmail(), newBody, emailSender, true);
            if (!notificationChangeTimeDTO.getNewDoctorEmail().equals(notificationChangeTimeDTO.getOlrDoctorEmail())){
                MailUtil.sendMail(notificationChangeTimeDTO.getOlrDoctorEmail(), newBody, emailSender, true);
            }
        } catch (MessagingException e) {
            log.error(e.getMessage(), e.getStackTrace());
        }
    }

    private String handleBodyBeforeSendMail(MedicalExaminationScheduleDTO scheduleDTO,String body, String file) {
        String newBody = body.replace("__FULLNAME_SCHEDULE__", getValue(scheduleDTO.getNameScheduler()))
                .replace("__DATE_SCHEDULE__", getValue(scheduleDTO.getMedicalExaminationDay()))
                .replace("__TIME_SCHEDULE__", getValue(scheduleDTO.getMedicalExaminationTime()))
                .replace("__DOCTOR_SCHEDULE__", getValue(scheduleDTO.getNameDoctor()))
                .replace("__HOSPITAL_SCHEDULE__", getValue(scheduleDTO.getNameHospital()));
        return Strings.isBlank(file) ? newBody : newBody.replace("__FILE_SCHEDULE__", file);
    }

    private String handleBodyBeforeSendMailCancel(MedicalExaminationScheduleDTO scheduleDTO, String name,String body, String file) {
        String newBody = body.replace("__FULL_NAME__", getValue(name))
                .replace("__DATE_SCHEDULE__", getValue(scheduleDTO.getMedicalExaminationDay()))
                .replace("__TIME_SCHEDULE__", getValue(scheduleDTO.getMedicalExaminationTime()));
        return newBody;
    }

    private String handleBodyBeforeSendMailResetPass(NotificationResetPasswordDTO dto,String body) {
        String newBody = body.replace("__FULL_NAME__", getValue(dto.getFullName()))
                .replace("__LINK__", getValue(dto.getUrl()));
        return newBody;
    }

    private String handleBodyBeforeSendMailChangeTime(NotificationChangeTimeDTO dto,String body) {
        String newBody = body.replace("__DATE_SCHEDULE__", getValue(dto.getDate()))
                .replace("__TIME_SCHEDULE__", getValue(dto.getWorkTime()))
                        .replace("__DOCTOR_SCHEDULE__", getValue(dto.getNewDoctorName()))
                        .replace("__HOSPITAL_SCHEDULE__", getValue(dto.getHospitalName()));
        return newBody;
    }

    private void sendMailToSchedule(List<MedicalExaminationScheduleDTO> medicalExaminationScheduleDTOList, String file, String body) {
        for (MedicalExaminationScheduleDTO dto : medicalExaminationScheduleDTOList) {
            if (Strings.isBlank(dto.getMailScheduler())) continue;
            String newBody = handleBodyBeforeSendMail(dto, body, file);
            try {
                MailUtil.sendMail(dto.getMailScheduler(), newBody, emailSender, true);
            } catch (MessagingException e) {
                log.error(e.getMessage(), e.getStackTrace());
            }
        }
    }

    private void sendMailToDoctorAndSchedule(List<MedicalExaminationScheduleDTO> medicalExaminationScheduleDTOList, String file, String body) {
        for (MedicalExaminationScheduleDTO dto : medicalExaminationScheduleDTOList) {
            if (Strings.isBlank(dto.getMailScheduler())) continue;
            String newBodyForDoctor = handleBodyBeforeSendMailCancel(dto, dto.getNameDoctor(), body, file);
            String newBodyForUser = handleBodyBeforeSendMailCancel(dto, dto.getNameScheduler(), body, file);
            try {
                MailUtil.sendMail(dto.getEmailDoctor(), newBodyForDoctor, emailSender, true);
                MailUtil.sendMail(dto.getMailScheduler(), newBodyForUser, emailSender, true);
            } catch (MessagingException e) {
                log.error(e.getMessage(), e.getStackTrace());
            }
        }
    }

    private String getValue(String text) {
        return Strings.isBlank(text) ? "" : text;
    }
}
