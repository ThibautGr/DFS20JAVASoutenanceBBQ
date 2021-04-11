package it.akademy.dfs20evalbbq.controllers;


import it.akademy.dfs20evalbbq.dao.AlimentDao;
import it.akademy.dfs20evalbbq.models.Aliment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/aliments")
public class AlimentController {
    private final AlimentDao alimentDao;

    @Autowired
    public AlimentController(AlimentDao alimentDao){this.alimentDao = alimentDao;}

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

    @DeleteMapping( "/{id}")
    public ResponseEntity<?> deleteAliment(@PathVariable int id){
        Aliment aliment = alimentDao.findById(id);
        if(aliment == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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
