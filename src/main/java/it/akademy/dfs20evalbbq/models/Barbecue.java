package it.akademy.dfs20evalbbq.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    private String date;
    private String address;


    /*
        relation externe
    */
    @JsonBackReference(value = "City-barbecues")
    @ManyToOne
    private City city;

    @JsonManagedReference(value = "barbecue-persons")
    @OneToMany
    private List<Person> persons;

    @JsonManagedReference(value = "barbecue-aliments")
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Aliment> aliments;

    public Barbecue() { }
    public Barbecue(int id, String name, String type, String date, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.date = date;
        this.type = type;
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

    public String getType() {
        return type;
    }

    public String getDate() {
        return date;
    }

    public String getAddress() {
        return address;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
