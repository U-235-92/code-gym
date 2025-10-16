package aq.gym.xml.api_using_examples;

import java.io.File;
import java.util.List;

import javax.xml.xpath.XPathException;

import org.w3c.dom.Element;

public abstract class LibraryDocumentHandler {

	protected static final String JAXP_SCHEMA_LANGUAGE ="http://java.sun.com/xml/jaxp/properties/schemaLanguage";
	protected static final String W3C_XML_SCHEMA = "http://www.w3.org/2001/XMLSchema";
	
	protected abstract void printBooksOfLibrary(File source);
	protected abstract void addBook(File source, File destination, Book book);
	protected abstract void editBook(File source, File destination, String id, String typeEdit, String titleEdit, List<String> authorsEdit);
	protected abstract void removeBook(File source, File destination, String id);
	protected abstract void makeLibrary(File destination, List<Book> books);
	protected abstract Element searchBookElement(File source, String id) throws XPathException;
	protected abstract List<Book> searchBookByAuthor(File source, String author);
	protected abstract List<Book> searchBookByTitle(File source, String title);
	protected abstract void transformDoument(File documentXML, File documentXSLT, File destination);
	protected abstract boolean isValidate(File document, File schema);
	
	protected void printBooks(List<Book> books) {
		books.stream().forEach(book -> System.out.println(book));
	}
}
