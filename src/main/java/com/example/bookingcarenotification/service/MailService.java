package com.example.bookingcarenotification.service;

import com.example.bookingcarenotification.model.MedicalExaminationScheduleDTO;

import java.io.IOException;
import java.util.List;

public interface MailService {
    void sendMail(List<MedicalExaminationScheduleDTO> medicalExaminationScheduleDTOList, String notificationType) throws IOException;
}
