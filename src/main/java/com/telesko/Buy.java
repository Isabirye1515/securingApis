package com.telesko;

import java.util.*;

import javax.persistence.*;


@Entity
@Table(name ="buys")
public class Buy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "movie_name")
    private String movieName;
    @ManyToOne
    @JoinColumn(name ="customer_id"  )
    private Customer customer;
    @Column(name = "buy_date")
    private Date  buyDate;
public Buy (){}
public Buy(String movieName, Customer customer,Date buyDate){
    this.buyDate =buyDate;
    this.movieName = movieName;
    this.customer = customer;
}
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
public Date getBuyDate() {
    return buyDate;
}
public void setBuyDate(Date buyDate) {
    this.buyDate = buyDate;
}

}
   
