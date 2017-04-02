package ro.ubb.bookstore.common.service;

import ro.ubb.bookstore.common.model.BaseEntity;
import ro.ubb.bookstore.common.model.validators.ValidatorException;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public interface ICRUDService<ID, T extends BaseEntity<ID>> {
    public CompletableFuture<Optional<T>> create(T entity) throws ValidatorException;
    public CompletableFuture<Optional<T>> read(ID id);
    public CompletableFuture<Iterable<T>> readAll();
    public CompletableFuture<Optional<T>> update(T entity) throws ValidatorException;
    public CompletableFuture<Optional<T>> delete(ID id);
}
