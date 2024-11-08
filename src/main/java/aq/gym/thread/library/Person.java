package aq.gym.thread.library;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class Person {

	@NonNull
	private String name;
	private List<Book> books = new ArrayList<>();
	
	public void takeBook(String name, Library library) {
		try {
			TimeUnit.MILLISECONDS.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Person " + this.name + " try to take a book " + name);
		Optional<Book> optBook = library.getBookByName(name);
		Consumer<Book> ifBookPresent = book -> {
			books.add(book);
			System.out.println(this.name + " took a book " + book.getName());
		};
		Runnable ifBookAbsent = () -> System.out.println(this.name +  " couldn't take a book " + name);
		optBook.ifPresentOrElse(ifBookPresent, ifBookAbsent);
		try {
			TimeUnit.MILLISECONDS.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
