package model.validators;

import model.Client;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;

/**
 * ClientValidator
 */
public class ClientValidator implements Validator<Client> {
    public void validate(Client entity) throws ClientException {
        this.validateName(entity);
        this.validateAddress(entity);
        this.validateEmail(entity);
    }

    private void validateName(Client entity) throws ClientException {
        if(StringUtils.isEmpty(entity.getName()))
            throw new ClientException("Name cannot be null");
    }

    private void validateAddress(Client entity) throws ClientException {
        if(StringUtils.isEmpty(entity.getAddress()))
            throw new ClientException("Address cannot be null");
    }

    private void validateEmail(Client entity) throws ClientException {
        EmailValidator validator = EmailValidator.getInstance();

        if(!validator.isValid(entity.getEmail()))
            throw new ClientException("Invalid email address");
    }
}
