package com.medicare_backend.medicare_backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medicare_backend.medicare_backend.repository.AppointmentRepository;
import com.medicare_backend.medicare_backend.schema.relationship.Appointment;

@Service
public class AppointmentService {
    private AppointmentRepository appointmentRepository;

    @Autowired
    public AppointmentService(AppointmentRepository appointmentRepository){
        this.appointmentRepository = appointmentRepository;
    }

    //Get all Appointment
    public List<Appointment> getAppointment() {
        return appointmentRepository.findAll();
    }

    //Get Appointment by Id
    public Optional<Appointment> getAppointmentById(Long appointmentId) {
        return appointmentRepository.findById(appointmentId);
    }

    //Create new Appointment
    public String createNewAppointment(Appointment appointment) {
        appointmentRepository.save(appointment);
        return "Create Success";
    }

    //Get Appointment by ScheduleId
    public List<Appointment> getAppointmentByScheduleId(long appointmentScheduleId) {
        return appointmentRepository.findAppointmentByappointmentScheduleId(appointmentScheduleId);
    }
}
