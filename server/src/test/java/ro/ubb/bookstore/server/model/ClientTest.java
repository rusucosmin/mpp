package ro.ubb.bookstore.server.model;

import org.junit.Before;
import org.junit.Test;
import ro.ubb.bookstore.common.model.Client;

import static org.junit.Assert.assertEquals;

public class ClientTest {
    Client client;
    @Before
    public void setUp() {
        client = new Client(1, "John Smith", "john.smith@test.com", "Main Street");
    }
    @Test
    public void getName() {
        assertEquals(client.getName(), "John Smith");
    }

    @Test
    public void getEmail() {
        assertEquals(client.getEmail(), "john.smith@test.com");
    }

    @Test
    public void getAddress() {
        assertEquals(client.getAddress(), "Main Street");
    }

    @Test
    public void setName() {
        client.setName("John Black");
        assertEquals(client.getName(), "John Black");
    }

    @Test
    public void setEmail() {
        client.setEmail("black@john.com");
        assertEquals(client.getEmail(), "black@john.com");
    }

    @Test
    public void setAddress() {
        client.setAddress("Secondary Street");
        assertEquals(client.getAddress(), "Secondary Street");
    }

    @Test
    public void getID() {
        assertEquals(client.getID(), new Integer(1));
    }

    @Test
    public void setID() {
        client.setID(2);
        assertEquals(client.getID(), new Integer(2));
    }

}