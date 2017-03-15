package service;

import model.BaseEntity;
import model.validators.ValidatorException;
import repository.Repository;

import java.util.Optional;

/**
 * CRUDService
 *      - generic class for a Create Read Update Delete Service
 *      - contains a Repository and modifies it as necessary
 * @param <ID> - the key for each T entity
 * @param <T> - the Classes that we want to store in the repository (the class should extend BaseEntity ID
 *              since we use the ID as key in the repository
 */
public class CRUDService<ID, T extends BaseEntity<ID>> {
    protected Repository<ID, T> entityRepository;

    public CRUDService(Repository<ID, T> entityRepository) {
        this.entityRepository = entityRepository;
    }

    /**
     * Method creates (saves) a new entity in the repository
     * @param entity
     * @return
     * @throws ValidatorException in case of an Exception of an invalid entity
     */
    public Optional<T> create(T entity) throws ValidatorException {
        return this.entityRepository.save(entity);
    }

    /**
     * Method gets (reads) the entity with a specific id
     * @param id
     * @return
     */
    public Optional<T> read(ID id) {
        return this.entityRepository.findOne(id);
    }

    /**
     * Method do readAll the entities in the current repository
     * @return
     */
    public Iterable<T> readAll() {
        return this.entityRepository.findAll();
    }

    /**
     * Method to update an entity in repository
     * @param entity
     * @return
     * @throws ValidatorException
     */
    public Optional<T> update(T entity) throws ValidatorException {
        return this.entityRepository.update(entity);
    }

    /**
     * Method to delete a record entity from the repository
     * @param id
     * @return
     */
    public Optional<T> delete(ID id) {
        return this.entityRepository.delete(id);
    }
}
