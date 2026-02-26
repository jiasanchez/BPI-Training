package bpi.m8apimodule2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import bpi.m8apimodule2.model.*;

public interface BookRepository extends JpaRepository<Book, Long>{
}
