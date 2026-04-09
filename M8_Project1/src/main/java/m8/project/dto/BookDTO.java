package m8.project.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class BookDTO {
	
	@NotNull(message = "Book ID is required.")
	private Long id;
	@NotBlank(message = "Book Title is required.")
	private String title;
	@NotBlank(message = "Book Author is required.")
	private String author;
	private Boolean isAvailable;
	
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

	public BookDTO(Long id, String title, String author, Boolean isAvailable) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.isAvailable = isAvailable;
	}
	public BookDTO() {}
}
