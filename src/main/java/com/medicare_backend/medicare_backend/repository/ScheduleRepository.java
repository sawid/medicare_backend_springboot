package com.medicare_backend.medicare_backend.repository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import com.medicare_backend.medicare_backend.schema.entity.Schedule;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule,Long> {

    Optional<Schedule> findScheduleByscheduleLocation(String scheduleLocation);

    Optional<Schedule> findScheduleByscheduleDate(LocalDate scheduleDate);

    Optional<Schedule> findScheduleByscheduleStart(LocalDateTime scheduleStart);
    
}
