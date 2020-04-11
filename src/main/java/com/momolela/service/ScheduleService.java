package com.momolela.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduleService {

    @Scheduled(cron = "0/5 * * * * ? ")
    public void schedule() {
        System.out.println("hello ... ");
    }
}
