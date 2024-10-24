package com.example.pp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientInfo {
    private String clientId;
    private String name;
    private String surname;
    private long age;
    private LocalDate birthday;
    private String phone;
}
