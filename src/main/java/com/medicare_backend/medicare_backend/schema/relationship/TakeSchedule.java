package com.medicare_backend.medicare_backend.schema.relationship;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "takeschedule")
public class TakeSchedule {
    private long employeeId;
    private long scheduleId;
    private LocalDateTime takeScheduleDate;

    public TakeSchedule() {
        
    }

    @Column(name = "employee_id", nullable = false)
    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    @Column(name = "schedule_id", nullable = false)
    public long getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(long scheduleId) {
        this.scheduleId = scheduleId;
    }

    @Column(name = "take_schedule_date", nullable = false)
    public LocalDateTime getTakeScheduleDate() {
        return takeScheduleDate;
    }

    public void setTakeScheduleDate(LocalDateTime takeScheduleDate) {
        this.takeScheduleDate = takeScheduleDate;
    }

}
