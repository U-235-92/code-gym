package aq.gym.xml.api_using_examples;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathEvaluationResult;
import javax.xml.xpath.XPathEvaluationResult.XPathResultType;
import javax.xml.xpath.XPathException;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import javax.xml.xpath.XPathNodes;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class LibraryDocumentHandlerDOM extends LibraryDocumentHandler {
	
	private boolean xsdValidate = false;
	private boolean nameSpaceAware = false;
	
	private DocumentBuilderFactory documentBuilderFactory;
	private DocumentBuilder documentBuilder;
	private Document document;
	
	private XPathFactory xPathFactory;
	private XPath xPath;
	
	public LibraryDocumentHandlerDOM(boolean xsdValidate, boolean nameSpaceAware) {
		this.xsdValidate = xsdValidate;
		this.nameSpaceAware = nameSpaceAware;
		try {
			initializeDOM();
			initializeXPath();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void initializeDOM() throws ParserConfigurationException, SAXException, IOException {
		documentBuilderFactory = DocumentBuilderFactory.newInstance();
		documentBuilderFactory.setNamespaceAware(nameSpaceAware);
		documentBuilderFactory.setValidating(true);
		if(xsdValidate) {			
			documentBuilderFactory.setAttribute(JAXP_SCHEMA_LANGUAGE, W3C_XML_SCHEMA);
		}
		documentBuilder = documentBuilderFactory.newDocumentBuilder();
	}
	
	private void initializeXPath() {
		xPathFactory = XPathFactory.newInstance();
		xPath = xPathFactory.newXPath();
	}

	@Override
	protected void printBooksOfLibrary(File source) {
		try {
			document = documentBuilder.parse(source);
			printBooksOfLibrary0(document);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void printBooksOfLibrary0(Document domDocument) throws ParserConfigurationException, SAXException, IOException {
		List<Book> bookList = new ArrayList<>();
		NodeList bookNodes = domDocument.getDocumentElement().getElementsByTagName("book");
		for (int i = 0; i < bookNodes.getLength(); i++) {
			Node bookNode = bookNodes.item(i);
			if(bookNode.getNodeType() == Node.ELEMENT_NODE) {
				String title = ((Element) bookNode).getElementsByTagName("title").item(0).getTextContent();
				String type = ((Element) bookNode).getAttribute("type");
				String id = ((Element) bookNode).getAttribute("id");
				NodeList authorNodes = ((Element) bookNode).getElementsByTagName("author");
				List<String> authorList = new ArrayList<>();
				for (int j = 0; j < authorNodes.getLength(); j++) {
					Node authorNode = authorNodes.item(j);
					String author = authorNode.getTextContent();
					authorList.add(author);
				}
				Book book = new Book();
				book.setTitle(title);
				book.setAuthors(authorList);
				book.setType(type);
				book.setId(id);
				bookList.add(book);
			}
		}
		printBooks(bookList);
	}
	
	@Override
	protected void addBook(File source, File destination, Book book) {
		if(book == null) {
			throw new NullPointerException("The book is null");
		}
		try {
			addBook0(source, book);
			saveLibrary(document, destination, "xml");
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void addBook0(File source, Book book) throws SAXException, IOException {
		document = documentBuilder.parse(source);
		Element bookElement = document.createElement("book");
		bookElement.setAttribute("id", book.getId());
		bookElement.setAttribute("type", book.getType());
		Element bookTitle = document.createElement("title");
		bookTitle.setTextContent(book.getTitle());
		bookElement.appendChild(bookTitle);
		for(String author : book.getAuthors()) {
			Element bookAuthor = document.createElement("author");
			bookAuthor.setTextContent(author);
			bookElement.appendChild(bookAuthor);
		}
		Element libraryElement = (Element) document.getElementsByTagName("library").item(0);
		libraryElement.appendChild(bookElement);
	}

	@Override
	protected void editBook(File source, File destination, String id, String typeEdit, String titleEdit, List<String> authorsEdit) {
		try {
			editBook0(source, id, typeEdit, titleEdit, authorsEdit);
			saveLibrary(document, destination, "xml");
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (XPathException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void editBook0(File source, String id, String typeEdit, String titleEdit, List<String> authorsEdit) throws SAXException, IOException, XPathException {
		if(id == null) {
			throw new NullPointerException("book id is null");
		}
		document = documentBuilder.parse(source);
		Element bookElement = searchBookElement(source, id);
		if(bookElement != null) {
			String oldTypeAttribute = bookElement.getAttribute("type");
			bookElement.setAttribute("type", typeEdit == null ? oldTypeAttribute : typeEdit);
			Element titleElement = (Element) bookElement.getElementsByTagName("title").item(0);
			String oldTitleTextContent = titleElement.getTextContent();
			titleElement.setTextContent(titleEdit == null ? oldTitleTextContent : titleEdit);
			NodeList authorNodes = bookElement.getElementsByTagName("author");
			if(authorsEdit != null) {
				if(authorNodes.getLength() == authorsEdit.size()) {
					for (int i = 0; i < authorNodes.getLength(); i++) {
						authorNodes.item(i).setTextContent(authorsEdit.get(i));
					}
				} else if(authorNodes.getLength() > authorsEdit.size()) {
					for (int i = 0; i < authorNodes.getLength(); i++) {
						if(i < authorsEdit.size()) {
							authorNodes.item(i).setTextContent(authorsEdit.get(i));
						} else {
							bookElement.removeChild(authorNodes.item(i));
						}
					}
				} else {
					for (int i = 0; i < authorsEdit.size(); i++) {
						if(i < authorNodes.getLength()) {
							authorNodes.item(i).setTextContent(authorsEdit.get(i));
						} else {
							Element authorElement = document.createElement("author");
							authorElement.setTextContent(authorsEdit.get(i));
							bookElement.appendChild(authorElement);
						}
					}
				}
			} 
		} else {
			System.out.println("The book id=" + id + " is not exist");
		}
	}

	@Override
	protected void removeBook(File source, File destination, String id) {
		try {
			removeBook0(source, destination, id);
			saveLibrary(document, destination, "xml");
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (XPathException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}
	}
	
	private void removeBook0(File source, File destination, String id) throws SAXException, IOException, XPathException {
		document = documentBuilder.parse(source);
		Element bookElement = searchBookElement(source, id);
		if(bookElement != null) {
			Element libraryElement = (Element) document.getElementsByTagName("library").item(0);
			libraryElement.removeChild(bookElement);
		} else {
			System.out.println("The book id=" + id + " is not exist");
		}
	}
	
	@Override
	protected void makeLibrary(File destination, List<Book> books) {
		fillLibrary(books);
		try {
			Document document = makeLibrary0(destination, books);
			saveLibrary(document, destination, "xml");
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}
	}
	
	private void fillLibrary(List<Book> books) {
		List<String> bookShildtAuthor = new ArrayList<>();
		bookShildtAuthor.add("Герберт Шилдт");
		Book shildtBook = new Book("Java. Полное руководство", bookShildtAuthor, "SN-1", "Информатика");
		List<String> bookPushkinAuthor = new ArrayList<>();
		bookPushkinAuthor.add("Александ Пушкин");
		Book bookPushkin = new Book("Сборник стихов Пушкина", bookPushkinAuthor, "SN-2", "Проза");
		List<String> bookBulichowAuthor = new ArrayList<>();
		bookBulichowAuthor.add("Александ Пушкин");
		Book bookBulichowElement = new Book("Приключения Алисы", bookBulichowAuthor, "SN-3", "Фантастика");
		List<String> bookImagineAuthor = new ArrayList<>();
		bookImagineAuthor.add("Иван Иванов");
		bookImagineAuthor.add("Петр Петров");
		Book bookImagineElement = new Book("Все обо всем", bookImagineAuthor, "SN-4", "Фантастика");
		books.add(shildtBook);
		books.add(bookPushkin);
		books.add(bookBulichowElement);
		books.add(bookImagineElement);
	}
	
	private Document makeLibrary0(File destination, List<Book> books) throws SAXException, ParserConfigurationException {
		SchemaFactory schemaFactory = SchemaFactory.newDefaultInstance();
		File schemaSource = Paths.get("src/main/java/aq/gym/xml/api_using_examples/LibraryXSD.xsd").toFile();
		Schema schema = schemaFactory.newSchema(schemaSource);
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		documentBuilderFactory.setNamespaceAware(true);
		documentBuilderFactory.setValidating(true);
		documentBuilderFactory.setSchema(schema);
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		Document document = documentBuilder.newDocument();
		Element libraryElement = makeLibraryElement(document);
		for(Book book : books) {
			Element bookElement = makeBookElement(document, book);
			libraryElement.appendChild(bookElement);
		}
		document.appendChild(libraryElement);
		return document;
	}
	
	private Element makeLibraryElement(Document document) {
		Element rootElement = document.createElement("library");
		rootElement.setAttribute("lib_name", "New library");
		rootElement.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
		rootElement.setAttribute("xsi:noNamespaceSchemaLocation", "LibraryXSD.xsd");
		return rootElement;
	}
	
	private Element makeBookElement(Document document, Book book) {
		Element bookElement = document.createElement("book");
		bookElement.setAttribute("id", book.getId());
		bookElement.setAttribute("type", book.getType());
		Element titleElement = document.createElement("title");
		titleElement.setTextContent(book.getTitle());
		bookElement.appendChild(titleElement);
		for(String author : book.getAuthors()) {
			Element authorElement = document.createElement("author");
			authorElement.setTextContent(author);
			bookElement.appendChild(authorElement);
		}
		return bookElement;
	}

	private void saveLibrary(Document document, File destination, String method) throws FileNotFoundException, TransformerException {
		DOMSource domSource = new DOMSource(document);
		StreamResult streamResult = new StreamResult(new FileOutputStream(destination));
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty(OutputKeys.METHOD, method);
		transformer.transform(domSource, streamResult);
	}
	
	@Override
	protected Element searchBookElement(File source, String id) throws XPathException {
		XPathEvaluationResult<?> xPathResult = xPath.evaluateExpression("//book[@id=" + "'" + id + "'" + "]", document);
		Element bookElement = null;
		if(xPathResult.type() == XPathResultType.NODESET) {
			XPathNodes xPathNodes = (XPathNodes) xPathResult.value();
			Node testNode = xPathNodes.get(0);
			if((testNode).getNodeType() == Node.ELEMENT_NODE) {
				bookElement = (Element) testNode;
			}
		}
		return bookElement;
	}

	@Override
	protected List<Book> searchBookByAuthor(File source, String author) {
		XPathNodes xPathNodes = null;
		try {
			xPathNodes = searchBookNodes(source, "author", author);
			return composeAllBooks(xPathNodes);
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
		return new ArrayList<>();
	}

	@Override
	protected List<Book> searchBookByTitle(File source, String title) {
		XPathNodes xPathNodes = null;
		try {
			xPathNodes = searchBookNodes(source, "title", title);
			return composeAllBooks(xPathNodes);
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
		return new ArrayList<>();
	}
	
	private XPathNodes searchBookNodes(File source, String nameElement, String valueElement) throws XPathExpressionException, SAXException, IOException {
		document = documentBuilder.parse(source);
		String expression = "//book[child::" + nameElement + "[text()=" + "'" + valueElement + "'" + "]]";
		XPathEvaluationResult<?> xPathEvaluationResult = xPath.evaluateExpression(expression , document); 
		XPathNodes xPathNodes = xPathEvaluationResult.type() == XPathResultType.NODESET ? (XPathNodes) xPathEvaluationResult.value() : null;
		if(xPathNodes == null) {
			throw new NullPointerException("The result of xPath is null");
		}
		return xPathNodes;
	}
	
	private List<Book> composeAllBooks(XPathNodes xPathNodes) {
		List<Book> bookList = new ArrayList<>();
		if(xPathNodes != null) {
			for(Node bookNode : xPathNodes) {
				if(bookNode.getNodeType() == Node.ELEMENT_NODE) {
					Element bookElement = (Element) bookNode;
					String id = bookElement.getAttribute("id");
					String type = bookElement.getAttribute("type");
					String title = bookElement.getElementsByTagName("title").item(0).getTextContent();
					List<String> authorList = new ArrayList<>();
					NodeList authorNodes = bookElement.getElementsByTagName("author");
					for (int i = 0; i < authorNodes.getLength(); i++) {
						Node authorNode = authorNodes.item(i);
						String authorValue = authorNode.getTextContent();
						authorList.add(authorValue);
					}
					Book book = new Book();
					book.setAuthors(authorList);
					book.setId(id);
					book.setTitle(title);
					book.setType(type);
					bookList.add(book);
				}
			}			
		}
		return bookList;
	}

	@Override
	protected void transformDoument(File documentXML, File documentXSLT, File destination) {
		try {
			transformDocument0(documentXML, documentXSLT, destination);
			System.out.println("Transformation succeed");
		} catch (TransformerException e) {
			e.printStackTrace();
		}
	}

	private void transformDocument0(File documentXML, File documentXSLT, File destination) throws TransformerException {
		StreamSource documentXMLSource = new StreamSource(documentXML);
		StreamSource documentXSLTSource = new StreamSource(documentXSLT);
		StreamResult documentHTMLResult = new StreamResult(destination);
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer(documentXSLTSource);
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty(OutputKeys.METHOD, "html");
		transformer.transform(documentXMLSource, documentHTMLResult);
	}

	@Override
	protected boolean isValidate(File documentFile, File schemaFile) {
		return isValidate0(documentFile, schemaFile);
	}
	
	private boolean isValidate0(File documentFile, File schemaFile) {
		Source documentSource = new StreamSource(documentFile);
		SchemaFactory schemaFactory = SchemaFactory.newDefaultInstance();
		try {
			Schema schema = schemaFactory.newSchema(schemaFile);
			Validator validator = schema.newValidator();
			validator.validate(documentSource);
			System.out.println("Document is correct");
			return true;
		} catch (SAXException | IOException e) {	
			System.err.println("Document isn't correct: " + e.getMessage());
			return false;
		} 
	}
}
