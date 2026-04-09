package m8.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import m8.project.entity.Loan;

public interface LoanRepository extends JpaRepository<Loan, Long> {

}
