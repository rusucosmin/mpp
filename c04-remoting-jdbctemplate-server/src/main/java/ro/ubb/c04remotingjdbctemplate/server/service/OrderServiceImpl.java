package ro.ubb.c04remotingjdbctemplate.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import ro.ubb.c04remotingjdbctemplate.common.Client;
import ro.ubb.c04remotingjdbctemplate.common.Order;
import ro.ubb.c04remotingjdbctemplate.common.OrderService;

import java.util.List;

/**
 * Created by Petru on 4/5/2017.
 */
public class OrderServiceImpl implements OrderService {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public void addOrder(Order o) {
        if(o == null)
            throw new IllegalArgumentException("order is null, too bad");
        String sql = "insert into orders (id, clientID, bookID, cnt) VALUES (?, ?, ?, ?);";
        jdbcTemplate.update(sql,o.getID(), o.getClientID(), o.getBookID(), o.getCnt());

    }

    public List<Order> getOrders() {
        String sql = "select * from orders;";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Order.class));
    }

    public void updateOrder(Order o) {
        if(o == null)
            throw new IllegalArgumentException("order is null, too bad");
        String sql = "Update orders SET clientID=?, bookID=?, cnt=? WHERE id=?";
        jdbcTemplate.update(sql, o.getClientID(), o.getBookID(), o.getCnt(), o.getID());
    }

    public void deleteOrder(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("id must not be null");
        }

        String sql = "delete from orders where id = ?;";
        jdbcTemplate.update(sql, id);
    }
}
