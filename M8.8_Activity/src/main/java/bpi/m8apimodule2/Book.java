package bpi.m8apimodule2;

public class Book {
	private Long id;
	private String title;
	private String author;
	
	public Book() {}
	
	public Book(Long id, String title, String author) {
		this.id = id;
		this.title = title;
		this.author = author;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	public Book(String title, String author) {
		super();
		this.title = title;
		this.author = author;
	}
	
	
}
