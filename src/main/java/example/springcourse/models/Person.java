package example.springcourse.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Person {

    private Integer id;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 10, message = "Name should be between 2 and 10 characters")
    private String name;

    @NotEmpty(message = "surname should not be empty")
    @Size(min = 2, max = 10, message = "Name should be between 2 and 10 characters")
    private String surname;

    @NotEmpty(message = "patronymic should not be empty")
    @Size(min = 2, max = 10, message = "Name should be between 2 and 10 characters")
    private String patronymic;

    @Min(value = 1900, message = "year should to be greater than 1900")
    private Integer year;

    public Person() {
    }


    public Person(Integer id, String name, String surname, String patronymic, Integer year) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.year = year;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }
}
