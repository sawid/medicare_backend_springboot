package com.medicare_backend.medicare_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medicare_backend.medicare_backend.repository.ScheduleRepository;
import com.medicare_backend.medicare_backend.schema.entity.Schedule;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

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

    //update scheduleId
    @Transactional
    public void updateSchedule( long scheduleId, //not complete //not check duplicate schedule yet
                                int scheduleCapacity, 
                                LocalDateTime scheduleStart, 
                                LocalDateTime scheduleEnd,
                                LocalDate scheduleDate, 
                                String scheduleLocation) {
        Schedule schedule = scheduleRepository.findById(scheduleId)
            .orElseThrow(() -> new IllegalStateException(
                "Schedule with ID : " + scheduleId + " dose not exist"
            ));
        if(scheduleCapacity != 0 && !Objects.equals(schedule.getScheduleCapacity(), scheduleCapacity)){
            schedule.setScheduleCapacity(scheduleCapacity);
        }
        if(scheduleStart != null && !Objects.equals(schedule.getScheduleStart(), scheduleStart)){
            schedule.setScheduleStart(scheduleStart);
        }
        if(scheduleEnd != null && !Objects.equals(schedule.getScheduleEnd(), scheduleEnd)){
            schedule.setScheduleEnd(scheduleEnd);
        }
        if(scheduleDate != null && !Objects.equals(schedule.getScheduleDate(), scheduleDate)){
            schedule.setScheduleDate(scheduleDate);
        }
        if(scheduleLocation != null && scheduleLocation.length() > 0 && !Objects.equals(schedule.getScheduleLocation(), scheduleLocation)){
            schedule.setScheduleLocation(scheduleLocation);
        }
    }
}
