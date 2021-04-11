package it.akademy.dfs20evalbbq.models;


import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @JsonManagedReference(value = "City-barbecues")
    @OneToMany
    private List<Barbecue> barbecues;

    private String name;
    private int postalCode;

    public City(){}

    public City(int id, String name, int postalCode) {
        this.id = id;
        this.name = name;
        this.postalCode = postalCode;

        this.barbecues = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    public List<Barbecue> getBarbecues() {
        return barbecues;
    }

    public void setBarbecues(List<Barbecue> barbecues) {
        this.barbecues = barbecues;
    }
}
