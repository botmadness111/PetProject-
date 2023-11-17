package example.springcourse.dao;

import example.springcourse.models.Book;
import example.springcourse.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class BookDao {
    private static JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> getBooks() {
        return jdbcTemplate.query("SELECT * FROM book", new BookMapper());
    }

    public Book getBook(int id) {

        List<Book> books = jdbcTemplate.query(
                "SELECT * FROM book WHERE id = ?",
                new Object[]{id},
                new BookMapper());


        for (Book book : books) {
            if (book.getId() == id) return book;
        }
        return null;
    }

    public void updateBook(Book bookNew) {

        jdbcTemplate.update(
                "UPDATE book SET name=?, nameAuthor=?, year=? WHERE id=?",
                bookNew.getName(), bookNew.getAuthorName(), bookNew.getYear(), bookNew.getId()
        );

    }

    public void add(Book book) {

        jdbcTemplate.update(
                "INSERT INTO book(name, nameAuthor, year) VALUES (?, ?, ?)",
                book.getName(), book.getAuthorName(), book.getYear()
        );

    }

    public Integer getPersonId(int id) {

        List<Book> books = jdbcTemplate.query(
                "SELECT * FROM book WHERE id = ?",
                new Object[]{id},
                new BookMapper()
        );

        for (Book book : books) {
            return book.getPerson_id();
        }
        return null;
    }

    public void updateOwner(int person_id, int id) {
        jdbcTemplate.update(
                "UPDATE book SET person_id=? WHERE id=?",
                person_id, id
        );
    }
}
