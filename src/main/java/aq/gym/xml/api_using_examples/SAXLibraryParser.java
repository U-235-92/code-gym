package aq.gym.xml.api_using_examples;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SAXLibraryParser {

	private File sourse;
	private SimpleHandler simpleHandler;
	private List<Book> bookList = new ArrayList<>();
	
	public SAXLibraryParser(File source) {
		if(source == null) {
			throw new NullPointerException("The source of a XML document is null");
		}
		this.sourse = source;
		simpleHandler = new SimpleHandler();
	}
	
	public void parseBooks() {
		try {
			SAXParser parser = initialize();
			parseBooks0(parser);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private SAXParser initialize() throws ParserConfigurationException, SAXException {
		SAXParserFactory saxFactory = SAXParserFactory.newInstance();
		saxFactory.setNamespaceAware(true);
		saxFactory.setValidating(true);
		SAXParser saxParser = saxFactory.newSAXParser();
		return saxParser;
	}

	private void parseBooks0(SAXParser parser) throws SAXException, IOException {
		parser.parse(sourse, simpleHandler);
		bookList.stream().forEach(book -> System.out.println(book));
	}
	
	private class SimpleHandler extends DefaultHandler {
		
		private String currentElement;
		private String endElement;
		private String bookTitle;
		private List<String> authorList = new ArrayList<>();
		
		public SimpleHandler() {
			super();
		}
		
		@Override
		public void startElement(String uri, String localName, String qName, Attributes attributes)
				throws SAXException {
			currentElement = localName;
		}
		
		@Override
		public void endElement(String uri, String localName, String qName) throws SAXException {
			currentElement = "";
			endElement = localName;
			if(endElement.equals("book")) {
				 Book book = new Book();
				 book.setTitle(bookTitle);
				 book.setAuthors(new ArrayList<>(authorList));
				 bookList.add(book);
				 authorList.clear();
			 }
		}
		
		 @Override
		public void characters(char[] ch, int start, int length) throws SAXException {
			 if(currentElement.equals("title")) {
				 bookTitle = new String(ch, start, length).trim();
			 } else if(currentElement.equals("author")) {
				 String author = new String(ch, start, length).trim();
				 authorList.add(author);
			 }
		}
	}
}
