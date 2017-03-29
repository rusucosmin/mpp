package ro.ubb.bookstore.client.tcp;

import ro.ubb.bookstore.common.model.Message;
import ro.ubb.bookstore.common.service.RequestServiceException;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @author radu.
 */
public class TcpClient {
    private String serviceHost;
    private int servicePort;

    public TcpClient(String serviceHost, int servicePort) {
        this.serviceHost = serviceHost;
        this.servicePort = servicePort;
    }

    public Message sendAndReceive(Message request) {
        OutputStream outputStream = null;
        try (Socket socket = new Socket(serviceHost, servicePort)) {
            outputStream = socket.getOutputStream();
            request.writeTo(outputStream);
            outputStream.flush();

            Message response = new Message();
            response.readFrom(socket.getInputStream());
            if (response.header().equalsIgnoreCase(Message.OK)) {
                return response;
            } else {
                throw new RequestServiceException(response.body());
            }
        } catch (IOException e) {
            //e.printStackTrace();
            throw new RequestServiceException(e);
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                //e.printStackTrace();
            }
        }
    }
}

