package repository;

import model.BaseEntity;
import model.validators.Validator;
import model.validators.ValidatorException;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import java.sql.*;

/**
 * DatabaseRepository implementation for the Repository
 * @param <ID>
 * @param <T>
 */
public class DatabaseRepository<ID, T extends BaseEntity<ID>> implements Repository<ID, T> {
    private Validator<T> validator;
    private Connection conn = null;

    public DatabaseRepository(Validator validator, String DB_URL, String USER, String PASS) {
        this.validator = validator;

        try {
            // STEP 2: Register JDBC driver
            Class.forName("org.postgresql.Driver");

            // STEP 3: Open a connection
            this.conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch(SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch(Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        }
    }

    private void executeQuery(String sqlQuery) {
        Statement stmt = null;
        try {
            stmt = this.conn.createStatement();
            stmt.executeUpdate(sqlQuery);
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(stmt != null)
                    stmt.close();
            } catch(SQLException e) {
                e.printStackTrace();
            }

            // better close the connection in the destructor
            // try {
            //     if(this.conn != null)
            //         this.conn.close();
            // } catch(SQLException e) {
            //     e.printStackTrace();
            // }
        }
    }

    /**
     * Method finds a specific entity based on an ID
     * @param id
     *            must be not null.
     * @return
     */
    public Optional<T> findOne(ID id) {

        // if (id == null) {
        //     throw new IllegalArgumentException("id must not be null");
        // }
        // return Optional.ofNullable(entities.get(id));
        return null;
    }

    /**
     * Method returns all the enitites from the current repository
     * @return
     */
    public Iterable<T> findAll() {
        // Set<T> allEntities = entities.entrySet()
        //                              .stream()
        //                              .map(entry -> entry.getValue())
        //                              .collect(Collectors.toSet());
        // return allEntities;
        return null;
    }

    /**
     * Method creates a new record
     * @param entity
     *            must not be null.
     * @return
     * @throws ValidatorException
     */
    public Optional<T> save(T entity) throws ValidatorException {
        // if (entity == null) {
        //     throw new IllegalArgumentException("id must not be null");
        // }
        // validator.validate(entity);
        // return Optional.ofNullable(entities.putIfAbsent(entity.getID(), entity));
        return null;
    }

    /**
     * Method deletes a record
     * @param id
     *            must not be null.
     * @return
     */
    public Optional<T> delete(ID id) {
        // if (id == null) {
        //     throw new IllegalArgumentException("id must not be null");
        // }
        // return Optional.ofNullable(entities.remove(id));
        return null;
    }

    /**
     * Method udpates a record
     * @param entity
     *            must not be null.
     * @return
     * @throws ValidatorException
     */
    public Optional<T> update(T entity) throws ValidatorException {
        // if (entity == null) {
        //     throw new IllegalArgumentException("entity must not be null");
        // }
        // validator.validate(entity);
        // return Optional.ofNullable(entities.computeIfPresent(entity.getID(), (k, v) -> entity));
        return null;
    }
}
