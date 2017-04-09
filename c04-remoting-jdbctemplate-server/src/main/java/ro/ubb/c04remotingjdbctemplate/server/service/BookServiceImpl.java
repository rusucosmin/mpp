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

        String sql = "select * from books;";
//        return jdbcTemplate.query(sql, (rs, i) -> new book(rs.getLong("id"), rs.getString("serial_number"),
//                rs.getString("name"), rs.getInt("group_number")));

         return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Book.class));
    }

    public void addBook(Book b) {
        if(b == null)
            throw new IllegalArgumentException("book is null, too bad");

        String sql = "insert into books (id, title, author, year, cnt) values (?,?,?,?,?);";
        jdbcTemplate.update(sql,  b.getID(), b.getTitle(), b.getAuthor(), b.getYear(), b.getCnt());
    }

    public void updateBook(Book b) {
        if(b == null)
            throw new IllegalArgumentException("book is null, too bad");
        String sql = "update books set title=?, author=?, year=?, cnt=? where id=?;";
        jdbcTemplate.update(sql, b.getTitle(), b.getAuthor(), b.getYear(), b.getCnt(), b.getID());
    }

    public void deleteBook(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("id must not be null");
        }
        String sql = "delete from books where id=?";
        jdbcTemplate.update(sql, id);
    }

    public int countAll() {
        String sql = "select count(*) from books;";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }
}
