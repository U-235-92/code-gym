package aq.gym.thread.library;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Library {

	private Map<String, Book> books = new HashMap<>( 
			Map.ofEntries(
				Map.entry("A", new Book("A")),
				Map.entry("B", new Book("B")),
				Map.entry("C", new Book("C")),
				Map.entry("D", new Book("D")),
				Map.entry("E", new Book("E"))
			) 
		);

	public synchronized Optional<Book> getBookByName(String name) {
		return Optional.ofNullable(books.remove(name));
	}
}
