package ph.com.bpi.training;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import ph.com.bpi.training.controller.MovieController;
import ph.com.bpi.training.model.Movie;
import ph.com.bpi.training.repository.MovieRepository;
import ph.com.bpi.training.service.MovieService;
import ph.com.bpi.training.utility.EntityManagerUtil;
import ph.com.bpi.training.utility.JsonUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;


public class Main {
	

private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        // Start server on port 4567
        port(4567);

        // Wire dependencies
        MovieService movieService = new MovieService();
        MovieController movieController = new MovieController(movieService);

        // Register routes
        movieController.registerRoutes();

        logger.info("Server started on port 4567");
    }    

}
