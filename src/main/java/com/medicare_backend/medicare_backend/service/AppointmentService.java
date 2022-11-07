package com.medicare_backend.medicare_backend.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

    //Update Appointment from Schedule
    @Transactional
    public void updateApponimentFromSchedule(long scheduleId, 
                                            LocalDateTime appointmentTimeStart, 
                                            LocalDateTime appointmentTimeEnd,
                                            LocalDate appointmentDate, 
                                            String appointmentLocation, 
                                            long appointmentDoctorId) {
        List<Appointment> appointments = getAppointmentByScheduleId(scheduleId);
        if(appointments == null || appointments.isEmpty()){
            //return have no appointment with this scheduleId
        }
        
        for(Appointment a : appointments){
            if(appointmentTimeStart != null && !Objects.equals(a.getAppiontmentTimeStart(), appointmentTimeStart)){
                a.setAppiontmentTimeStart(appointmentTimeStart);
                //add sth to list                
            }
            if(appointmentTimeEnd != null && !Objects.equals(a.getAppiontmentTimeEnd(), appointmentTimeEnd)){
                a.setAppiontmentTimeEnd(appointmentTimeEnd);
                //add sth to list                
            }
            if(appointmentDate != null && !Objects.equals(a.getAppointmentDate(), appointmentDate)){
                a.setAppointmentDate(appointmentDate);
                //add sth to list                
            }
            if(appointmentLocation != null && appointmentLocation.length() > 0 && !Objects.equals(a.getAppiontmentLocation(), appointmentLocation)){
                a.setAppiontmentLocation(appointmentLocation);
                //add sth to list  
            }
            if(appointmentDoctorId != 0 && !Objects.equals(a.getAppointmentDoctorId(), appointmentDoctorId)){
                a.setAppointmentDoctorId(appointmentDoctorId);
                //add sth to list  
            }
        }
        //return list of appointment or list of patientId that have update (for do notification)
        
    }
}
