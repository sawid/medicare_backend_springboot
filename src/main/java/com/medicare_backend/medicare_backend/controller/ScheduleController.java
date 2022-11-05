package com.medicare_backend.medicare_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.medicare_backend.medicare_backend.schema.entity.Schedule;
import com.medicare_backend.medicare_backend.service.ScheduleService;

import java.util.List;
import java.util.Optional;


@RestController
public class ScheduleController {
    
    private ScheduleService scheduleService;

    @Autowired
    public ScheduleController(ScheduleService scheduleService){
        this.scheduleService = scheduleService;
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

}
