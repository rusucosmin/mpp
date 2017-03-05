package model.validators;

import model.Client;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;

public class ClientValidator implements Validator<Client> {
    public void validate(Client entity) throws ValidatorException {
        this.validateName(entity);
        this.validateAddress(entity);
        this.validateEmail(entity);
    }

    private void validateName(Client entity) throws ValidatorException {
        if(StringUtils.isEmpty(entity.getName()))
            throw new ValidatorException("Name cannot be null");
    }

    private void validateAddress(Client entity) throws ValidatorException {
        if(StringUtils.isEmpty(entity.getAddress()))
            throw new ValidatorException("Address cannot be null");
    }

    private void validateEmail(Client entity) throws ValidatorException {
        EmailValidator validator = EmailValidator.getInstance();

        if(!validator.isValid(entity.getEmail()))
            throw new ValidatorException("Invalid email address");
    }
}
