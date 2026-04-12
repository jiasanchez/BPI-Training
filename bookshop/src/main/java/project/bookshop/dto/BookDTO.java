package project.bookshop.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class BookDTO {
	
	private Long id;
	@NotBlank(message = "Book Title is required.")
	private String title;
	@NotBlank(message = "Book Author is required.")
	private String author;
	@NotNull(message = "Book Price is required")
	private Double price;
	@NotNull(message = "Date is required")
	private LocalDate createdAt;
	
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
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public LocalDate getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}
	public BookDTO(Long id, @NotBlank(message = "Book Title is required.") String title,
			@NotBlank(message = "Book Author is required.") String author,
			@NotNull(message = "Book Price is required") Double price,
			@NotNull(message = "Date is required") LocalDate createdAt) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.price = price;
		this.createdAt = createdAt;
	}
	
	public BookDTO() {}
}
