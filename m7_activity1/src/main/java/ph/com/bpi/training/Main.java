package ph.com.bpi.training;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;


public class Main {
	
	private static final Logger logger =  LoggerFactory.getLogger(Main.class);
	private static final ObjectMapper mapper = new ObjectMapper();
	 
    public static void main(String[] args) {
    	
    	 // Start server on port 4567 (default)
        port(4567);
        
        // add routes here
        get("/check-connection", (req, res) -> {
        	res.type("application/json");
        	
            Map<String, String> response = new HashMap<>();
            response.put("status", "Server is running");
            
            return JsonUtil.toJson(response);
        });
        // Get Movies List
        get("/movies", (req, res) -> {
            Map<String, Object> response = new HashMap<String, Object>();
         // intialize entityManager;
        	EntityManager em = EntityManagerUtil.getInstance().createEntityManager();        	
            // initialize movieRepository
        	MovieRepository movieRepository = new MovieRepository(em);
        	
        	em.getTransaction().begin();
            List<Movie> movies = movieRepository.findAll();
            res.type("application/json");
            response.put("status", "Success");
            response.put("data",movies);
            em.close();
            
            return JsonUtil.toJson(response);
        });
        
        
        //Post Movies List
        post("/movies", (req, res) -> {
        	Map<String, Object> response = new HashMap<String, Object>();	
        	
        	res.type("application/json");
        	
            if(req.body() == null || req.body().isBlank() ) {            	
            	res.status(400);
            	response.put("status", "ERROR");
                response.put("data", null);
                response.put("message", "Request body cannot be null");
                
                return JsonUtil.toJson(response);
            }
           
            Movie data = JsonUtil.fromJson(req.body(), Movie.class);
            EntityManager em = EntityManagerUtil.getInstance().createEntityManager();
            MovieRepository movieRepository = new MovieRepository(em);
            em.getTransaction().begin();
            Movie saveMovie = movieRepository.save(data);
            em.getTransaction().commit();
            em.close();
            
            response.put("status", "Success");
            response.put("data", saveMovie);
            
            return JsonUtil.toJson(response);
        });
        
    }
    
    

}
