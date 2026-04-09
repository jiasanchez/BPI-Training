package m8.project.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"loanId","userId","name","bookId","bookTitle"})
public class LoanDTO {
	
	private Long loanId; //For JSON RESPONSE ONLY	
	private Long userId;
	private String name; //For JSON RESPONSE ONLY
	private Long bookId;
	private String bookTitle; //For JSON RESPONSE ONLY
	
	public LoanDTO(Long bookId, Long userId) {
		this.bookId = bookId;
		this.userId = userId;
	}

	public Long getBookId() {
		return bookId;
	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getLoanId() {
		return loanId;
	}

	public void setLoanId(Long loanId) {
		this.loanId = loanId;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LoanDTO( ) {}
	
}
