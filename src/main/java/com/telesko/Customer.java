package com.telesko;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "arrival")
    private Date arrival;

    public Customer (){}

    public Customer(String name, String email, Date arrival) {
        this.name = name;
        this.email = email;
        this.arrival = arrival;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

   
    public Date getCreatedAt() {
        return arrival;
    }

    public void setCreatedA(Date arrival) {
        this.arrival = arrival;
    }

    
    
}

