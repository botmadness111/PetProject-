package example.springcourse.controllers;

import example.springcourse.dao.PersonDao;
import example.springcourse.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
@RequestMapping("/people")
public class PersonController {

    private final PersonDao personDao;

    @Autowired
    public PersonController(PersonDao personDao) {
        this.personDao = personDao;
    }

    @GetMapping
    public String people(Model model) {
        model.addAttribute("people", personDao.getPeople());

        return "people/people";
    }

    @GetMapping("/{id}")
    public String person(Model model, @PathVariable("id") int id) {
        Person person = personDao.getPerson(id);
        model.addAttribute(person);

        return "person/person";
    }

    @GetMapping("/new")
    public String addPerson(Model model) {
        model.addAttribute(new Person());
        return "people/new";
    }

    @PostMapping
    public String addNewPerson(@ModelAttribute("person") @Valid Person person,
                               BindingResult bindingResult) {

        if (bindingResult.hasErrors()) return "/people/new";

        personDao.add(person);

        return "redirect:/people";
    }


}
