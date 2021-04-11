package it.akademy.dfs20evalbbq.controllers;

import it.akademy.dfs20evalbbq.dao.BarbecueDao;
import it.akademy.dfs20evalbbq.dao.CityDao;
import it.akademy.dfs20evalbbq.models.Barbecue;
import it.akademy.dfs20evalbbq.models.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cities")
public class CityController {

private final CityDao cityDao;
private final BarbecueDao barbecueDao;

@Autowired

public CityController(CityDao cityDao, BarbecueDao barbecueDao) {
        this.cityDao = cityDao;
        this.barbecueDao = barbecueDao;
}

        @GetMapping
        public ResponseEntity<List<City>> getAllCitiesById(@RequestParam(required = false)String name){
                List<City> cities;
                if (name == null){
                        cities = cityDao.findAll();
                        return new ResponseEntity<>(cities, HttpStatus.OK);
                }
                cities = cityDao.findAllByName(name);
                return new ResponseEntity<>(cities, HttpStatus.OK);
        }

        @PostMapping
        public ResponseEntity<City> createCity(@RequestBody City city){
                City newCity = cityDao.save(city);
                return new ResponseEntity<>(newCity, HttpStatus.CREATED);
        }

        @PutMapping("/{cityId}/barbecue/{barbecueid}")
        public ResponseEntity<City> putCity(@PathVariable int cityId, @PathVariable int barbecueid){
               City modifiedCity = cityDao.findById(cityId);
                if(modifiedCity == null){
                    System.out.println("citer pas trouver");
                        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
                Barbecue barbecueToAdd = barbecueDao.findById(barbecueid);
                if (barbecueToAdd == null){
                    System.out.println("BBQ pas trouver");
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }

                modifiedCity.getBarbecues().add(barbecueToAdd);
                barbecueToAdd.setCity(modifiedCity);
                cityDao.save(modifiedCity);
                return new ResponseEntity<>(modifiedCity, HttpStatus.OK);

        }



}
