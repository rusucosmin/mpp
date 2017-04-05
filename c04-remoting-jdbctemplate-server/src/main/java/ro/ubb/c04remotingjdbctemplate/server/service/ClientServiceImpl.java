package ro.ubb.c04remotingjdbctemplate.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import ro.ubb.c04remotingjdbctemplate.common.Client;
import ro.ubb.c04remotingjdbctemplate.common.ClientService;

import java.util.List;

/**
 * Created by Petru on 4/5/2017.
 */
public class ClientServiceImpl implements ClientService {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Client> getClients() {
        String sql = "select * from clients;";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Client.class));
    }

    public void addClient(Client c) {
        if(c == null)
            throw new IllegalArgumentException("client is null, too bad");
        String sql = "INSERT INTO clients (id, name, email, address) VALUES (?, ?, ?, ?);";
        jdbcTemplate.update(sql, c.getID(), c.getName(), c.getEmail(), c.getAddress());
    }
}
