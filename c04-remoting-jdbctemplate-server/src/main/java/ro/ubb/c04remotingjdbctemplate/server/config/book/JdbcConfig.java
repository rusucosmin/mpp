package ro.ubb.c04remotingjdbctemplate.server.config.book;

import org.apache.commons.dbcp.BasicDataSource;
import org.postgresql.Driver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * @author radu.
 */

@Configuration
public class JdbcConfig {

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }

    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(Driver.class.getName());
        dataSource.setUrl("jdbc:postgresql://sergiu.ml:5432/mpp");
        dataSource.setUsername("mpp");
        dataSource.setPassword("asdf1234");
        dataSource.setInitialSize(2);
        dataSource.setMaxActive(5);
        return dataSource;

    }
}
