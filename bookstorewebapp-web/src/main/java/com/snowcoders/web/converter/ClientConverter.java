package com.snowcoders.web.converter;

import com.snowcoders.core.model.Client;
import com.snowcoders.web.dto.ClientDto;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.stream.Collectors;

@Component
public class ClientConverter extends BaseConverter<Client, ClientDto> {
    @Override
    public Client convertDtoToModel(ClientDto dto) {
        Client client = Client.builder()
                .name(dto.getName())
                .orders(new HashSet<>())
                .build();
        client.setId(dto.getId());
        return client;
    }

    @Override
    public ClientDto convertModelToDto(Client client) {
        ClientDto clientDto = ClientDto.builder()
                .name(client.getName())
                .build();
        clientDto.setId(client.getId());
        clientDto.setBooks(client.getBooks().stream()
                        .map(b->b.getId())
                        .collect(Collectors.toSet()));
        return clientDto;
    }
}
