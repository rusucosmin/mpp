package com.snowcoders.web.controller;

import com.snowcoders.core.model.Client;
import com.snowcoders.core.service.ClientService;
import com.snowcoders.web.converter.ClientConverter;
import com.snowcoders.web.dto.ClientDto;
import com.snowcoders.web.dto.ClientsDto;
import com.snowcoders.web.dto.EmptyJsonResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ClientController {

    @Autowired
    protected ClientService clientService;

    @Autowired
    protected ClientConverter clientConverter;

    private static final Logger log = LoggerFactory.getLogger(ClientController.class);

    @RequestMapping(value = "/clients", method = RequestMethod.GET)
    public ClientsDto getAll() {
        log.trace("ClientController::getAll()");
        return new ClientsDto(clientConverter.convertModelsToDtos(clientService.findAll()));
    }

    @RequestMapping(value = "/clients/{clientId}", method = RequestMethod.PUT)
    public Map<String, ClientDto> updateClient(
            @PathVariable final Long clientId,
            @RequestBody final Map<String, ClientDto> clientDtoMap) {
        log.trace("ClientController::updateClient()");
        log.trace("updateStudent: clientId={}, clientDtoMap={}", clientId, clientDtoMap);
        ClientDto clientDto = clientDtoMap.get("client");
        Client client = clientService.updateClient(clientId, clientDto.getName(), clientDto.getBooks());

        Map<String, ClientDto> result = new HashMap<>();
        result.put("client", clientConverter.convertModelToDto(client));

        log.trace("ClientController::updateClient()");
        log.trace("ClientController::updateClient(): " + clientConverter.convertModelToDto(client));
        return result;
    }

    @RequestMapping(value = "/clients", method = RequestMethod.POST)
    public Map<String, ClientDto> createClient(
            @RequestBody final Map<String, ClientDto> clientDtoMap) {
        log.trace("ClientController::createClient()");
        ClientDto clientDto = clientDtoMap.get("client");
        Client client = clientService.createClient(clientDto.getName());
        log.trace("ClientController::client={}", client);

        Map<String, ClientDto> result = new HashMap<>();
        result.put("client", clientConverter.convertModelToDto(client));
        log.trace("Result: " + clientConverter.convertModelToDto(client).toString());

        return result;
    }

    @RequestMapping(value = "/clients/{clientId}", method = RequestMethod.DELETE)
    public ResponseEntity deleteClient(@PathVariable final Long clientId) {
        log.trace("ClientController::deleteClient()");
        clientService.deleteClient(clientId);
        return new ResponseEntity(new EmptyJsonResponse(), HttpStatus.OK);
    }
}
