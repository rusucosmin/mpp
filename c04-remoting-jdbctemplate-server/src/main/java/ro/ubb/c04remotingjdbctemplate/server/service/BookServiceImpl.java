package ro.ubb.c04remotingjdbctemplate.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import ro.ubb.c04remotingjdbctemplate.common.Book;
import ro.ubb.c04remotingjdbctemplate.common.BookService;

import java.util.List;

/**
 * @author radu.
 */
public class BookServiceImpl implements BookService {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Book> getAllBooks() {

        String sql = "select * from books";
//        return jdbcTemplate.query(sql, (rs, i) -> new Book(rs.getLong("id"), rs.getString("serial_number"),
//                rs.getString("name"), rs.getInt("group_number")));

         return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Book.class));
    }

    public void addBook(Book b) {
        if(b == null)
            throw new IllegalArgumentException("book is null, too bad");

        String sql = "insert into books (id, title, author, year, cnt) values (?,?,?,?,?)";
        jdbcTemplate.update(sql,  b.getID(), b.getTitle(), b.getAuthor(), b.getYear(), b.getCnt());
    }

    public void update(Book b) {
        String sql = "update books set id=?, title=? author=? year=? cnt=? where id=?";
        jdbcTemplate.update(sql, b.getID(), b.getTitle(), b.getAuthor(), b.getYear(), b.getCnt(), b.getID());
    }

    public void delete(Book s) {
        String sql = "delete from books where id=?";
        jdbcTemplate.update(sql, s.getID());
    }

    public Book findBySerialNumber(String serial_number) {
        String sql = "select * from student where serial_number=?";
        Book s = jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(Book.class), serial_number);
        return s;
    }

    public int countAll() {
        String sql = "select count(*) from books";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }
}
