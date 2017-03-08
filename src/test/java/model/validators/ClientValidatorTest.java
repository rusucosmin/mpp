package model.validators;

import model.Client;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class ClientValidatorTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();
    ClientValidator clientValidator;
    @Before
    public void setUp() {
        clientValidator = new ClientValidator();
    }

    @Test
    public void validateEmptyName() throws ClientException {
        thrown.expect(ClientException.class);
        thrown.expectMessage("Name cannot be null");

        clientValidator.validate(new Client(1, "", "test@test.com", "test address"));
    }

    @Test
    public void validateName() {
        clientValidator.validate(new Client(1, "Test Name", "test@test.com", "test address"));
    }

    @Test
    public void validateEmptyAddress() throws ClientException {
        thrown.expect(ClientException.class);
        thrown.expectMessage("Address cannot be null");

        clientValidator.validate(new Client(1, "Test Name", "test@test.com", ""));
    }

    @Test
    public void validateAddress() {
        clientValidator.validate(new Client(1, "Test Name", "test@test.com", "test address"));
    }

    @Test
    public void validateNotEmail1() throws ClientException {
        thrown.expect(ClientException.class);
        thrown.expectMessage("Invalid email address");
        clientValidator.validate(new Client(1, "Test Name", "test.com", "test address"));
    }

    @Test
    public void validateNotEmail2() throws ClientException {
        thrown.expect(ClientException.class);
        thrown.expectMessage("Invalid email address");
        clientValidator.validate(new Client(1, "Test Name", "test@test", "test address"));
    }

    @Test
    public void validateEmail() {
        clientValidator.validate(new Client(1, "Test Name", "test@test.com", "test address"));
    }

}
