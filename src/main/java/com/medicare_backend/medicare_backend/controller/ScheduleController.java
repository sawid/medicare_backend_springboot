package com.medicare_backend.medicare_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.medicare_backend.medicare_backend.schema.entity.Schedule;
import com.medicare_backend.medicare_backend.schema.request.UpdateSchedule;
import com.medicare_backend.medicare_backend.service.ScheduleService;
import com.medicare_backend.medicare_backend.service.AppointmentService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
public class ScheduleController {

    private ScheduleService scheduleService;
    private AppointmentService appointmentService;

    @Autowired
    public ScheduleController(ScheduleService scheduleService,
            AppointmentService appointmentService) {
        this.scheduleService = scheduleService;
        this.appointmentService = appointmentService;
    }

    @GetMapping(path = "/schedules")
    public ResponseEntity<?> getSchedule() {
        try {
            List<Schedule> data = scheduleService.getSchedule();
            if (!(data != null && data.isEmpty())) {
                return ResponseEntity.ok().body(data);
            } else {
                return ResponseEntity.status(500).body("Schedule List Not Found");
            }
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(500).body("server error");
        }
    }

    @GetMapping(path = "/schedules/findbyId/{id}")
    public ResponseEntity<?> getScheduleById(@PathVariable("id") Long scheduleId) {
        try {
            Optional<Schedule> data = scheduleService.getScheduleById(scheduleId);
            if (data.isPresent()) {
                return ResponseEntity.ok().body(data);
            } else {
                return ResponseEntity.status(500).body("Schedule with Id : " + scheduleId + " Not Found");
            }
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(500).body("server error");
        }
    }

    @PostMapping(path = "/schedules/createNewSchedule")
    public ResponseEntity<?> createNewSchedule(@RequestBody Schedule schedule) {
        try {
            String data = scheduleService.createNewSchedule(schedule);
            if (data == "Create Success") {
                return ResponseEntity.ok().body(data);
            } else {
                return ResponseEntity.status(500).body(data);
            }
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(500).body("server error");
        }
    }

    @PutMapping(path = "/schedules/update") // not finish //not check busy schedule/application yet
    public ResponseEntity<?> updateSchedule(@RequestBody UpdateSchedule schedule) {
        try {
            // update appointment , return list of patientHNId of edited appointment <-- can
            // empty
            List<Long> patientHNId = appointmentService
                    .updateApponimentFromSchedule(schedule.getScheduleId(),
                            schedule.getScheduleStart(),
                            schedule.getScheduleEnd(),
                            schedule.getScheduleDate(),
                            schedule.getScheduleLocation(),
                            schedule.getAppointmentDoctorId());

            // update schedule
            scheduleService.updateSchedule(schedule.getScheduleId(),
                    schedule.getScheduleCapacity(),
                    schedule.getScheduleStart(),
                    schedule.getScheduleEnd(),
                    schedule.getScheduleDate(),
                    schedule.getScheduleLocation());
            if (patientHNId.isEmpty()) { // if list of patientHNId of edited appointment is empty
                return ResponseEntity.ok()
                        .body("Schedule with ID: " + schedule.getScheduleId() + " has update successfully");
            }
            return ResponseEntity.ok().body(patientHNId);
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(500).body("server error");
        }
    }
}
