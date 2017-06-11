package com.snowcoders.core.service;

import com.snowcoders.core.model.Book;
import com.snowcoders.core.model.Client;
import com.snowcoders.core.repository.BookRepository;
import com.snowcoders.core.repository.ClientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    protected ClientRepository clientRepository;
    @Autowired
    protected BookRepository bookRepository;

    private Logger log = LoggerFactory.getLogger(ClientService.class);

    @Override
    public List<Client> findAll() {
        //return clientRepository.findAll();
        return clientRepository.findAllWithOrdersGraph();
        //return clientRepository.findAllWithOrdersSqlQuery();
        //return clientRepository.findAllWithOrdersJpql();
        //return clientRepository.findAllWithOrdersJpaCriteria();
    }

    @Override
    public Client createClient(String name) {
        Client client = Client.builder()
                .name(name)
                .orders(new HashSet<>())
                .build();
        log.trace(client.toString());
        client = clientRepository.save(client);
        log.trace(client.toString());
        return client;
    }

    @Override
    public Client findOne(Long id) {
        return clientRepository.findOneWithOrders(id);
    }

    @Override
    @Transactional
    public Client updateClient(Long id, String name, Set<Long> books) {
        Client client = clientRepository.findOneWithOrders(id);
        log.trace("updateClient = {}, name = {}, ids={}", client, name, books);
        client.setName(name);
        /// find bookIds that we should remove
        Set<Long> toBeRemoved = client.getBooks().stream()
                .map(b -> b.getId())
                .filter(bid -> !books.contains(bid))
                .collect(Collectors.toSet());
        log.trace("idsToBeRemoved = {}", toBeRemoved);

        /// remove those books
        List<Book> bookListToBeRemoved = bookRepository.findAll(toBeRemoved);

        bookListToBeRemoved.stream()
                .forEach(client::removeBook);

        log.trace("booksToBeRemoved = {}", bookListToBeRemoved);

        /// find books that are already present
        client.getBooks().stream()
                .map(b -> b.getId())
                .forEach(bid -> {
                    if (books.contains(bid))
                        books.remove(bid);
                });

        log.trace("idsToBeAdded = {}", books);

        // add the remaining books
        List<Book> bookList = bookRepository.findAll(books);
        log.trace("booksToBeAdded = {}", bookList);

        bookList.stream()
                .forEach(client::addBook);
        log.trace("doneUpdatingClient = {}", client);
        return client;
    }

    @Override
    public void deleteClient(Long id) {
        clientRepository.delete(id);
    }
}
