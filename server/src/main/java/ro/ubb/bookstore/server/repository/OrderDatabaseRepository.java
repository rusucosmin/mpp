package ro.ubb.bookstore.server.repository;

import ro.ubb.bookstore.common.model.Order;
import ro.ubb.bookstore.common.model.validators.Validator;
import ro.ubb.bookstore.common.model.validators.ValidatorException;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * DatabaseRepository implementation for the Repository
 */
public class OrderDatabaseRepository extends DatabaseConnection implements Repository<Integer, Order> {
    Validator<Order> validator;

    public OrderDatabaseRepository(Validator validator, String DB_URL, String USER, String PASS) {
        super(DB_URL, USER, PASS);
        this.validator = validator;
    }

    /**
     * Method finds a specific entity based on an ID
     * @param id
     *            must be not null.
     * @return
     */
    public Optional<Order> findOne(Integer id) {
        String query = "SELECT id, clientID, bookID, cnt FROM orders WHERE id='" + id.toString() + "';";
        ResultSet rs = executeQuery(query);
        Order ret = null;

        try {
            while(rs.next()) {
                int orderID = rs.getInt("id");
                int clientID = rs.getInt("clientID");
                int bookID = rs.getInt("bookID");
                int cnt = rs.getInt("cnt");

                ret = new Order(orderID, clientID, bookID, cnt);
                break;
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(ret);
    }


    /**
     * Method returns all the enitites from the current repository
     * @return
     */
    public Iterable<Order> findAll() {
        String query = "SELECT id, clientID, bookID, cnt FROM orders;";
        ResultSet rs = executeQuery(query);

        List<Order> ret = new ArrayList<Order>();

        try {
            while(rs.next()) {
                int orderID = rs.getInt("id");
                int clientID = rs.getInt("clientID");
                int bookID = rs.getInt("bookID");
                int cnt = rs.getInt("cnt");

                ret.add(new Order(orderID, clientID, bookID, cnt));
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return ret;

    }

    /**
     * Method creates a new record
     * @param x
     *            must not be null.
     * @return
     * @throws ValidatorException
     */
    public Optional<Order> save(Order x) throws ValidatorException {
        if(x == null)
            throw new IllegalArgumentException("order is null, too bad");

        validator.validate(x);

        String query = "INSERT INTO orders (id, clientID, bookID, cnt) " +
            String.format("VALUES (%d, %d, %d, %d);", x.getID(), x.getClientID(), x.getBookID(), x.getCnt());

        Optional<Order> opt = findOne(x.getID());
        if(opt.isPresent())
            return opt;

        executeUpdate(query);

        return Optional.empty();
    }

    /**
     * Method deletes a record
     * @param id
     *            must not be null.
     * @return
     */
    public Optional<Order> delete(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("id must not be null");
        }

        Optional<Order> ret = findOne(id);

        if(!ret.isPresent())
            return null;

        String query = "DELETE FROM orders WHERE id='" + id.toString() + "';";
        executeUpdate(query);

        return ret;
    }

    /**
     * Method updates a record
     * @param x
     *            must not be null.
     * @return
     * @throws ValidatorException
     */
    public Optional<Order> update(Order x) throws ValidatorException {
        if(x == null)
            throw new IllegalArgumentException("order is null, too bad");

        validator.validate(x);

        Optional<Order> ret = findOne(x.getID());

        if(!ret.isPresent())
            return Optional.empty();

        String query = String.format("UPDATE orders SET id='%d', clientID='%d', bookID='%d', cnt='%d' WHERE id='%d';", x.getID(), x.getClientID(), x.getBookID(), x.getCnt(), x.getID());

        executeUpdate(query);

        return Optional.ofNullable(x);
    }
}
