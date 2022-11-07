package com.medicare_backend.medicare_backend.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.medicare_backend.medicare_backend.schema.entity.Patient;
import com.medicare_backend.medicare_backend.schema.relationship.Appointment;
import com.medicare_backend.medicare_backend.service.AppointmentService;
import com.medicare_backend.medicare_backend.service.PatientService;

@RestController
public class AppointmentController {

    private AppointmentService appointmentService;
    private PatientService patientService;

    @Autowired
    public AppointmentController(AppointmentService appointmentService,
            PatientService patientService) {
        this.appointmentService = appointmentService;
        this.patientService = patientService;
    }

    @GetMapping(path = "/appointments")
    public ResponseEntity<?> getAppointment() {
        List<Appointment> data = appointmentService.getAppointment();
        if (!(data != null && data.isEmpty())) {
            return ResponseEntity.ok().body(data);
        } else {
            return ResponseEntity.status(500).body("Appointment List Not Found");
        }
    }

    @GetMapping(path = "/appointments/findbyId/{id}")
    public ResponseEntity<?> getAppointmentById(@PathVariable("id") Long appointmentId) {
        Optional<Appointment> data = appointmentService.getAppointmentById(appointmentId);
        if (data.isPresent()) {
            return ResponseEntity.ok().body(data);
        } else {
            return ResponseEntity.status(500).body("Appointment with Id : " + appointmentId + " Not Found");
        }
    }

    @GetMapping(path = "/appointments/findbyappointmentScheduleId/{id}") // finish
    public ResponseEntity<?> getPatientByScheduleId(@PathVariable("id") long appointmentScheduleId) {
        List<Appointment> dataAp = appointmentService.getAppointmentByScheduleId(appointmentScheduleId); // List of
                                                                                                         // appointment
        List<JSONObject> data = new ArrayList<>(); // List of JSONdata
        if (dataAp != null && !dataAp.isEmpty()) {
            for (Appointment a : dataAp) {
                Optional<Patient> patient = patientService.getPatientById(a.getAppointmentPatientId());
                JSONObject object = new JSONObject();
                object.put("appointmentDate", a.getAppointmentDate());
                object.put("appointmentTimeStart", a.getAppiontmentTimeStart());
                object.put("appointmentTimeEnd", a.getAppiontmentTimeEnd());
                object.put("patientFirstName", patient.get().getPatientFirstName());
                object.put("patientMiddleName", patient.get().getPatientMiddleName());
                object.put("patientLastName", patient.get().getPatientLastName());
                data.add(object);
            }
            return ResponseEntity.ok().body(data);
        } else {
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