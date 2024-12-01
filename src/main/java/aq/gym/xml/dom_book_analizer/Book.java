package aq.gym.xml.dom_book_analizer;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {

	private String isbn;
	private String title;
	private List<Author> authors;
}
