package com.example.bookingcarenotification.repository;

import com.example.bookingcarenotification.entity.MedicalExaminationScheduleEntity;
import com.example.bookingcarenotification.model.MedicalExaminationScheduleDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface MedicalExaminationScheduleRepository extends JpaRepository<MedicalExaminationScheduleEntity, Long> {

    @Query(value = "SELECT " +
            "new com.example.bookingcarenotification.model.MedicalExaminationScheduleDTO(m.id, m.user.fullName, m.user.email, m.hospitalName, m.doctor.fullName, m.date, w.time, m.doctor.email) " +
            "FROM MedicalExaminationScheduleEntity m " +
            "INNER JOIN " +
            "WorkTimeEntity w on m.workTimeID = w.id WHERE m.date = :date AND m.status = :status"
            )
    List<MedicalExaminationScheduleDTO> findAllSchedulerByDateAndStatus(@Param("date") String date, @Param("status") Integer status);

    @Query(value = "SELECT " +
            "new com.example.bookingcarenotification.model.MedicalExaminationScheduleDTO(m.id, m.user.fullName, m.user.email, m.hospitalName, m.doctor.fullName, m.date, w.time, m.doctor.email) " +
            "FROM MedicalExaminationScheduleEntity m " +
            "INNER JOIN " +
            "WorkTimeEntity w on m.workTimeID = w.id WHERE m.id in (:ids)"
    )
    List<MedicalExaminationScheduleDTO> findAllSchedulerByIds(@Param("ids") List<Long> ids);

    @Query(value = "SELECT " +
            "new com.example.bookingcarenotification.model.MedicalExaminationScheduleDTO(m.id, m.user.fullName, m.user.email, m.hospitalName, m.doctor.fullName, m.date, w.time, m.doctor.email) " +
            "FROM MedicalExaminationScheduleEntity m " +
            "INNER JOIN " +
            "WorkTimeEntity w on m.workTimeID = w.id " +
            "WHERE m.date = :date AND m.status = :status AND m.statusPayment = :statusPayment AND m.type = :type"
    )
    List<MedicalExaminationScheduleDTO> findAllSchedulerByDateAndStatusAndStatusPayment(@Param("date") String date,
                                                                                        @Param("status") Integer status,
                                                                                        @Param("statusPayment") Integer statusPayment,
                                                                                        @Param("type") String type);

    @Transactional
    @Modifying
    @Query(value = "UPDATE medical_examination_schedule SET status = :status WHERE id in :ids", nativeQuery = true)
    Integer updateByStatusPayment(@Param("status") Integer status,@Param("ids") List<Long> ids);

}
