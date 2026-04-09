package m9.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import m9.project.entity.Loan;

public interface LoanRepository extends JpaRepository<Loan, Long> {

}
