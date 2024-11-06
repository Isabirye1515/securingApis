package com.telesko;

import java.util.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MovieService {
@Autowired
private HibernateTemplate hTemplate;
 
public void setHTemplate(HibernateTemplate htTemplate){
    this.hTemplate = htTemplate;
}

public List<Movie> getAll(){
return hTemplate.loadAll(Movie.class);
}

public List<Customer> getAllCustomers(){
    return hTemplate.loadAll(Customer.class);
}
public List<Rental> getAllRentals(){
    return hTemplate.loadAll(Rental.class);
    }
    

public List<Buy> getAllBuyers(){
    return hTemplate.loadAll(Buy.class);
    }

    @Transactional(readOnly = false) // Allows write operations
    public void addMovie(Movie movie) {   
        hTemplate.save(movie);  // Now using the movie passed from the controller
    }
    @Transactional(readOnly = false)
    public void addCustomer(Customer customer){
        hTemplate.save(customer);

    }
    public Movie getMovieBYId(int id){
        Movie movie = hTemplate.get(Movie.class,id);
        return movie;
    }
    public Customer getByIdCustomer(int id){
        Customer customer = hTemplate.get(Customer.class,id);
        return customer;
    }
@Transactional(readOnly = false)
    public void addBuyer(Buy buy){
        hTemplate.save(buy);
    }

    @Transactional(readOnly = false)
    public boolean delete(Class<?> clazz ,int id){
Object entity = hTemplate.get(clazz, id);
if(entity !=null){
    hTemplate.delete(entity);
}
return true;
    }
}