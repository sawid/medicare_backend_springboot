package com.medicare_backend.medicare_backend.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.medicare_backend.medicare_backend.schema.relationship.Appointment;
import com.medicare_backend.medicare_backend.service.AppointmentService;

@RestController
public class AppointmentController {
    
    private AppointmentService appointmentService;

    @Autowired
    public AppointmentController(AppointmentService appointmentService){
        this.appointmentService = appointmentService;
    }

    @GetMapping(path = "/appointments")
    public ResponseEntity<?> getAppointment(){
        List<Appointment> data = appointmentService.getAppointment();
        if(!(data != null && data.isEmpty())){
            return ResponseEntity.ok().body(data);
        } else {
            return ResponseEntity.status(500).body("Appointment List Not Found");
        }
    }

    @GetMapping(path = "/appointments/findbyId/{id}")
    public ResponseEntity<?> getAppointmentById(@PathVariable("id") Long appointmentId){
        Optional<Appointment> data = appointmentService.getAppointmentById(appointmentId);
        if(data.isPresent()){
            return ResponseEntity.ok().body(data);
        } else {
            return ResponseEntity.status(500).body("Appointment with Id : " + appointmentId + " Not Found");
        }
    }

    @GetMapping(path = "/appointments/findbyappointmentScheduleId/{id}") //don't finish
    public ResponseEntity<?> getPatientByScheduleId(@PathVariable("id") int appointmentScheduleId){
        List<Appointment> dataAp = appointmentService.getAppointmentByScheduleId(appointmentScheduleId);
        List<Integer> data = new ArrayList<>(); //List of patientId
        if(dataAp != null && !dataAp.isEmpty()){
            for(Appointment a : dataAp){
            data.add(a.getAppointmentPatientId());
            }
            return ResponseEntity.ok().body(data);
        }
        else {
            return ResponseEntity.status(500).body("This Schedule don't have Patient");
        }
    }

    @PostMapping(path = "/appointments/createNewAppointment")
    public ResponseEntity<?> createNewAppointment(@RequestBody Appointment appointment) {
        String data = appointmentService.createNewAppointment(appointment);
        if (data == "Create Success") {
            return ResponseEntity.ok().body(data);
        } else {
            return ResponseEntity.status(500).body(data);
        }
    }
}