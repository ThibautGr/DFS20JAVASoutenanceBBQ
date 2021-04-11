package it.akademy.dfs20evalbbq.controllers;

import it.akademy.dfs20evalbbq.dao.AlimentDao;
import it.akademy.dfs20evalbbq.dao.BarbecueDao;
import it.akademy.dfs20evalbbq.dao.PersonDao;
import it.akademy.dfs20evalbbq.models.Aliment;
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
    private final AlimentDao alimentDao;
    private final BarbecueDao barbecueDao;

    @Autowired
    public PersonController(PersonDao personDao, AlimentDao alimentDao, BarbecueDao barbecueDao) {
        this.personDao = personDao;
        this.alimentDao = alimentDao;
        this.barbecueDao = barbecueDao;
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

//    @DeleteMapping("/{id}")
//    public ResponseEntity<?> deletePersonn(@PathVariable int id){
//        Person person = personDao.findById(id);
//        if (person == null){
//            return new ResponseEntity<>(HttpStatus.OK);
//        }
//        personDao.deleteById(id);
//        return new ResponseEntity<>(person,HttpStatus.NO_CONTENT);
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePerson(@PathVariable int id){
        Person person = personDao.findById(id);
        if (person == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<Barbecue> barbecues = barbecueDao.findAllByPersons(person);
        if (barbecues != null){
            for (Barbecue barbecue: barbecues){
                barbecue.getPersons().removeIf(person1 -> person1.getId() == id);
                barbecueDao.save(barbecue);
            }
        }
        personDao.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<Person> putPerson(@PathVariable int id, @RequestBody Person person){
//        Person modifedPerson = personDao.findById(id);
//        System.out.println("inside Put method");
//
//        if(modifedPerson == null){
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        person.setId(id);
//        modifedPerson = personDao.save(person);
//        return new ResponseEntity<>(modifedPerson, HttpStatus.OK);
//    }

    @PutMapping("/{personId}/aliment/{alimentid}")
    public ResponseEntity<Person> addAlimentsInPerson(@PathVariable int personId, @PathVariable int alimentid){

        Person modifedPerson = personDao.findById(personId);

        if(modifedPerson == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Aliment alimentToAdd = alimentDao.findById(alimentid);

        if(alimentToAdd == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if(modifedPerson.getBarbecue() != null){
            Barbecue barbecueQuiReçois = barbecueDao.findById(modifedPerson.getBarbecue().getId());

            barbecueQuiReçois.getAliments().add(alimentToAdd);
            barbecueDao.save(barbecueQuiReçois);

        }

        modifedPerson.getAliments().add(alimentToAdd);
        alimentToAdd.setPerson(modifedPerson);
        modifedPerson.setId(personId);
        personDao.save(modifedPerson);
        return new ResponseEntity<>(modifedPerson, HttpStatus.OK);
    }


}
