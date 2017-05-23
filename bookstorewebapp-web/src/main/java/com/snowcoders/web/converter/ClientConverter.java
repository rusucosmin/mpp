package com.snowcoders.web.converter;

import com.snowcoders.core.model.Client;
import com.snowcoders.web.dto.ClientDto;
import org.springframework.stereotype.Component;

@Component
public class ClientConverter extends BaseConverter<Client, ClientDto> {
    @Override
    public Client convertDtoToModel(ClientDto dto) {
        Client client = new Client(dto.getName());//, new HashSet<>());
        client.setId(dto.getId());
        return client;
    }

    @Override
    public ClientDto convertModelToDto(Client client) {
        ClientDto clientDto = new ClientDto(client.getName());
        clientDto.setId(client.getId());
        return clientDto;
    }
}
