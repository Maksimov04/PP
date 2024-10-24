package com.example.pp.service;

import com.example.pp.entity.Client;
import com.example.pp.entity.ClientInfo;
import com.example.pp.entity.SmsMassage;
import com.example.pp.http.FeignClients;
import com.example.pp.mapper.MapperClient;
import com.example.pp.repository.ClientRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Getter
@Slf4j
public class ClientServiceImpl implements ClientService { // бизнес логика сделать

    private final ClientRepository repository;
    private final FeignClients feignClients;
    private final KafkaTemplate<String, SmsMassage> kafkaTemplate;
    private final MapperClient clientMapper;
    private final Month currentMonth = LocalDate.now().getMonth();

    @Value("${client.myVariable}")
    private String myVariableValue;


    @Override
    public void findByPhone() {


        try {
            List<ClientInfo> clients = feignClients.getClients();
            for (ClientInfo clientsInfo : clients) {
                if (clientsInfo != null &&
                        clientsInfo.getPhone().endsWith("7") &&
                        clientsInfo.getBirthday().getMonth() == currentMonth &&
                        repository.findPhoneByPhone(clientsInfo.getPhone()) == null) {
                    Client client = clientMapper.clientsInfoTo(clientsInfo);
                    repository.save(client);

                }
            }

            if (LocalTime.now().isBefore(LocalTime.of(19, 0))) {
                List<Client> unsentMessages = repository.findAllUnsentMessages();
                for (Client client : unsentMessages) {
                    SmsMassage message = clientMapper.clientToMessage(client, getMyVariableValue());
                    kafkaTemplate.send("topic", message);
                    client.setMessageSend(true);
                    repository.save(client);

                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void findById(String id) {


        try {
            Optional.ofNullable(feignClients.getClientsById(id))
                    .filter(clientsInfo -> clientsInfo.getPhone().endsWith("7") && clientsInfo.getBirthday().getMonth() == currentMonth)
                    .ifPresent(clientsInfo -> {
                        repository.save(clientMapper.clientsInfoTo(clientsInfo));
                    });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}







