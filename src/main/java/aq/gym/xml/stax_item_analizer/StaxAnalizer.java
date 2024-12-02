package aq.gym.xml.stax_item_analizer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class StaxAnalizer {

	private XMLStreamReader xmlStreamReader;
	
	public StaxAnalizer(File source) {
		try {
			XMLInputFactory xmlInputFactory = XMLInputFactory.newDefaultFactory();
			xmlStreamReader = xmlInputFactory.createXMLStreamReader(new FileInputStream(source));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}
	
	public List<Item> getItems() {
		List<Item> items = new ArrayList<Item>();
		try {
			Item item = null;
			String element = null;
			while(xmlStreamReader.hasNext()) {
				int event = xmlStreamReader.next();
				if(event == XMLStreamConstants.START_ELEMENT) {
					element = xmlStreamReader.getLocalName();
					if(element.equals("item")) {						
						item = makeItemAnGetAttributes();
					}
				} else if(event == XMLStreamConstants.CHARACTERS) {
					if (element.equals("name")) {
						setItemName(item);
						element = "";
					} else if (element.equals("characteristic")) {
						setItemCharacteristic(item);
						element = "";
					} else if (element.equals("expiration")) {
						setItemExpirationAndPutThemToList(item, items);
					}
				}
			}
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
		return items;
	}
	
	private Item makeItemAnGetAttributes() {
		Item item = new Item();
		String id = xmlStreamReader.getAttributeValue(0);
		item.setId(id);
		return item;
	}
	
	private void setItemName(Item item) {
		String name = xmlStreamReader.getText();
		item.setName(name);
	}
	
	private void setItemCharacteristic(Item item) {
		String characteristic = xmlStreamReader.getText();
		item.setCharacteristic(characteristic);
	}
	
	private void setItemExpirationAndPutThemToList(Item item, List<Item> items) {
		String expiration = xmlStreamReader.getText().trim();
		if(expiration.length() > 0) {							
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate expDate = LocalDate.parse(expiration, dtf);
			item.setExpiration(expDate);
			items.add(item);
			item = null;
		}
	}
}
