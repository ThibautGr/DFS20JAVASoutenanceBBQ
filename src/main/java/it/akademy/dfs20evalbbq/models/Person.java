package it.akademy.dfs20evalbbq.models;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String lastName;

    public Person(){}
    public Person(int id, String firstName, String lastName) {
        this.id = id;
        this.name = firstName;
        this.lastName = lastName;
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
}
