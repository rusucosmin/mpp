package model.validators;

/**
 * Validator interfacde with only one method: validate
 * @param <T>
 */
public interface Validator<T> {
    void validate(T entity) throws ValidatorException;
}
