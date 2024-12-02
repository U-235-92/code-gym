package aq.gym.xml.sax_item_analizer;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SaxAnalizer {

	private SAXParser parser;
	private List<Item> items = new ArrayList<Item>();
	
	public SaxAnalizer() {
		try {
			SAXParserFactory factory = SAXParserFactory.newDefaultInstance();
			SchemaFactory schemaFactory = SchemaFactory.newDefaultInstance();
			File schemaSource = new File("src/main/java/aq/gym/xml/sax_item_analizer/item_xsd.xsd");
			Schema schema = schemaFactory.newSchema(schemaSource);
			factory.setSchema(schema);
			parser = factory.newSAXParser();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
	}
	
	public List<Item> getItems(File source) {
		if(parser != null) {
			try {
				parser.parse(source, new ItemHandler());
			} catch (SAXException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return items;
	}
	
	private class ItemHandler extends DefaultHandler {
		
		private Item item;
		private String currentElementName;
		
		@Override
		public void startElement(String uri, String localName, String qName, Attributes attributes)
				throws SAXException {
			if(qName.equals("item")) {				
				item = new Item();
				String id = attributes.getValue(0);
				item.setId(id);
			} else {
				currentElementName = qName;
			}
		}
		
		@Override
		public void endElement(String uri, String localName, String qName) throws SAXException {
			if(qName.equals("item")) {
				items.add(item);
				item = null;
			}
		}
		
		@Override
		public void characters(char[] ch, int start, int length) throws SAXException {
			if (currentElementName.equals("name")) {
				String name = new String(ch, start, length);
				item.setName(name);
			} else if (currentElementName.equals("characteristic")) {
				String characteristic = new String(ch, start, length);
				item.setCharacteristic(characteristic);
			} else if (currentElementName.equals("expiration")) {
				String expiration = new String(ch, start, length);
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				LocalDate expDate = LocalDate.parse(expiration, dtf);
				item.setExpiration(expDate);
			}
		}
	}
}
