package model.validators;

/**
 * Created by cosmin on 3/5/17.
 */
public interface Validator<T> {
    void validate(T entity) throws ValidatorException;
}
