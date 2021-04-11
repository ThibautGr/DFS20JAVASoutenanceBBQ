package it.akademy.dfs20evalbbq.dao;

import it.akademy.dfs20evalbbq.models.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface BarbecueDao extends JpaRepository<Barbecue, Integer> {

    List<Barbecue> findAll();

    List<Barbecue> findAllByName(String name);

    List<Barbecue> findAllByPersons(Person person);
    List<Barbecue> findAllByAliments(Aliment aliment);

    Barbecue findById(int id);

    Barbecue findByName(String name);

    Barbecue save(Barbecue barbecue);

    void deleteById(int id);


    //@Transactional
    // void deleteByName(String name); // utilisation d'un delete dans le cas ou un chiffre c'est facile de ce tromper contrairement a un nom. (faux si nom pas unique)


}
