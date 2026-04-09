package m8.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import m8.project.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long>{
	
	List<Book> findByIsAvailableTrue();
	List<Book> findByIsAvailableFalse();
}
