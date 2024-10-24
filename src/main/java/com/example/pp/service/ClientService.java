package com.example.pp.service;

import com.example.pp.entity.Client;
import com.example.pp.entity.SmsMassage;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;


public interface ClientService {


    void findByPhone();

    void findById(String clientId);

}