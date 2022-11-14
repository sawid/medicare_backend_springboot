package com.medicare_backend.medicare_backend.route;

import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.Gson;
import com.medicare_backend.medicare_backend.controller.EmployeeController;
import com.medicare_backend.medicare_backend.repository.AppointmentRepository;
import com.medicare_backend.medicare_backend.repository.EmployeeRepository;
import com.medicare_backend.medicare_backend.repository.PatientRepository;
import com.medicare_backend.medicare_backend.repository.ScheduleRepository;
import com.medicare_backend.medicare_backend.repository.TakeScheduleRepository;
import com.medicare_backend.medicare_backend.schema.entity.AuthenticationPatient;
import com.medicare_backend.medicare_backend.schema.entity.Employee;
import com.medicare_backend.medicare_backend.schema.entity.Patient;
import com.medicare_backend.medicare_backend.schema.entity.Schedule;
import com.medicare_backend.medicare_backend.schema.relationship.Appointment;
import com.medicare_backend.medicare_backend.schema.relationship.TakeSchedule;
import com.medicare_backend.medicare_backend.schema.entity.Patient;
import com.medicare_backend.medicare_backend.schema.relationship.Appointment;
import com.medicare_backend.medicare_backend.service.InternalPayload;
import com.medicare_backend.medicare_backend.service.PatientService;
import com.medicare_backend.medicare_backend.service.ScheduleService;
import com.medicare_backend.medicare_backend.service.TakeScheduleService;
import com.medicare_backend.medicare_backend.service.PatientService;

import java.util.ArrayList;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.json.simple.JSONObject;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

@RestController
public class EmployeeRoute {

    private PatientService patientService;
    @Autowired
    private EmployeeController employeeController;
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private TakeScheduleRepository takeScheduleRepository;

    @Autowired
    private ScheduleRepository scheduleRepository;

    @PostMapping("/employee/register")
    public ResponseEntity<?> registerEmployee(@RequestHeader("authtoken") String authtoken,
            @RequestBody Employee user) {
        InternalPayload data = employeeController.registerEmployee(authtoken, user);
        if (data.getStatusCode() == "0") {
            return ResponseEntity.ok().body(data);
        } else {
            return ResponseEntity.status(500).body(data);
        }

    }

    @PostMapping("/employee/login")
    public ResponseEntity<?> loginToEmployee(@RequestBody AuthenticationPatient auth) {
        InternalPayload data = employeeController.loginEmployee(auth);
        if (data.getStatusCode() == "0") {
            return ResponseEntity.ok().body(data);
        } else {
            return ResponseEntity.status(500).body(data);
        }

    }

    @PostMapping("/createEmployee")
    public ResponseEntity<?> createEmployee(@RequestBody Employee employee) {
        employeeController.saveEmployee(employee);
        return ResponseEntity.ok().body(employee);
    }

    @GetMapping(path = "/employee")
    public ResponseEntity<?> getEmployee() {
        List<Employee> data = employeeController.getEmployee();
        for (Employee _employee : data) {
            _employee.setEmployeePassword("maibokeiei");
        }
        if (!(data != null && data.isEmpty())) {
            return ResponseEntity.ok().body(data);
        } else {
            return ResponseEntity.status(500).body("Patient List Not Found");
        }
    }

    @GetMapping(path = "/employeeinfo/findbyId/{id}")
    public ResponseEntity<?> getEmployeeByID(@PathVariable("id") long employeeid) {
        Optional<Employee> data = employeeController.getEmployeeById(employeeid);
        data.get().setEmployeePassword("maibokeiei");
        if (!(data != null && !data.isPresent())) {
            return ResponseEntity.ok().body(data);
        } else {
            return ResponseEntity.status(500).body("Patient List Not Found");
        }
    }

    @PutMapping(path = "/employeeinfo/findbyId/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable("id") long employeeId, @RequestBody Employee employee) {
        try {
            Employee _employee = employeeController.updatEmployee(employeeId, employee);
            Employee updatEmployee = employeeController.saveEmployee(_employee);
            return ResponseEntity.ok().body(updatEmployee);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(employee);
        }
    }

    @GetMapping(path = "/employee/findappointment/{id}")
    public ResponseEntity<?> employeeAppointment(@PathVariable("id") long employeeId, @RequestBody Employee employee) {
        try {
            List<Appointment> _appointment = employeeController.getAppointmentbyEmployeeId(employeeId);
            List<JSONObject> data = new ArrayList<>();
            Integer patientCount = 0;
            if (_appointment != null && !_appointment.isEmpty()) {
                for (Appointment _data : _appointment) {
                    Optional<Patient> patient = patientService.getPatientById(_data.getAppointmentPatientId());
                    JSONObject object = new JSONObject();
                    object.put("appointmentDate", _data.getAppointmentDate());
                    object.put("appointmentTimeStart", _data.getAppiontmentTimeStart());
                    object.put("appointmentTimeEnd", _data.getAppiontmentTimeEnd());
                    object.put("patientFirstName", patient.get().getPatientFirstName());
                    object.put("patientMiddleName", patient.get().getPatientMiddleName());
                    object.put("patientLastName", patient.get().getPatientLastName());
                    data.add(object);
                    patientCount++;
                }
                JSONObject _patientcount = new JSONObject();
                _patientcount.put("patientcount", _patientcount);
                data.add(_patientcount);
                return ResponseEntity.ok().body(data);
            } else {
                return ResponseEntity.status(500).body("Appointmentid not found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body(employee);
        }
    }

    @GetMapping(path = "/getappointmentbyEmployee/{id}")
    public ResponseEntity<?> getAppointmentByEmployeeId(@PathVariable("id") long employeeId) {
        List<TakeSchedule> takeSchedule = takeScheduleRepository.findTakeScheduleByemployeeId(employeeId);
        if (takeSchedule == null || takeSchedule.isEmpty()) {
            return ResponseEntity.status(500).body("employee id " + employeeId + " is not exiting");
        }
        JSONObject _datajason = new JSONObject();
        for (TakeSchedule data : takeSchedule) {
            Optional<Schedule> schedule = scheduleRepository.findById(data.getScheduleId());
            List<Appointment> appointments = appointmentRepository
                    .findAppointmentByappointmentScheduleId(schedule.get().getScheduleId());
            List<JSONObject> objects = new ArrayList<>();
            JSONObject _object = new JSONObject();
            int patient = 0;
            _object.put("scheduleId", schedule.get().getScheduleId());
            _object.put("scheduleType", schedule.get().getScheduleType());
            _object.put("scheduleDate", schedule.get().getScheduleDate());
            _object.put("scheduleStartTIme", schedule.get().getScheduleStart());
            _object.put("scheduleFinishTime", schedule.get().getScheduleEnd());
            for (Appointment a : appointments) {
                patient++;
            }
            _object.put("patientCount", appointments.size());
            objects.add(_object);
            _datajason.put(schedule.get().getScheduleDate().toString(), objects);
        }
        return ResponseEntity.ok().body(_datajason);
    }

}
