package ro.ubb.bookstore.client.service;

import ro.ubb.bookstore.client.tcp.TcpClient;
import ro.ubb.bookstore.common.model.BaseEntity;
import ro.ubb.bookstore.common.model.Message;
import ro.ubb.bookstore.common.model.validators.BookStoreException;
import ro.ubb.bookstore.common.model.validators.ValidatorException;
import ro.ubb.bookstore.common.service.ICRUDService;
import ro.ubb.bookstore.common.utils.StringSerialize;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

public class CRUDServiceClient<ID, T extends BaseEntity<ID>> implements ICRUDService<ID,T> {
    protected final ExecutorService executorService;
    protected final TcpClient tcpClient;
    private final String CREATE, READ, READ_ALL, UPDATE, DELETE;

    public CRUDServiceClient(ExecutorService executorService, TcpClient tcpClient, String className) {
        this.executorService = executorService;
        this.tcpClient = tcpClient;
        this.CREATE = "create" + className;
        this.READ = "read" + className;
        this.READ_ALL = "readAll" + className + "s";
        this.UPDATE = "update" + className;
        this.DELETE = "delete" + className;
    }
    private Optional<T> requestOptional(Message request) throws IOException, ClassNotFoundException {
        Message response = tcpClient.sendAndReceive(request);
        if(response.header().equals(Message.OK))
            if(response.body().equals("null"))
                return Optional.empty();
            else
                return Optional.ofNullable((T) StringSerialize.fromString(response.body()));
        throw new BookStoreException("Message ERROR from server");
    }
    @Override
    public CompletableFuture<Optional<T>> create(T entity) throws ValidatorException {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return requestOptional(new Message(this.CREATE, StringSerialize.toString(entity)));
            } catch(Exception e) {
                e.printStackTrace();
            }
            throw new BookStoreException("Error while saving entity...");
        }, executorService);
    }

    @Override
    public CompletableFuture<Optional<T>> read(ID id) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return requestOptional(new Message(this.READ, id.toString()));
            } catch(Exception e) {
                e.printStackTrace();
            }
            throw new BookStoreException("Error while reading entity...");
        }, executorService);
    }

    @Override
    public CompletableFuture<Iterable<T>> readAll() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Message request = new Message(this.READ_ALL, "");
                Message response = tcpClient.sendAndReceive(request);
                if(response.header().equals(Message.OK)) {
                    ArrayList<T> arrayList = (ArrayList) StringSerialize.fromString(response.body());
                    return arrayList;
                }
                return new ArrayList<T>();
            } catch(Exception e) {
                e.printStackTrace();
            }
            throw new BookStoreException("Error while reading all entities...");
        }, executorService);
    }

    @Override
    public CompletableFuture<Optional<T>> update(T entity) throws ValidatorException {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return this.requestOptional(new Message(this.UPDATE, StringSerialize.toString(entity)));
            } catch(Exception e) {
                e.printStackTrace();
            }
            throw new BookStoreException("Error while updating entity...");
        }, executorService);
    }

    @Override
    public CompletableFuture<Optional<T>> delete(ID id) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return this.requestOptional(new Message(this.DELETE, id.toString()));
            } catch(Exception e) {
                e.printStackTrace();
            }
            throw new BookStoreException("Error while deleting entity...");
        }, executorService);
    }
}
