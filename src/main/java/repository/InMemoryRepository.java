package repository;

import model.BaseEntity;
import model.validators.Validator;
import model.validators.ValidatorException;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class InMemoryRepository<ID, T extends BaseEntity<ID>> implements Repository<ID, T> {
    private Map<ID, T> entities;
    private Validator<T> validator;
    public InMemoryRepository(Validator validator) {
        this.validator = validator;
        this.entities = new HashMap<ID, T>();
    }

    public Optional<T> findOne(ID id) {
        if (id == null) {
            throw new IllegalArgumentException("id must not be null");
        }
        return Optional.ofNullable(entities.get(id));
    }

    public Iterable<T> findAll() {
        Set<T> allEntities = entities.entrySet()
                                     .stream()
                                     .map(entry -> entry.getValue())
                                     .collect(Collectors.toSet());
        return allEntities;
    }

    public Optional<T> save(T entity) throws ValidatorException {
        if (entity == null) {
            throw new IllegalArgumentException("id must not be null");
        }
        validator.validate(entity);
        return Optional.ofNullable(entities.putIfAbsent(entity.getID(), entity));
    }

    public Optional<T> delete(ID id) {
        if (id == null) {
            throw new IllegalArgumentException("id must not be null");
        }
        return Optional.ofNullable(entities.remove(id));
    }

    public Optional<T> update(T entity) throws ValidatorException {
        if (entity == null) {
            throw new IllegalArgumentException("entity must not be null");
        }
        validator.validate(entity);
        return Optional.ofNullable(entities.computeIfPresent(entity.getID(), (k, v) -> entity));
    }
}
