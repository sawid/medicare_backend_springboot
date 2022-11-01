package com.medicare_backend.medicare_backend.schema.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "schedule")
public class Schedule {
    private long scheduleId;
    private int scheduleCapacity;
    private LocalDateTime scheduleStart;
    private LocalDateTime scheduleEnd;
    private LocalDate scheduleDate;
    private String scheduleLocation;
    
    public Schedule() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getScheduleId() {
        return this.scheduleId;
    }

    public void setScheduleId(long scheduleId) {
        this.scheduleId = scheduleId;
    }

    @Column(name = "schedule_capacity", nullable = false)
    public int getScheduleCapacity() {
        return scheduleCapacity;
    }

    public void setScheduleCapacity(int scheduleCapacity) {
        this.scheduleCapacity = scheduleCapacity;
    }

    @Column(name = "schedule_date", nullable = false)
    public LocalDate getScheduleDate() {
        return scheduleDate;
    }

    public void setScheduleDate(LocalDate scheduleDate) {
        this.scheduleDate = scheduleDate;
    }

    @Column(name = "schedule_end", nullable = false)
    public LocalDateTime getScheduleEnd() {
        return scheduleEnd;
    }

    public void setScheduleEnd(LocalDateTime scheduleEnd) {
        this.scheduleEnd = scheduleEnd;
    }

    @Column(name = "schedule_start", nullable = false)
    public LocalDateTime getScheduleStart() {
        return scheduleStart;
    }

    public void setScheduleStart(LocalDateTime scheduleStart) {
        this.scheduleStart = scheduleStart;
    }

    @Column(name = "schedule_location", nullable = false)
    public String getScheduleLocation() {
        return scheduleLocation;
    }

    public void setScheduleLocation(String scheduleLocation) {
        this.scheduleLocation = scheduleLocation;
    }

    @Override
    public String toString() {
        return "String of Schedule";
    }

}
