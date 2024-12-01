package aq.gym.xml.dom_book_analizer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import javax.xml.xpath.XPathNodes;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED, staticName = "getInstance")
public class DomAnalizer {

	static final String JAXP_SCHEMA_LANGUAGE = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
	static final String W3C_XML_SCHEMA = "http://www.w3.org/2001/XMLSchema";

	private static DocumentBuilder documentBuilder;
	
	@NonNull
	private File source;
	
	static {
		initDocumentBuilder();
	}
	
	private static void initDocumentBuilder() {
		try {
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newDefaultInstance();
			documentBuilderFactory.setNamespaceAware(true);
			documentBuilderFactory.setValidating(true);
			documentBuilderFactory.setAttribute(JAXP_SCHEMA_LANGUAGE, W3C_XML_SCHEMA);
			documentBuilder = documentBuilderFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
	}
	
	public List<Book> getBooks() {
		List<Book> books = new ArrayList<Book>();
		try {
			Document document = parseDocument(source);
			XPathFactory xPathFactory = XPathFactory.newDefaultInstance();
			XPath xPath = xPathFactory.newXPath();
			String booksXPath = "//book";
			XPathNodes nodes = xPath.evaluateExpression(booksXPath, document, XPathNodes.class);
			nodes.forEach(bookNode -> {
				if(bookNode.getNodeType() == Node.ELEMENT_NODE) {
					String title = ((Element) bookNode).getElementsByTagName("title").item(0).getTextContent();					
					String isbn = ((Element) bookNode).getAttributes().getNamedItem("isbn").getNodeValue();
					NodeList authorNodes = ((Element) bookNode).getElementsByTagName("author");
					List<Author> authors = new ArrayList<Author>();
					for(int i = 0; i < authorNodes.getLength(); i++) {
						String firstname = ((Element) bookNode).getElementsByTagName("firstname").item(i).getTextContent();
						String lastname = ((Element) bookNode).getElementsByTagName("lastname").item(i).getTextContent();
						Author author = new Author(firstname, lastname);
						authors.add(author);
					}
					Book book = new Book(isbn, title, authors);
					books.add(book);
				}
			});
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
		return books;
	}
	
	public Set<Author> getAuthors() {
		Set<Author> authors = new HashSet<Author>();
		Document document = parseDocument(source);
		XPathFactory xPathFactory = XPathFactory.newDefaultInstance();
		XPath xPath = xPathFactory.newXPath();
		String authorsXPath = "//author";
		try {
			XPathNodes nodes = xPath.evaluateExpression(authorsXPath, document, XPathNodes.class);
			nodes.forEach(authorNode -> {
				if(authorNode.getNodeType() == Node.ELEMENT_NODE) {
					String firstname = ((Element) authorNode).getElementsByTagName("firstname").item(0).getTextContent();
					String lastname = ((Element) authorNode).getElementsByTagName("lastname").item(0).getTextContent();
					Author author = new Author(firstname, lastname);
					authors.add(author);
				}
			});
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
		return authors;
	}
	
	public Optional<Book> getBook(String isbn) {
		Document document = parseDocument(source);
		XPathFactory xPathFactory = XPathFactory.newDefaultInstance();
		XPath xPath = xPathFactory.newXPath();
		String pathToBookISBN = "//book[@isbn=" + "'" + isbn + "'" + "]";
		Book book = null;
		try {
			 Node bookNode = xPath.evaluateExpression(pathToBookISBN, document, Node.class);
			 if(bookNode != null) {				 
				 if(bookNode.getNodeType() == Node.ELEMENT_NODE) {
					 String title = ((Element) bookNode).getElementsByTagName("title").item(0).getTextContent();					
					 NodeList authorNodes = ((Element) bookNode).getElementsByTagName("author");
					 List<Author> authors = new ArrayList<Author>();
					 for(int i = 0; i < authorNodes.getLength(); i++) {
						 String firstname = ((Element) bookNode).getElementsByTagName("firstname").item(i).getTextContent();
						 String lastname = ((Element) bookNode).getElementsByTagName("lastname").item(i).getTextContent();
						 Author author = new Author(firstname, lastname);
						 authors.add(author);
					 }
					 book = new Book(isbn, title, authors);
				 }
			 }
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}		
		return Optional.ofNullable(book);
	}
	
	public void addBook(Book book) {
		Document document = parseDocument(source);
		Element bookElement = document.createElement("book");
		bookElement.setAttribute("isbn", book.getIsbn());
		Element titleElement = document.createElement("title");
		titleElement.setTextContent(book.getTitle());
		bookElement.appendChild(titleElement);
		for(Author author : book.getAuthors()) {
			Element authorElement = document.createElement("author");
			Element firstNameElement = document.createElement("firstname");
			firstNameElement.setTextContent(author.getFirstname());
			Element lastNameElement = document.createElement("lastname");
			lastNameElement.setTextContent(author.getLastname());
			authorElement.appendChild(firstNameElement);
			authorElement.appendChild(lastNameElement);
			bookElement.appendChild(authorElement);
		}
		Node booksNode = document.getDocumentElement();
		booksNode.appendChild(bookElement);
		saveChanges(document);
	}
	
	public void deleteBook(String isbn) {
		try {			
			Document document = parseDocument(source);
			XPathFactory xPathFactory = XPathFactory.newDefaultInstance();
			XPath xPath = xPathFactory.newXPath();
			String pathToBookISBN = "//book[@isbn=" + "'" + isbn + "'" + "]";
			Node bookNode = xPath.evaluateExpression(pathToBookISBN, document, Node.class);
			if(bookNode != null) 
				document.getDocumentElement().removeChild(bookNode);
			saveChanges(document);
		} catch(XPathExpressionException e) {
			e.printStackTrace();
		}
	}
	
	private Document parseDocument(File source) {
		Document document = null;
		if(source.exists()) {			
			try {
				document = documentBuilder.parse(source);
			} catch (SAXException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} 
		return document;
	}
	
	private void saveChanges(Document document) {
		try {
			DOMSource domSource = new DOMSource(document);
			StreamResult streamResult = new StreamResult(new FileOutputStream(source));
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer;
			transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty(OutputKeys.METHOD, "xml");
			transformer.transform(domSource, streamResult);
		} catch (TransformerException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
