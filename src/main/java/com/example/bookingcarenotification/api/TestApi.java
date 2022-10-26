package com.example.bookingcarenotification.api;

import com.example.bookingcarenotification.common.CodeConstant;
import com.example.bookingcarenotification.model.NotificationScheduleDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestApi {

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    @PostMapping("/test-kafka")
    public ResponseEntity<?> test(@RequestBody NotificationScheduleDTO scheduleDTO) throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(scheduleDTO);
        kafkaTemplate.send(CodeConstant.NOTIFICATION_TOPIC, json);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
