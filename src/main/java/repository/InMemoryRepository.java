package repository;

import model.BaseEntity;
import model.validators.Validator;
import model.validators.ValidatorException;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * InMemoryRepository implementation for the Repository
 * @param <ID>
 * @param <T>
 */
public class InMemoryRepository<ID, T extends BaseEntity<ID>> implements Repository<ID, T> {
    private Map<ID, T> entities;
    private Validator<T> validator;
    public InMemoryRepository(Validator validator) {
        this.validator = validator;
        this.entities = new HashMap<ID, T>();
    }

    /**
     * Method finds a specific entity based on an ID
     * @param id
     *            must be not null.
     * @return
     */
    public Optional<T> findOne(ID id) {
        if (id == null) {
            throw new IllegalArgumentException("id must not be null");
        }
        return Optional.ofNullable(entities.get(id));
    }

    /**
     * Method returns all the enitites from the current repository
     * @return
     */
    public Iterable<T> findAll() {
        Set<T> allEntities = entities.entrySet()
                                     .stream()
                                     .map(entry -> entry.getValue())
                                     .collect(Collectors.toSet());
        return allEntities;
    }

    /**
     * Method creates a new record
     * @param entity
     *            must not be null.
     * @return
     * @throws ValidatorException
     */
    public Optional<T> save(T entity) throws ValidatorException {
        if (entity == null) {
            throw new IllegalArgumentException("id must not be null");
        }
        validator.validate(entity);
        return Optional.ofNullable(entities.putIfAbsent(entity.getID(), entity));
    }

    /**
     * Method deletes a record
     * @param id
     *            must not be null.
     * @return
     */
    public Optional<T> delete(ID id) {
        if (id == null) {
            throw new IllegalArgumentException("id must not be null");
        }
        return Optional.ofNullable(entities.remove(id));
    }

    /**
     * Method udpates a record
     * @param entity
     *            must not be null.
     * @return
     * @throws ValidatorException
     */
    public Optional<T> update(T entity) throws ValidatorException {
        if (entity == null) {
            throw new IllegalArgumentException("entity must not be null");
        }
        validator.validate(entity);
        return Optional.ofNullable(entities.computeIfPresent(entity.getID(), (k, v) -> entity));
    }
}
