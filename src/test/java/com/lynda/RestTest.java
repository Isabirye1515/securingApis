package com.lynda;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.telesko.Customer;
import com.telesko.Movie;
import com.telesko.MovieRest;
import com.telesko.MovieService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class RestTest {

    private MockMvc mockMvc;

    @Mock
    private MovieService movieService; // Mock the MovieService

    @InjectMocks
    private MovieRest movieRest; // Inject mocks into MovieRest controller

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mocks
        mockMvc = MockMvcBuilders.standaloneSetup(movieRest).build(); // Setup MockMvc with the controller
    }

    @Test
    public void testMovies() throws Exception {
        // Prepare the test data
        List<Movie> list = new ArrayList<>();
        list.add(new Movie("JACKY CHAN", "action", 2010, new Date(), 10));

        // Mock the behavior of the movieService
        when(movieService.getAll()).thenReturn(list);

        // Perform the request and assert the response
        mockMvc.perform(get("/api/movies") // Perform GET request
                .contentType(MediaType.APPLICATION_JSON)) // Specify content type
                .andExpect(status().isOk()) // Expect HTTP status 200
                .andExpect(content().contentType(MediaType.APPLICATION_JSON)) // Expect JSON content
                .andExpect(jsonPath("$[0].name").value("JACKY CHAN")); // Assert the movie name
    }
    @Test
    public void getCustomers() throws Exception{
        List<Customer> customers = new ArrayList<>();
        when(movieService.getAllCustomers()).thenReturn(customers);
        customers.add(new Customer("isabirye elijah","isabiryeelijah10@gmail.com",new Date()));
        mockMvc.perform(get("/api/customers")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$[0].name").value("isabirye elijah"));
    }
}
