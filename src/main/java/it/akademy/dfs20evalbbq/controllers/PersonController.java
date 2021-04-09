package it.akademy.dfs20evalbbq.controllers;

import it.akademy.dfs20evalbbq.dao.PersonDao;
import it.akademy.dfs20evalbbq.models.Barbecue;
import it.akademy.dfs20evalbbq.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
import java.util.List;

@RestController
@RequestMapping("/persons")
public class PersonController {

    private final PersonDao personDao;


    @Autowired
    public PersonController(PersonDao personDao) {
        this.personDao = personDao;
    }



    @PostMapping
    public ResponseEntity<Person> createPersone(@RequestBody Person person){
        personDao.save(person);
        return new ResponseEntity<>(person, HttpStatus.CREATED);
    }
    //Read all and by one param

  @GetMapping
  public ResponseEntity<List<Person>> getAllPersonsByParam(@RequestParam(required = false) String name){
        List<Person> persons;
        if (name == null){
            persons = personDao.findAll();
            return new ResponseEntity<>(persons, HttpStatus.OK);
        }
        persons = personDao.findAllByName(name);
        return new ResponseEntity<>(persons,HttpStatus.OK);
  }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePersonn(@PathVariable int id){
        Person person = personDao.findById(id);
        if (person == null){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        personDao.deleteById(id);
        return new ResponseEntity<>(person,HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Person> putPerson(@PathVariable int id, @RequestBody Person person){
        Person modifedPerson = personDao.findById(id);
        System.out.println("inside Put method");

        if(modifedPerson == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        person.setId(id);
        modifedPerson = personDao.save(person);
        return new ResponseEntity<>(modifedPerson, HttpStatus.OK);
    }


}
