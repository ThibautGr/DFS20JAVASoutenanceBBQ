package it.akademy.dfs20evalbbq.dao;


import it.akademy.dfs20evalbbq.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonDao extends JpaRepository<Person, Integer> {

    List<Person> findAll();

    List<Person> findAllByName(String name);

    Person save(Person person);

    Person findById(int id);

    void deleteById(int id);

}
