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

import java.util.List;

@RestController

@RequestMapping("/aliments")
public class AlimentController {
    private final AlimentDao alimentDao;
    private final PersonDao personDao;
    private final BarbecueDao barbecueDao;

    @Autowired
    public AlimentController(AlimentDao alimentDao,PersonDao personDao,BarbecueDao barbecueDao){
        this.alimentDao = alimentDao;
        this.personDao = personDao;
        this.barbecueDao = barbecueDao;
    }

    @PostMapping
    public ResponseEntity<Aliment> createAliment(@RequestBody Aliment aliment){
        Aliment addAliment = alimentDao.save(aliment);
        return new ResponseEntity<>(addAliment, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Aliment>>getAllAlimentById(@RequestParam(required = false)String name){
        List<Aliment> aliments;
        if (name == null){
            aliments = alimentDao.findAll();
            return new ResponseEntity<>(aliments, HttpStatus.OK);
        }
        aliments = alimentDao.findAllByName(name);
        return new ResponseEntity<>(aliments,HttpStatus.OK);
    }

@DeleteMapping("/{id}")
public ResponseEntity<?>deleteFood(@PathVariable int id){
        Aliment aliment = alimentDao.findById(id);
        if (aliment == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<Person> personList = personDao.findAllByAliments(aliment);
        List<Barbecue> barbecues = barbecueDao.findAllByAliments(aliment);
        if( barbecues == null && personList == null){
             alimentDao.deleteById(id);
        }
        if (personList != null){
            for (Person person: personList){
                person.getAliments().removeIf(aliment1 -> aliment1.getId()==1);

                    personDao.save(person);

            }
        }
        if (barbecues != null){
            for (Barbecue barbecue: barbecues){
                barbecue.getAliments().removeIf(aliment1 -> aliment1.getId()==id);
                barbecueDao.save(barbecue);
            }
        }
        alimentDao.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
}

    @PutMapping("/{id}")
    public ResponseEntity<Aliment> putAliment(@PathVariable int id, @RequestBody Aliment aliment){
        Aliment modifiedAliment = alimentDao.findById(id);
        if (modifiedAliment == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        aliment.setId(id);
        modifiedAliment = alimentDao.save(aliment);
        return new ResponseEntity<>(modifiedAliment, HttpStatus.OK);
    }

}
