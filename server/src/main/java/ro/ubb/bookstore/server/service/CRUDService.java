package ro.ubb.bookstore.server.service;

import ro.ubb.bookstore.common.model.BaseEntity;
import ro.ubb.bookstore.common.model.validators.ValidatorException;
import ro.ubb.bookstore.common.service.ICRUDService;
import ro.ubb.bookstore.server.repository.Repository;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

/**
 * CRUDService
 *      - generic class for a Create Read Update Delete Service
 *      - contains a Repository and modifies it as necessary
 * @param <ID> - the key for each T entity
 * @param <T> - the Classes that we want to store in the repository (the class should extend BaseEntity ID
 *              since we use the ID as key in the repository
 */
public class CRUDService<ID, T extends BaseEntity<ID>> implements ICRUDService<ID, T> {
    protected Repository<ID, T> entityRepository;
    protected ExecutorService executor;

    public CRUDService(Repository<ID, T> entityRepository, ExecutorService executor) {
        this.entityRepository = entityRepository;
        this.executor = executor;
    }

    /**
     * Method creates (saves) a new entity in the repository
     * @param entity
     * @return
     * @throws ValidatorException in case of an Exception of an invalid entity
     */
    public CompletableFuture<Optional<T>> create(T entity) throws ValidatorException {
        return CompletableFuture.supplyAsync(() -> this.entityRepository.save(entity), executor);
    }

    /**
     * Method gets (reads) the entity with a specific id
     * @param id
     * @return
     */
    public CompletableFuture<Optional<T>> read(ID id) {
        return CompletableFuture.supplyAsync(() -> this.entityRepository.findOne(id), executor);
    }

    /**
     * Method do readAll the entities in the current repository
     * @return
     */
    public CompletableFuture<Iterable<T>> readAll() {
        return CompletableFuture.supplyAsync(() -> this.entityRepository.findAll(), executor);
    }

    /**
     * Method to update an entity in repository
     * @param entity
     * @return
     * @throws ValidatorException
     */
    public CompletableFuture<Optional<T>> update(T entity) throws ValidatorException {
        return CompletableFuture.supplyAsync(() -> this.entityRepository.update(entity), executor);
    }

    /**
     * Method to delete a record entity from the repository
     * @param id
     * @return
     */
    public CompletableFuture<Optional<T>> delete(ID id) {
        return CompletableFuture.supplyAsync(() -> this.entityRepository.delete(id), executor);
    }
}
