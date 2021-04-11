package it.akademy.dfs20evalbbq.dao;


import it.akademy.dfs20evalbbq.models.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityDao extends JpaRepository<City, Integer> {
    List<City> findAll();

    City findById(int id);

   List<City> findAllByName(String name);

    City save(City city);
    void deleteById(int id);

}
