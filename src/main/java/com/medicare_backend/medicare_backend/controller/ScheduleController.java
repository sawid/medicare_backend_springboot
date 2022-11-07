package com.medicare_backend.medicare_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.medicare_backend.medicare_backend.schema.entity.Schedule;
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
    public ScheduleController(  ScheduleService scheduleService,
                                AppointmentService appointmentService){
        this.scheduleService = scheduleService;
        this.appointmentService = appointmentService;
    }

    @GetMapping(path = "/schedules")
    public ResponseEntity<?> getSchedule(){
        List<Schedule> data = scheduleService.getSchedule();
        if(!(data != null && data.isEmpty())){
            return ResponseEntity.ok().body(data);
        } else {
            return ResponseEntity.status(500).body("Schedule List Not Found");
        }
    }

    @GetMapping(path = "/schedules/findbyId/{id}")
    public ResponseEntity<?> getScheduleById(@PathVariable("id") Long scheduleId){
        Optional<Schedule> data = scheduleService.getScheduleById(scheduleId);
        if(data.isPresent()){
            return ResponseEntity.ok().body(data);
        }else{
            return ResponseEntity.status(500).body("Schedule with Id : " + scheduleId + " Not Found");
        }
    }

    @PostMapping(path = "/schedules/createNewSchedule")
    public ResponseEntity<?> createNewSchedule(@RequestBody Schedule schedule) {
        String data = scheduleService.createNewSchedule(schedule);
        if (data == "Create Success") {
            return ResponseEntity.ok().body(data);
        } else {
            return ResponseEntity.status(500).body(data);
        }
    }

    @PutMapping(path = "/schedules/update/{id}") //not finish //not check duplicate schedule yet and not update to Appoinment yet
    public ResponseEntity<?> updateSchedule(
                @PathVariable("id") long scheduleId,
                @RequestParam(required = true) int scheduleCapacity, //can't update to be 0
                @RequestParam(required = false) LocalDateTime scheduleStart,
                @RequestParam(required = false) LocalDateTime scheduleEnd,
                @RequestParam(required = false) LocalDate scheduleDate,
                @RequestParam(required = false) String scheduleLocation,
                @RequestParam(required = true) long appointmentDoctorId //can't update to be 0 
                //if don't want to update DoctorId or Capacity sent param as 0
                //because long and int can't be null
                ){
        scheduleService.updateSchedule( scheduleId,
                                        scheduleCapacity,
                                        scheduleStart,
                                        scheduleEnd,
                                        scheduleDate,
                                        scheduleLocation);
        appointmentService.updateApponimentFromSchedule(scheduleId,
                                                        scheduleStart,
                                                        scheduleEnd,
                                                        scheduleDate,
                                                        scheduleLocation,
                                                        appointmentDoctorId);
        return ResponseEntity.ok().body("Schedule with ID: " + scheduleId + " has update successfully");
    }

}
