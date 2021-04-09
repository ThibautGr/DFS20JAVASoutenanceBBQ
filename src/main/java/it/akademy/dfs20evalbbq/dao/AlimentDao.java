package it.akademy.dfs20evalbbq.dao;


import it.akademy.dfs20evalbbq.models.Aliment;
import it.akademy.dfs20evalbbq.models.Barbecue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlimentDao extends JpaRepository<Aliment, Integer> {

    List<Aliment> findAll();

    List<Aliment> findAllByName(String name);

    Aliment findById(int id);

    Aliment findByName(String name);

    Aliment save(Aliment aliment);

    void deleteById(int id);
}
