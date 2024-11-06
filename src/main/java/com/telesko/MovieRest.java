package com.telesko;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class MovieRest {

@Autowired
MovieService movieService;


@CrossOrigin
@GetMapping("movies")
public ResponseEntity<List<Movie>> getMovies(){
    List<Movie> movies = movieService.getAll();

    return ResponseEntity.ok(movies);
}

@CrossOrigin
@GetMapping("customers")
public ResponseEntity<List<Customer>>  getCustomers(){
    List<Customer> customers = movieService.getAllCustomers();
    return ResponseEntity.ok(customers);
}
@CrossOrigin
@GetMapping("rentals")
public ResponseEntity<List<Rental>> getRentals(){
    List<Rental> rentals = movieService.getAllRentals();
    return ResponseEntity.ok(rentals);
}
@CrossOrigin
@GetMapping("buyers")
public ResponseEntity<List<Buy>> getBuyers(){
    List<Buy> buyers = movieService.getAllBuyers();
    return ResponseEntity.ok(buyers);
}

@PostMapping("/addMovie")
public ResponseEntity<String> addMovie(
    @RequestParam String name,
    @RequestParam String genere,
    @RequestParam int year,
    @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date created_at, // Specify the date format
    @RequestParam int price
) {
    Movie movie = new Movie();
    movie.setName(name);
    movie.setGenere(genere);
    movie.setYear(year);
    movie.setCreatedAt(created_at); // Set the passed date
    movie.setPrice(price);

    movieService.addMovie(movie);

    return ResponseEntity.ok("Movie added successfully");
}
@PostMapping("/addCustomer")
public ResponseEntity<String> addCustomer(
        @RequestParam String name,
        @RequestParam String email,
        @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date arrival) {
    Customer customer = new Customer();
    customer.setName(name);
    customer.setEmail(email);
    customer.setCreatedA(arrival);
    // Process the request here
    System.out.println("Name: " + name);
    System.out.println("Email: " + email);
    System.out.println("Arrival: " + arrival); // Should print a Date object
movieService.addCustomer(customer);
    return ResponseEntity.ok("Customer added successfully");
}
@GetMapping("/movie/{id}")
public ResponseEntity<Movie> getMovieById(@PathVariable int id){
    Movie movie = movieService.getMovieBYId(id);
    if(movie == null){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
    return ResponseEntity.ok(movie);
}
@GetMapping("/customer/{id}")
public ResponseEntity<Customer> getCustomerById(@PathVariable int id){
    Customer customer = movieService.getByIdCustomer(id);
    if(customer == null ){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
    return ResponseEntity.ok(customer);
}
@PostMapping("/addBuyer")
public ResponseEntity<String> addBuyer(
        @RequestParam String movieName,
        @RequestParam String name,
        @RequestParam String email,
        @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date arrival,
        @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date buyDate
    ) {
        // Create a new Customer instance and set its attributes
        Customer customer = new Customer();
        customer.setName(name);
        customer.setEmail(email);
        customer.setCreatedA(arrival);
        movieService.addCustomer(customer);
        

        // Create a new Buy instance and set its attributes
        Buy buyer = new Buy();
        buyer.setMovieName(movieName);
        buyer.setBuyDate(buyDate);
        buyer.setCustomer(customer); // Assuming there's a setter for customer in Buy

        // Add the buyer using the movie service
        movieService.addBuyer(buyer);

        return ResponseEntity.ok("Added buyer successfully");
    }
    @DeleteMapping("/movie/{id}")
    public ResponseEntity<List<Movie>> removeMovie(@PathVariable int id) {
        // Attempt to delete the movie using the service
        boolean isDeleted = movieService.delete(Movie.class,id);

        if (!isDeleted) {
            // Return 404 Not Found if the movie was not found
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        // Retrieve the updated list of movies
        List<Movie> movies = movieService.getAll();

        // Return the updated movie list with 200 OK status
        return ResponseEntity.ok(movies);
    }

}