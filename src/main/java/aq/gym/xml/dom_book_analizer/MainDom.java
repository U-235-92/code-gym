package aq.gym.xml.dom_book_analizer;

import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class MainDom {

	public static void main(String[] args) {
		File source = new File("src/main/java/aq/gym/xml/dom_book_analizer/books.xml");
		DomAnalizer domAnalizer = DomAnalizer.getInstance(source);
		printBooks(domAnalizer);
		printAuthors(domAnalizer);
		printBook(domAnalizer, "9785389149885");
		addBook(domAnalizer);
		printBooks(domAnalizer);
		deleteBook(domAnalizer);
		printBooks(domAnalizer);
	}
	
	private static void printBooks(DomAnalizer domAnalizer) {
		List<Book> books = domAnalizer.getBooks();
		books.forEach(System.out::println);
	}
	
	private static void printAuthors(DomAnalizer domAnalizer) {
		Set<Author> authors = domAnalizer.getAuthors();
		authors.forEach(System.out::println);
	}
	
	private static void printBook(DomAnalizer domAnalizer, String isbn) {
		Optional<Book> book = domAnalizer.getBook(isbn);
		book.ifPresentOrElse(System.out::println, () -> System.out.println("Book not found!"));
	}
	
	private static void addBook(DomAnalizer domAnalizer) {
		Book book = new Book();
		book.setIsbn("9785389247970");
		book.setTitle("Сегун");
		book.setAuthors(List.of(new Author("Джеймс", "Клавел")));
		domAnalizer.addBook(book);
	}
	
	private static void deleteBook(DomAnalizer domAnalizer) {
		domAnalizer.deleteBook("9785389247970");
	}
}
