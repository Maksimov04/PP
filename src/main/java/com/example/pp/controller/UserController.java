package com.example.pp.controller;


import com.example.pp.entity.Client;
import com.example.pp.service.ClientService;

import com.example.pp.service.ClientServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/client")
@RequiredArgsConstructor
public class UserController {
    public final ClientServiceImpl service;


    @GetMapping("/getClient")
    public void findByPhone() {
        service.findByPhone();
    }

    @GetMapping("/{clientId}")
    public void findById(@PathVariable String clientId) {
        service.findById(clientId);
    }
}
