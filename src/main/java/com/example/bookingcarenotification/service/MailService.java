package com.example.bookingcarenotification.service;

import com.example.bookingcarenotification.model.MedicalExaminationScheduleDTO;
import com.example.bookingcarenotification.model.NotificationChangeTimeDTO;
import com.example.bookingcarenotification.model.NotificationResetPasswordDTO;

import java.io.IOException;
import java.util.List;

public interface MailService {
    void sendMail(List<MedicalExaminationScheduleDTO> medicalExaminationScheduleDTOList, String notificationType, String file) throws IOException;

    void sendMailResetPassword(NotificationResetPasswordDTO notificationResetPasswordDTO) throws IOException;

    void sendMailChangeTime(NotificationChangeTimeDTO notificationChangeTimeDTO) throws IOException;

}
