package com.example.bookingcarenotification.notification;

import com.example.bookingcarenotification.common.CodeConstant;
import com.example.bookingcarenotification.model.MedicalExaminationScheduleDTO;
import com.example.bookingcarenotification.model.NotificationScheduleDTO;
import com.example.bookingcarenotification.service.MailService;
import com.example.bookingcarenotification.service.MedicalExaminationScheduleService;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class KafkaListen {

    private final Logger log = LoggerFactory.getLogger(KafkaListen.class);

    @Autowired
    private MedicalExaminationScheduleService medicalService;

    @Autowired
    private MailService mailService;

    @KafkaListener(topics = CodeConstant.NOTIFICATION_TOPIC)
    public void listen(String message) throws IOException {
        log.info("Kafka receive message from topic {}: {}", CodeConstant.NOTIFICATION_TOPIC, message);
        Gson gson = new Gson();
        NotificationScheduleDTO dto = gson.fromJson(message, NotificationScheduleDTO.class);
        List<MedicalExaminationScheduleDTO> medicalScheduleDTOS = medicalService.findAllSchedulerByIds(dto.getIds());
        mailService.sendMail(medicalScheduleDTOS, dto.getTypeNotification(), dto.getFile());
    }
}
