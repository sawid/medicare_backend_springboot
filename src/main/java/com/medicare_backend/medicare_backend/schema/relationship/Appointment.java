package com.medicare_backend.medicare_backend.schema.relationship;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.*;

@Entity
@Table(name = "appointment")
public class Appointment {

    private long appointmentId;
    private int appointmentPatientId;
    private int appointmentDoctorId;
    private LocalDate appointmentDate;
    private String appiontmentLocation;
    private int appointmentScheduledId;
    private LocalDateTime appiontmentTimeStart;
    private LocalDateTime appiontmentTimeEnd;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
        public long getId() {
        return appointmentId;
    }
    public void setId(long id) {
        this.appointmentId = id;
    }

    public Appointment() {
    }

    public Appointment(int appointmentPatientId, int appointmentDoctorId, 
                    LocalDate appointmentDate, String appiontmentLocation, 
                    int appointmentScheduledId, LocalDateTime appiontmentTimeStart, 
                    LocalDateTime appiontmentTimeEnd) {
        this.appointmentPatientId = appointmentPatientId;
        this.appointmentDoctorId = appointmentDoctorId;
        this.appointmentDate = appointmentDate;
        this.appiontmentLocation = appiontmentLocation;
        this.appointmentScheduledId = appointmentScheduledId;
        this.appiontmentTimeStart = appiontmentTimeStart;
        this.appiontmentTimeEnd = appiontmentTimeEnd;
    }

    @Column(name = "appointmentId", nullable = false)
    public long getAppointmentId() {
        return this.appointmentId;
    }

    public void setAppointmentId(long appointmentId) {
        this.appointmentId = appointmentId;
    }

    @Column(name = "appointmentPatientId", nullable = false)
    public int getAppointmentPatientId() {
        return this.appointmentPatientId;
    }

    public void setAppointmentPatientId(int appointmentPatientId) {
        this.appointmentPatientId = appointmentPatientId;
    }

    @Column(name = "appointmentDoctorId", nullable = false)
    public int getAppointmentDoctorId() {
        return this.appointmentDoctorId;
    }

    public void setAppointmentDoctorId(int appointmentDoctorId) {
        this.appointmentDoctorId = appointmentDoctorId;
    }

    @Column(name = "appointmentDate", nullable = false)
    public LocalDate getAppointmentDate() {
        return this.appointmentDate;
    }

    public void setAppointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    @Column(name = "appiontmentLocation", nullable = false)
    public String getAppiontmentLocation() {
        return this.appiontmentLocation;
    }

    public void setAppiontmentLocation(String appiontmentLocation) {
        this.appiontmentLocation = appiontmentLocation;
    }

    @Column(name = "appointmentScheduledId", nullable = false)
    public int getAppointmentScheduledId() {
        return this.appointmentScheduledId;
    }

    public void setAppointmentScheduledId(int appointmentScheduledId) {
        this.appointmentScheduledId = appointmentScheduledId;
    }

    @Column(name = "appiontmentTimeStart", nullable = false)
    public LocalDateTime getAppiontmentTimeStart() {
        return this.appiontmentTimeStart;
    }

    public void setAppiontmentTimeStart(LocalDateTime appiontmentTimeStart) {
        this.appiontmentTimeStart = appiontmentTimeStart;
    }

    @Column(name = "appiontmentTimeEnd", nullable = false)
    public LocalDateTime getAppiontmentTimeEnd() {
        return this.appiontmentTimeEnd;
    }

    public void setAppiontmentTimeEnd(LocalDateTime appiontmentTimeEnd) {
        this.appiontmentTimeEnd = appiontmentTimeEnd;
    }
}
