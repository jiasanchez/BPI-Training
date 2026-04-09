package m9.project.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import m9.project.dto.BookDTO;
import m9.project.dto.LoanDTO;
import m9.project.entity.Book;
import m9.project.entity.Loan;
import m9.project.entity.User;
import m9.project.repository.BookRepository;
import m9.project.repository.LoanRepository;
import m9.project.repository.UserRepository;

@Service
public class LoanService {
	private static final Logger logger = LoggerFactory.getLogger(LoanService.class);
	private final BookRepository bookRepo;
	private final UserRepository userRepo;
	private final LoanRepository loanRepo;
	
	public LoanService(BookRepository bookRepo,
						  UserRepository userRepo,
						  LoanRepository loanRepo) {
		this.bookRepo = bookRepo;
		this.loanRepo = loanRepo;
		this.userRepo = userRepo;
	}
	
		//GET ALL LOANS
		public List<LoanDTO> getAllLoans(){
			return loanRepo.findAll()
					.stream()
					.map(this::convertToDTO)
					.toList();
		}
	
		//BORROW BOOK
		@Transactional
		public LoanDTO borowBook(LoanDTO loanDTO) {
			
			Book book = bookRepo.findById(loanDTO.getBookId())
					.orElseThrow(() -> new RuntimeException("Book not found."));
			if(!book.getIsAvailable()) {
					throw new RuntimeException("Book is already borrowed.");				
			}
			
			User user = userRepo.findById(loanDTO.getUserId())
					.orElseThrow(() -> new RuntimeException("User not found")); 
			Loan loan = new Loan();
			loan.setBook(book);
			loan.setUser(user);
			loan.setIsActive(true);
			
			book.setIsAvailable(false);
			bookRepo.save(book);
			
			loanRepo.save(loan);
			logger.info(user.getName() + " succesfully borrowed Book ID {}" + book.getId());
			return convertToDTO(loan);
			
		}
		
		//RETURN BOOK
		@Transactional
		public LoanDTO returnBook(Long loanId) {
			Loan loan = loanRepo.findById(loanId)
					.orElseThrow(() -> new RuntimeException("Loan not found."));
			if(!loan.getIsActive()) {
				logger.info("Book ID {} successfully returned.", loan.getBook());
				throw new RuntimeException("Book already returned.");
			}
			
			loan.setIsActive(false);
			Book book = loan.getBook();
			if (book != null) {
				book.setIsAvailable(true);
				bookRepo.save(book);
			}
			logger.info(loan.getUser() + " succesfully returend book.");
			return convertToDTO(loan);
		}
		
		//ENTITY TO DTO
		private LoanDTO convertToDTO(Loan loan) {
			LoanDTO loanDTO = new LoanDTO();
			loanDTO.setLoanId(loan.getLoanId());			
			loanDTO.setBookId(loan.getBook().getId());
			loanDTO.setBookTitle(loan.getBook().getTitle());
			loanDTO.setUserId(loan.getUser().getUserID());
			loanDTO.setName(loan.getUser().getName());
			return loanDTO;
		}

}
