package com.example.pp.repository;

import com.example.pp.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {


    String findPhoneByPhone(String phone);


    List<Client> findAllUnsentMessages();


}

