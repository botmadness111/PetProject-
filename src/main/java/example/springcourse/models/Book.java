package example.springcourse.models;

public class Book {
    private Integer id;

    private String name;

    public void setId(Integer id) {
        this.id = id;
    }

    private String authorName;
    private Integer year;
    private Integer person_id;


    public Book(Integer id, String name, String authorName, Integer year, Integer person_id) {
        this.id = id;
        this.name = name;
        this.authorName = authorName;
        this.year = year;
        this.person_id = person_id;
    }

    public Book() {
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getPerson_id() {
        return person_id;
    }

    public void setPerson_id(Integer person_id) {
        this.person_id = person_id;
    }
}
