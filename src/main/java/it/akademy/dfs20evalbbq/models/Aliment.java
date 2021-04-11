package it.akademy.dfs20evalbbq.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
public class Aliment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    /*
        relation externe
     */

    @JsonBackReference(value = "barbecue-aliments")
    @ManyToOne
    private Barbecue barbecue;

    @JsonBackReference(value = "person-aliment")
    @ManyToOne
    private Person person;

    public Aliment(){};

    public Aliment(int id, String name) {
        this.id = id;
        this.name = name;
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


    public void setBarbecue(Barbecue barbecue) {

        this.barbecue = barbecue;
    }
    public Barbecue getBarbecue() {

        return barbecue;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
