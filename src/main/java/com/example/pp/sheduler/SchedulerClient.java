package com.example.pp.sheduler;

import com.example.pp.service.ClientServiceImpl;
import lombok.Data;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Data
@Component
public class SchedulerClient {
    private final ClientServiceImpl clientService;

@Scheduled(cron = "${scheduled}")
    public void findByPhone(){
        clientService.findByPhone();
    }
}
