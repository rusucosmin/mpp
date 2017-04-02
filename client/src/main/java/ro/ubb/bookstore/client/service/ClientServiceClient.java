package ro.ubb.bookstore.client.service;

import ro.ubb.bookstore.client.tcp.TcpClient;
import ro.ubb.bookstore.common.model.Client;
import ro.ubb.bookstore.common.service.IClientService;

import java.util.concurrent.ExecutorService;

public class ClientServiceClient extends CRUDServiceClient<Integer, Client> implements IClientService {
    public ClientServiceClient(ExecutorService executorService, TcpClient tcpClient) {
        super(executorService, tcpClient, "Client");
    }
}
