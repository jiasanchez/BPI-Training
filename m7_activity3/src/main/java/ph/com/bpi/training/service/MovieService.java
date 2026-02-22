
package ph.com.bpi.training.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import ph.com.bpi.training.utility.EntityManagerUtil;
import ph.com.bpi.training.model.Movie;
import ph.com.bpi.training.repository.MovieRepository;

public class MovieService {

    private static final Logger logger = LoggerFactory.getLogger(MovieService.class);

    public List<Movie> getAllMovies() {
        EntityManager em = EntityManagerUtil.getInstance().createEntityManager();
        try {
            MovieRepository repo = new MovieRepository(em);
            return repo.findAll();
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    public Movie createMovie(Movie movie) {
        EntityManager em = EntityManagerUtil.getInstance().createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            MovieRepository repo = new MovieRepository(em);
            Movie saved = repo.save(movie);
            tx.commit();
            return saved;
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            logger.error("Failed to save movie", e);
            throw e;
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
}
