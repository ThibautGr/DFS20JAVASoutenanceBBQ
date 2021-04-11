package it.akademy.dfs20evalbbq.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Barbecue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String type;

    /*
        relation externe
    */
    @JsonManagedReference(value = "barbecue-persons")
    @OneToMany
    private List<Person> persons;

    @JsonManagedReference(value = "barbecue-aliments")
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Aliment> aliments;

    public Barbecue() { }
    public Barbecue(int id, String name) {
        this.id = id;
        this.name = name;
        this.persons = new ArrayList<>();
        this.aliments = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Person> getPersons() { return persons; }

    public void setPersons(List<Person> persons) { this.persons = persons; }

    public List<Aliment> getAliments() { return aliments; }

    public void setAliments(List<Aliment> aliments) { this.aliments = aliments; }
}
