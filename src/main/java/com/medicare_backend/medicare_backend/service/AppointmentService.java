package com.medicare_backend.medicare_backend.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

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
        List<Appointment> appointments = new ArrayList<>();
        try {
            appointments = appointmentRepository.findAll();
        } catch (Exception e) {
            System.out.println(e);
        }
        return appointments;
    }

    //Get Appointment by Id
    public Optional<Appointment> getAppointmentById(Long appointmentId) {
        Optional<Appointment> appointment = Optional.empty();
        try {
            appointment = appointmentRepository.findById(appointmentId);
        } catch (Exception e) {
            System.out.println(e);
        }
        return appointment;
    }

    //Create new Appointment
    public String createNewAppointment(Appointment appointment) {
        try{
        appointmentRepository.save(appointment);
        } catch(Exception e){
            System.out.println(e);
        }
        return "Create Success";
    }

    //Get Appointment by ScheduleId
    public List<Appointment> getAppointmentByScheduleId(long appointmentScheduleId) {
        List<Appointment> appointments = new ArrayList<>();
        try {
            appointments = appointmentRepository.findAppointmentByappointmentScheduleId(appointmentScheduleId);
        } catch (Exception e) {
            System.out.println(e);
        }
        return appointments;
    }

    //Update Appointment from Schedule
    @Transactional
    public List<Long> updateApponimentFromSchedule(long scheduleId, 
                                            LocalDateTime appointmentTimeStart, 
                                            LocalDateTime appointmentTimeEnd,
                                            LocalDate appointmentDate, 
                                            String appointmentLocation, 
                                            long appointmentDoctorId) {
        List<Appointment> appointments = getAppointmentByScheduleId(scheduleId);
        List<Long> patientHNId = new ArrayList<>();
        try {
            if(appointments == null || appointments.isEmpty()){
                return patientHNId;
            }
        
            for(Appointment a : appointments){
                if(appointmentTimeStart != null && !Objects.equals(a.getAppiontmentTimeStart(), appointmentTimeStart)){
                    a.setAppiontmentTimeStart(appointmentTimeStart);
                    patientHNId.add(a.getAppointmentPatientId());              
                }
                if(appointmentTimeEnd != null && !Objects.equals(a.getAppiontmentTimeEnd(), appointmentTimeEnd)){
                    a.setAppiontmentTimeEnd(appointmentTimeEnd);
                    if(!patientHNId.contains(a.getAppointmentPatientId())){
                        patientHNId.add(a.getAppointmentPatientId());
                    }               
                }
                if(appointmentDate != null && !Objects.equals(a.getAppointmentDate(), appointmentDate)){
                    a.setAppointmentDate(appointmentDate);
                    if(!patientHNId.contains(a.getAppointmentPatientId())){
                        patientHNId.add(a.getAppointmentPatientId());
                    }                 
                }
                if(appointmentLocation != null && appointmentLocation.length() > 0 && !Objects.equals(a.getAppiontmentLocation(), appointmentLocation)){
                    a.setAppiontmentLocation(appointmentLocation);
                    if(!patientHNId.contains(a.getAppointmentPatientId())){
                        patientHNId.add(a.getAppointmentPatientId());
                    }    
                }
                if(appointmentDoctorId != 0 && !Objects.equals(a.getAppointmentDoctorId(), appointmentDoctorId)){
                    a.setAppointmentDoctorId(appointmentDoctorId);
                    if(!patientHNId.contains(a.getAppointmentPatientId())){
                        patientHNId.add(a.getAppointmentPatientId());
                    }    
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return patientHNId;
        
    }
}
