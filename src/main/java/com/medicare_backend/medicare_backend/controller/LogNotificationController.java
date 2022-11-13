package com.medicare_backend.medicare_backend.controller;

import org.springframework.web.bind.annotation.RestController;

import com.medicare_backend.medicare_backend.repository.LogNotificationRepository;
import com.medicare_backend.medicare_backend.schema.entity.LogNotification;
import com.medicare_backend.medicare_backend.service.LogNotificationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
public class LogNotificationController {

    @Autowired
    private LogNotificationRepository logNotificationRepository;
    private LogNotificationService logNotificationService;

    public LogNotificationController(LogNotificationService logNotificationService) {
        this.logNotificationService = logNotificationService;
    }

    @PostMapping(path = "/addlogNotification")
    public ResponseEntity<?> createPatient(@RequestBody LogNotification logNotification) {
        try {
            logNotificationService.createLogNotification(logNotification);
            return ResponseEntity.ok().body(logNotification);
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(500).body(logNotification);
        }
    }
    
}
