package com.telesko;
import javax.persistence.*;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
class PersistCustomers{
    public static void main(String[] args) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer("John Doe", "john.doe@example.com", new Date()));
        customers.add(new Customer("Jane Smith", "jane.smith@example.com", new Date()));
        customers.add(new Customer("Michael Johnson", "michael.johnson@example.com", new Date()));
        customers.add(new Customer("Emily Davis", "emily.davis@example.com", new Date()));
        customers.add(new Customer("Robert Brown", "robert.brown@example.com", new Date()));
        customers.add(new Customer("Sarah Wilson", "sarah.wilson@example.com", new Date()));
        customers.add(new Customer("James Taylor", "james.taylor@example.com", new Date()));
        customers.add(new Customer("Elizabeth Thomas", "elizabeth.thomas@example.com", new Date()));
        customers.add(new Customer("Daniel White", "daniel.white@example.com", new Date()));
        customers.add(new Customer("Jessica Martin", "jessica.martin@example.com", new Date()));
        customers.add(new Customer("William Harris", "william.harris@example.com", new Date()));
        customers.add(new Customer("Sophia Thompson", "sophia.thompson@example.com", new Date()));
        customers.add(new Customer("Joseph Garcia", "joseph.garcia@example.com", new Date()));
        customers.add(new Customer("Olivia Clark", "olivia.clark@example.com", new Date()));
        customers.add(new Customer("David Rodriguez", "david.rodriguez@example.com", new Date()));
        customers.add(new Customer("Chloe Lewis", "chloe.lewis@example.com", new Date()));
        customers.add(new Customer("Alexander Walker", "alexander.walker@example.com", new Date()));
        customers.add(new Customer("Ava Hall", "ava.hall@example.com", new Date()));
        customers.add(new Customer("Liam Allen", "liam.allen@example.com", new Date()));
        customers.add(new Customer("Isabella Young", "isabella.young@example.com", new Date()));
for(Customer customer:customers){
session.save(customer);
}
transaction.commit();
session.close();
        } catch (Exception e) {
            if(transaction != null){
                transaction.rollback();
            }

            e.printStackTrace();
        }
    }
}