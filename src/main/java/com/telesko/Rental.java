package com.telesko;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;


@Entity
@Table(name = "rentals")
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "movie_name")
    private String movieName;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false) // Ensure this cannot be null
    private Customer customer;

    @Column(name = "rental_date", updatable = false)
    private Date rentalDate;

    @Column(name = "return_date")
    private Date returnDate;

    // Default constructor
    public Rental() {}

    // Constructor with parameters
    public Rental(String movieName, Customer customer, Date rentalDate, Date returnDate) {
        this.movieName = movieName;
        this.customer = customer;
        this.rentalDate = rentalDate;
        this.returnDate = returnDate;
    }

    // Getters and setters...
    public int getId() {
        return id;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Date getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(Date rentalDate) {
        this.rentalDate = rentalDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }
}

class PersistRental {

    public static void main(String[] args) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            List<Rental> rentals = createRentals(session);
            for (Rental rental : rentals) {
                session.save(rental);
            }
            transaction.commit();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    private static List<Rental> createRentals(Session session) throws ParseException {
        List<Rental> rentals = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        // Creating 20 demo rentals
        for (int i = 1; i <= 20; i++) {
            String movieName = "Movie " + i;
            Date arrivalDate = dateFormat.parse("2024-10-" + (10 - i)); // Example arrival date
            Customer customer = new Customer("Customer " + i, "customer" + i + "@example.com", arrivalDate); // Pass arrival date
            session.save(customer); // Persist the customer
            Date rentalDate = dateFormat.parse("2024-10-" + (22 - i)); // Adjust rental date
            Date returnDate = dateFormat.parse("2024-10-" + (29 - i)); // Adjust return date
            rentals.add(new Rental(movieName, customer, rentalDate, returnDate));
        }
        return rentals;
    }
}
