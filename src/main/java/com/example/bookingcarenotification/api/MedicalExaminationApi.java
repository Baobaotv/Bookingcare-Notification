package com.example.bookingcarenotification.api;

import com.example.bookingcarenotification.model.MedicalExaminationScheduleDTO;
import com.example.bookingcarenotification.service.MedicalExaminationScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@RestController()
@RequestMapping(value = "/api")
public class MedicalExaminationApi {

    @Autowired
    private MedicalExaminationScheduleService medicalServiceImpl;

    @GetMapping(value = "/get-scheduler-take-place-tomorrow")
    public ResponseEntity<List<MedicalExaminationScheduleDTO>> findAllSchedulerTakePlaceTomorrow() throws IOException {
        List<MedicalExaminationScheduleDTO> medicalExaminationScheduleDTOList = medicalServiceImpl.findAllSchedulerTakePlaceTomorrow();
        return ResponseEntity.ok(medicalExaminationScheduleDTOList);
    }

}
