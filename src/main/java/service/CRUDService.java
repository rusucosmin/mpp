package service;

import model.BaseEntity;
import model.validators.ValidatorException;
import repository.Repository;

import java.util.Optional;

public class CRUDService<ID, T extends BaseEntity<ID>> {
    protected Repository<ID, T> entityRepository;

    public CRUDService(Repository<ID, T> entityRepository) {
        this.entityRepository = entityRepository;
    }

    public Optional<T> create(T entity) throws ValidatorException {
        return this.entityRepository.save(entity);
    }

    public Optional<T> read(ID id) {
        return this.entityRepository.findOne(id);
    }

    public Iterable<T> readAll() {
        return this.entityRepository.findAll();
    }

    public Optional<T> update(T entity) throws ValidatorException {
        return this.entityRepository.update(entity);
    }

    public Optional<T> delete(ID id) {
        return this.entityRepository.delete(id);
    }
}
