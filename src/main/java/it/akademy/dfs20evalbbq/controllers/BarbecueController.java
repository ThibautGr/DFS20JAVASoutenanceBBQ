package it.akademy.dfs20evalbbq.controllers;


import it.akademy.dfs20evalbbq.dao.BarbecueDao;
import it.akademy.dfs20evalbbq.dao.PersonDao;
import it.akademy.dfs20evalbbq.models.Barbecue;
import it.akademy.dfs20evalbbq.models.Person;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/barbecues")
public class BarbecueController {

    private final BarbecueDao barbecueDao;

    private  final PersonDao personDao;

    @Autowired
    public BarbecueController(BarbecueDao barbecueDao, PersonDao personDao) {
        this.barbecueDao = barbecueDao;
        this.personDao = personDao;
    }

    /*
     *Read all
     */
//    @GetMapping
//    public ResponseEntity<List<Barbecue>> getAllBarbecue(){
//        List<Barbecue> barbecues= barbecueDao.findAll();
//        return new ResponseEntity<>(barbecues, HttpStatus.OK);
//    }


    /*
    *Read one by id
     */

    @GetMapping("/id/{id}")
    public ResponseEntity<Barbecue> getBarbecueById(@PathVariable int id){
        Barbecue barbecue = barbecueDao.findById(id);
        if(barbecue == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(barbecue, HttpStatus.OK);
    }

    /*
     *Read all or one by name
     */
    @GetMapping
    public ResponseEntity<List<Barbecue>> getAllBarbecueById(@RequestParam(required = false) String name){
        List<Barbecue> barbecues;
        if(name == null){
            barbecues = barbecueDao.findAll();
            return new ResponseEntity<>(barbecues, HttpStatus.OK);
        }
        barbecues = barbecueDao.findAllByName(name);
        return new ResponseEntity<>(barbecues,HttpStatus.OK);
    }
//    @GetMapping("/namedecon/{name}")
//    public ResponseEntity<List<Barbecue>> getAllBarbecueById(@PathVariable String name){
//        List<Barbecue> barbecues = barbecueDao.findAllByName(name);
//        return new ResponseEntity<>(barbecues,HttpStatus.OK);
//    }


    /*
     * Create
     */
    @PostMapping
    public ResponseEntity<Barbecue> createBarbecue(@RequestBody Barbecue barbecue){
        Barbecue addedBarbecue = barbecueDao.save(barbecue);
        return new ResponseEntity<>(addedBarbecue, HttpStatus.CREATED);
    }

    /*
    *Delete
     */
//idée de merde si nom pas unique
//    @DeleteMapping("/{name}")
//    public ResponseEntity<?>deleteBarbecue(@PathVariable String name){
//       Barbecue barbecueToDelete = barbecueDao.findByName(name);
//        if (barbecueToDelete == null){
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//       barbecueDao.deleteByName(name);
//       return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBarbecue(@PathVariable int id){
        Barbecue barbecue = barbecueDao.findById(id);
        if (barbecue == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        barbecueDao.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<Barbecue> putBarbecue(@PathVariable int id, @RequestBody Barbecue barbecue){
//        Barbecue modifiedBarbecue = barbecueDao.findById(id);
//        if (modifiedBarbecue == null){
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        barbecue.setId(id);
//        modifiedBarbecue = barbecueDao.save(barbecue);
//        return new ResponseEntity<>(modifiedBarbecue, HttpStatus.OK);
//    }

    @PutMapping("/{bbqId}/person/{personId}")
    public ResponseEntity<Barbecue> invitePersonInBbq(@PathVariable int bbqId, @PathVariable int personId){

        Barbecue barbecue = barbecueDao.findById(bbqId);
     if (barbecue == null){
         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
     }
        Person person = personDao.findById(personId);
     if (person == null){
         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
     }

     barbecue.getPersons().add(person);
     person.setBarbecue(barbecue);
     barbecueDao.save(barbecue);
     return new ResponseEntity<>(barbecue, HttpStatus.OK);

    }




}
