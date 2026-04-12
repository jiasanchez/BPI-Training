package project.bookshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import project.bookshop.entity.Book;

public interface BookRepository extends JpaRepository <Book, Long>{

}
