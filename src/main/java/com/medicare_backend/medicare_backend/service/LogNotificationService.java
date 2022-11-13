package com.medicare_backend.medicare_backend.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.medicare_backend.medicare_backend.repository.LogNotificationRepository;
import com.medicare_backend.medicare_backend.schema.entity.LogNotification;

@Service
public class LogNotificationService {

    private final LogNotificationRepository logNotificationRepository;

    public LogNotificationService(LogNotificationRepository logNotificationRepository) {
        this.logNotificationRepository = logNotificationRepository;
    }

    public String createLogNotification (LogNotification logNotification){
        logNotificationRepository.save(logNotification);
        return "Suscess";
    }
    
}
