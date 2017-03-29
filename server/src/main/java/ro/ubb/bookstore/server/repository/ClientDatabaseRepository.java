package ro.ubb.bookstore.server.repository;

import ro.ubb.bookstore.common.model.BaseEntity;
import ro.ubb.bookstore.common.model.Client;
import ro.ubb.bookstore.common.model.validators.Validator;
import ro.ubb.bookstore.common.model.validators.ValidatorException;

import java.util.Optional;
import java.util.List;
import java.util.ArrayList;

import java.sql.*;

/**
 * DatabaseRepository implementation for the Repository
 * @param <ID>
 * @param <T>
 */
public class ClientDatabaseRepository extends DatabaseConnection implements Repository<Integer, Client> {
    Validator<Client> validator;

    public ClientDatabaseRepository(Validator validator, String DB_URL, String USER, String PASS) {
        super(DB_URL, USER, PASS);
        this.validator = validator;
    }

    /**
     * Method finds a specific entity based on an ID
     * @param id
     *            must be not null.
     * @return
     */
    public Optional<Client> findOne(Integer id) {
        String query = "SELECT id, name, email, address FROM clients WHERE id='" + id.toString() + "';";
        ResultSet rs = executeQuery(query);
        Client ret = null;

        try {
            while(rs.next()) {
                int clientID = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String address = rs.getString("address");

                ret = new Client(clientID, name, email, address);
                break;
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(ret);
    }


    /**
     * Method returns all the entites from the current repository
     * @return
     */
    public Iterable<Client> findAll() {
        String query = "SELECT id, name, email, address FROM clients;";
        ResultSet rs = executeQuery(query);

        List<Client> ret = new ArrayList<Client>();

        try {
            while(rs.next()) {
                int clientID = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String address = rs.getString("address");

                ret.add(new Client(clientID, name, email, address));
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return ret;

    }

    /**
     * Method creates a new record
     * @param entity
     *            must not be null.
     * @return
     * @throws ValidatorException
     */
    public Optional<Client> save(Client c) throws ValidatorException {
        if(c == null)
            throw new IllegalArgumentException("client is null, too bad");

        validator.validate(c);

        String query = "INSERT INTO clients (id, name, email, address) " +
            String.format("VALUES (%d, '%s', '%s', '%s');", c.getID(), c.getName(), c.getEmail(), c.getAddress());

        executeUpdate(query);

        // TODO: make sure this is what I want to return
        return Optional.ofNullable(c);
    }

    /**
     * Method deletes a record
     * @param id
     *            must not be null.
     * @return
     */
    public Optional<Client> delete(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("id must not be null");
        }

        Optional<Client> ret = findOne(id);

        String query = "DELETE FROM clients WHERE id='" + id.toString() + "';";
        executeUpdate(query);

        return ret;
    }

    /**
     * Method updates a record
     * @param entity
     *            must not be null.
     * @return
     * @throws ValidatorException
     */
    public Optional<Client> update(Client c) throws ValidatorException {
        if(c == null)
            throw new IllegalArgumentException("client is null, too bad");

        validator.validate(c);

        String query = String.format("UPDATE clients SET id='%d', name='%s', email='%s', address='%s' WHERE id='%d';", c.getID(), c.getName(), c.getEmail(), c.getAddress(), c.getID());

        executeUpdate(query);

        // TODO: make sure this is what I want to return
        return Optional.ofNullable(c);
    }
}
