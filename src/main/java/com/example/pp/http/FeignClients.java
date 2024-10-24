package com.example.pp.http;

import com.example.pp.entity.ClientInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(url = "${client.url}",name = "client")
public interface FeignClients {
    @PostMapping("/api/v1/getClient")
    List<ClientInfo> getClients();

    @PostMapping("/api/v1/getClient/{clientId}")
    ClientInfo getClientsById(@PathVariable String clientId);

}
