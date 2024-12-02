package aq.gym.xml.sax_item_analizer;

import java.io.File;
import java.util.List;

public class MainSAX {

	public static void main(String[] args) {
		File source = new File("src/main/java/aq/gym/xml/sax_item_analizer/item_xml.xml");
		SaxAnalizer analizer = new SaxAnalizer();
		List<Item> items = analizer.getItems(source);
		items.forEach(System.out::println);
	}
}
