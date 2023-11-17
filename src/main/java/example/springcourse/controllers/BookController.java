package example.springcourse.controllers;

import example.springcourse.dao.BookDao;
import example.springcourse.dao.PersonDao;
import example.springcourse.models.Book;
import example.springcourse.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/books")
public class BookController {

    private final BookDao bookDao;
    private final PersonDao personDao;

    @Autowired
    public BookController(BookDao bookDao, PersonDao personDao) {
        this.bookDao = bookDao;
        this.personDao = personDao;
    }

    @GetMapping
    public String books(Model model) {
        model.addAttribute("books", bookDao.getBooks());
        return "books/books";
    }

    @GetMapping("/{id}")
    public String book(@PathVariable("id") int id, Model model) {
        Book book = bookDao.getBook(id);
        model.addAttribute("book", book);
        model.addAttribute("people", personDao.getPeople());

        Person personTmp = personDao.getPerson(bookDao.getPersonId(id));
        if (personTmp == null) {
            personTmp = new Person();
            personTmp.setName("NoName");
            model.addAttribute(personTmp);
        } else {
            model.addAttribute(personTmp);
        }


        return "/book/book";
    }

    @GetMapping("/{id}/edit")
    public String addNewBook(@PathVariable("id") int id, Model model) {
        Book book = bookDao.getBook(id);
        model.addAttribute("book", book);

        return "books/edit";
    }

    @PostMapping("/{id}")
    public String updateBook(@ModelAttribute("book") Book book) {
        bookDao.updateBook(book);
        return "redirect:/books";
    }

    @PostMapping("/{id}/change")
    public String updateBook(@ModelAttribute("person") Person person, @PathVariable("id") int id) {
        System.out.println(person.getId());
        bookDao.updateOwner(person.getId(), id);

        return "redirect:/books";
    }

    @GetMapping("/new")
    public String addNewBook(Model model) {
        model.addAttribute("book", new Book());
        return "books/new";
    }

    @PostMapping()
    public String saveNewBook(@ModelAttribute("book") Book book) {
        Book bookToBeAddition = new Book();
        bookToBeAddition.setName(book.getName());
        bookToBeAddition.setAuthorName(book.getAuthorName());
        bookToBeAddition.setYear(book.getYear());

        bookDao.add(bookToBeAddition);
        return "redirect:/books";
    }
}
