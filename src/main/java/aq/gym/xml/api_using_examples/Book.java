package aq.gym.xml.api_using_examples;

import java.util.ArrayList;
import java.util.List;

public class Book {

	private String title = "";
	private List<String> authors = new ArrayList<>();
	private String id;
	private String type;
	
	public Book() {
		super();
	}

	public Book(String title, List<String> authors, String id, String type) {
		super();
		this.title = title;
		this.authors = authors;
		this.id = id;
		this.type = type;
	}

	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}

	public List<String> getAuthors() {
		return authors;
	}

	public void setAuthors(List<String> authors) {
		this.authors = authors;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return title + ", " + authors.toString() + ", " + type + ", " + " " + id;
	}
}
