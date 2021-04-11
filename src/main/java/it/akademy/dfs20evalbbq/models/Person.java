package it.akademy.dfs20evalbbq.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String lastName;

    /*
        relation externe
    */
    @JsonBackReference(value = "barbecue-persons")
    @ManyToOne
    private Barbecue barbecue;

    @JsonManagedReference(value = "person-aliment")
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Aliment> aliments;

    public Person(){}
    public Person(int id, String firstName, String lastName) {
        this.id = id;
        this.name = firstName;
        this.lastName = lastName;


        this.aliments = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String firstName) {
        this.name = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Barbecue getBarbecue() {
        return barbecue;
    }

    public void setBarbecue(Barbecue barbecue) {
        this.barbecue = barbecue;
    }

    public List<Aliment> getAliments() {
        return aliments;
    }

    public void setAliments(List<Aliment> aliments) {
        aliments = aliments;
    }
}
