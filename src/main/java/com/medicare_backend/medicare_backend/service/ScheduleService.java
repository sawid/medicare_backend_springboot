package com.medicare_backend.medicare_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medicare_backend.medicare_backend.repository.ScheduleRepository;
import com.medicare_backend.medicare_backend.schema.entity.Schedule;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class ScheduleService {
    
    private ScheduleRepository scheduleRepository;

    @Autowired
    public ScheduleService(ScheduleRepository scheduleRepository){
        this.scheduleRepository = scheduleRepository;
    }

    //Get all Schedule
    public List<Schedule> getSchedule() {
        return scheduleRepository.findAll();
    }

    //Get schedule by Id
    public Optional<Schedule> getScheduleById(Long scheduleId) {
        return scheduleRepository.findById(scheduleId);
    }

    //Post create new schedule //Still not check duplicate Schedule
    public String createNewSchedule(Schedule schedule) {
        //check isscheduletaken by Location Date and Starttime
        // Optional<Schedule> scheduleOptionalLocation = scheduleRepository.findScheduleByscheduleLocation(schedule.getScheduleLocation());
        // Optional<Schedule> scheduleOptionalDate = scheduleRepository.findScheduleByscheduleDate(schedule.getScheduleDate());
        // Optional<Schedule> scheduleOptionalStart = scheduleRepository.findScheduleByscheduleStart(schedule.getScheduleStart());
        // if(scheduleOptionalStart.isPresent()){
        //     return "ERROR";
        // }
        scheduleRepository.save(schedule);
        return "Create Success";
    }

}
