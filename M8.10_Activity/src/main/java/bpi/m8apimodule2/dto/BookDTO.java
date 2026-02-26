package bpi.m8apimodule2.dto;

import jakarta.validation.constraints.NotBlank;

public class BookDTO {

	@NotBlank(message ="Book Title is required")
	private String title;
	@NotBlank(message = "Book Author is required")
	private String author;
	
	public BookDTO() {}


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

	public BookDTO(String title, String author) {
		
		this.title = title;
		this.author = author;
	}
	
}
