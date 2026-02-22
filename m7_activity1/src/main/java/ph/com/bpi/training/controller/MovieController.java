
package ph.com.bpi.training.controller;

import static spark.Spark.get;
import static spark.Spark.post;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ph.com.bpi.training.utility.JsonUtil;
import ph.com.bpi.training.model.Movie;
import ph.com.bpi.training.service.MovieService;

public class MovieController {

    private static final Logger logger = LoggerFactory.getLogger(MovieController.class);

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    /**
     * Registers all routes exposed by this controller.
     */
    public void registerRoutes() {

        // Health Check
        get("/check-connection", (req, res) -> {
            res.type("application/json");
            Map<String, String> response = new HashMap<>();
            response.put("status", "Server is running");
            return JsonUtil.toJson(response);
        });

        // GET /movies
        get("/movies", (req, res) -> {
            res.type("application/json");
            Map<String, Object> response = new HashMap<>();
            try {
                response.put("status", "Success");
                response.put("data", movieService.getAllMovies());
                return JsonUtil.toJson(response);
            } catch (Exception e) {
                logger.error("Error fetching movies", e);
                res.status(500);
                response.put("status", "ERROR");
                response.put("data", null);
                response.put("message", "Internal Server Error");
                return JsonUtil.toJson(response);
            }
        });

        // POST /movies
        post("/movies", (req, res) -> {
            res.type("application/json");
            Map<String, Object> response = new HashMap<>();

            try {
                if (req.body() == null || req.body().isBlank()) {
                    res.status(400);
                    response.put("status", "ERROR");
                    response.put("data", null);
                    response.put("message", "Request body cannot be null");
                    return JsonUtil.toJson(response);
                }

                Movie moviePayload = JsonUtil.fromJson(req.body(), Movie.class);
                Movie saved = movieService.createMovie(moviePayload);

                response.put("status", "Success");
                response.put("data", saved);
                return JsonUtil.toJson(response);

            } catch (Exception e) {
                logger.error("Error creating movie", e);
                res.status(500);
                response.put("status", "ERROR");
                response.put("data", null);
                response.put("message", "Internal Server Error");
                return JsonUtil.toJson(response);
            }
        });
    }
}
