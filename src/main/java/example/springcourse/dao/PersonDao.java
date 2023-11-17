package example.springcourse.dao;

import example.springcourse.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDao {
    private static JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> getPeople() {
        List<Person> people;
        people = jdbcTemplate.query("SELECT * FROM person", new PersonMapper());
        return people;
    }

    public Person getPerson(int id) {
        List<Person> people = jdbcTemplate.query(
                "SELECT * FROM person WHERE id=?",
                new Object[]{id},
                new PersonMapper());

        for (Person person : people) {
            if (person.getId() == id) return person;
        }
        return null;
    }

    public void add(Person person) {

        jdbcTemplate.update("INSERT INTO person (name, surname, patronymic, year) VALUES (?, ?, ?, ?)",
                person.getName(), person.getSurname(), person.getPatronymic(), person.getYear());

    }

}
