package com.snowcoders.web.controller;

import com.snowcoders.core.model.Client;
import com.snowcoders.core.service.ClientService;
import com.snowcoders.web.converter.ClientConverter;
import com.snowcoders.web.dto.ClientDto;
import com.snowcoders.web.dto.ClientsDto;
import com.snowcoders.web.dto.EmptyJsonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ClientController {

    @Autowired
    protected ClientService clientService;

    @Autowired
    protected ClientConverter clientConverter;

    @RequestMapping(value = "/clients", method = RequestMethod.GET)
    public ClientsDto getAll() {
        return new ClientsDto(clientService.findAll());
    }

    @RequestMapping(value = "/clients/{clientId}", method = RequestMethod.PUT)
    public Map<String, ClientDto> updateBook(
            @PathVariable final Long clientId,
            @RequestBody final Map<String, ClientDto> clientDtoMap) {
        ClientDto clientDto = clientDtoMap.get("client");
        Client client = clientService.updateClient(clientId, clientDto.getName());

        Map<String, ClientDto> result = new HashMap<>();
        result.put("client", clientConverter.convertModelToDto(client));

        return result;
    }

    @RequestMapping(value = "/clients", method = RequestMethod.POST)
    public Map<String, ClientDto> createClient(
            @RequestBody final Map<String, ClientDto> clientDtoMap) {
        ClientDto clientDto = clientDtoMap.get("client");
        Client client = clientService.createClient(clientDto.getName());

        Map<String, ClientDto> result = new HashMap<>();
        result.put("client", clientConverter.convertModelToDto(client));

        return result;
    }

    @RequestMapping(value = "/clients/{clientId}", method = RequestMethod.DELETE)
    public ResponseEntity deleteClient(@PathVariable final Long clientId) {
        clientService.deleteClient(clientId);
        return new ResponseEntity(new EmptyJsonResponse(), HttpStatus.OK);
    }
}
