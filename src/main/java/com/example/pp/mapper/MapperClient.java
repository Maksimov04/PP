package com.example.pp.mapper;

import com.example.pp.entity.Client;
import com.example.pp.entity.ClientInfo;
import com.example.pp.entity.SmsMassage;

import org.mapstruct.Mapper;

@Mapper(componentModel = "massager")
public interface MapperClient {
    Client clientsInfoTo(ClientInfo clientsInfo);

    SmsMassage  clientToMessage(Client client, String string);

    default String concat1(Client client, String string) {
        String[] words = client.getFirstName().split(" ", 5);
        return String.format("%s %s , в этом месяце для вас действует скидка %s", words[0], words[1], string);
}
}
