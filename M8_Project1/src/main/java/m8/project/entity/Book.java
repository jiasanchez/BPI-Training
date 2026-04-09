package m8.project.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "books")
public class Book {

	@Id
	@Column(name ="book_id")
	private Long id;
	@Column(name = "title", columnDefinition = "VARCHAR(300)", length = 300)
	private String title;
	@Column(name = "author", columnDefinition = "VARCHAR(300)", length = 300)
	private String author;	
	@Column(name = "is_available")
	private Boolean isAvailable = true;
	
	@OneToOne(mappedBy ="book", cascade = CascadeType.REMOVE, orphanRemoval = true)
	private Loan loan;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Boolean getIsAvailable() {
		return isAvailable;
	}

	public void setIsAvailable(Boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public Loan getLoan() {
		return loan;
	}

	public void setLoan(Loan loan) {
		this.loan = loan;
	}

	public Book(Long id, String title, String author, Boolean isAvailable, Loan loan) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.isAvailable = isAvailable;
		this.loan = loan;
	}
	
	public Book() {}
	
	}
