package com.medicare_backend.medicare_backend.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medicare_backend.medicare_backend.proxy.PublicApiProxy;
import com.medicare_backend.medicare_backend.schema.entity.Employee;
import com.medicare_backend.medicare_backend.schema.entity.Schedule;
import com.medicare_backend.medicare_backend.schema.relationship.Appointment;
import com.medicare_backend.medicare_backend.schema.relationship.TakeSchedule;
import com.medicare_backend.medicare_backend.service.AppointmentService;
import com.medicare_backend.medicare_backend.service.EmployeeService;
import com.medicare_backend.medicare_backend.service.ScheduleService;
import com.medicare_backend.medicare_backend.service.TakeScheduleService;
import com.medicare_backend.medicare_backend.service.TokenAuthenticationService;

@EnableCaching
@RestController
public class PublicApi {
    
    @Autowired
    private PublicApiProxy publicApiProxy;

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private TakeScheduleService takeScheduleService;
    @Autowired
    private EmployeeService employeeService;

    @GetMapping(path = "/getSlotTime")
    public ResponseEntity<?> getSchedule() {
        try {
            List<Schedule> data = publicApiProxy.getAllSchedule();
            List<JSONObject> objects = new ArrayList<>();
            for (Schedule s : data) {
                List<Appointment> appointments = appointmentService.getAppointmentByScheduleId(s.getScheduleId());
                Optional<TakeSchedule> takeSchedule = takeScheduleService
                        .getTakeScheduleByScheduleId(s.getScheduleId());
                Optional<Employee> e = employeeService.getEmployeeById(takeSchedule.get().getEmployeeId());
                JSONObject _object = new JSONObject();
                System.out.println(e);
                _object.put("docterDepartMent", e.get().getEmployeeDepartment());
                _object.put("scheduleType", s.getScheduleType());
                _object.put("scheduleDate", s.getScheduleDate());
                _object.put("scheduleStartTIme", s.getScheduleStart());
                _object.put("scheduleFinishTime", s.getScheduleEnd());
                _object.put("scheduleCapacity", s.getScheduleCapacity());
                _object.put("patientCount", appointments.size());
                objects.add(_object);
            }
            if (!(data != null && data.isEmpty())) {
                return ResponseEntity.ok().body(data);
            } else {
                return ResponseEntity.status(400).body("Not Found Data");
            }
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(500).body("server error");
        }
    }

}
