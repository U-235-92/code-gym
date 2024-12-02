package aq.gym.xml.stax_item_analizer;

import java.io.File;
import java.util.List;

public class MainStax {

	public static void main(String[] args) {
		File source = new File("src/main/java/aq/gym/xml/stax_item_analizer/item_xml.xml");
		StaxAnalizer analizer = new StaxAnalizer(source );
		List<Item> items = analizer.getItems();
		items.forEach(System.out::println);
	}

}
