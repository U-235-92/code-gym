package aq.gym.xml.api_using_examples;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.xpath.XPathException;

import org.w3c.dom.Element;

public class LibraryDocumentHandlerSTAX extends LibraryDocumentHandler {

	private String currentElementName;
	private String endElementName;
	private Book book;
	private List<Book> bookList = new ArrayList<>();
	private List<String> authorList = new ArrayList<>();
	private ByteArrayOutputStream baos;
	
	public LibraryDocumentHandlerSTAX() {
		super();
	}
	
	private XMLEventReader getEventReader(File sourceDocumentXML) throws XMLStreamException, FileNotFoundException {
		FileInputStream fis = new FileInputStream(sourceDocumentXML);
		BufferedInputStream bis = new BufferedInputStream(fis);
		XMLInputFactory inputFactory = XMLInputFactory.newInstance();
		XMLEventReader eventReader = inputFactory.createXMLEventReader(bis);
		return eventReader;
	}
	
	private XMLEventWriter getEventWriter() throws XMLStreamException, FileNotFoundException {
		baos = new ByteArrayOutputStream();
		XMLOutputFactory inputFactory = XMLOutputFactory.newInstance();
		XMLEventWriter eventWriter = inputFactory.createXMLEventWriter(baos);
		return eventWriter;
	}
	
	private XMLStreamWriter getStreamWriter() throws XMLStreamException, FileNotFoundException {
		baos = new ByteArrayOutputStream();
		XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
		XMLStreamWriter streamWriter = outputFactory.createXMLStreamWriter(baos);
		return streamWriter;
	}
	
	private void formatWrite(String xml, File destination) throws TransformerException {
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty(OutputKeys.STANDALONE, "yes");
		StreamSource streamSource = new StreamSource(new StringReader(xml));
		StreamResult streamResult = new StreamResult(destination);
		transformer.transform(streamSource, streamResult);
	}
	
	@Override
	protected void printBooksOfLibrary(File source) {
		try {
			printBooksLibrary0(getEventReader(source));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}

	private void printBooksLibrary0(XMLEventReader reader) throws XMLStreamException {
		while(reader.hasNext()) {
			XMLEvent event = reader.nextEvent();
			if(event.isStartElement()) {
				handlePrintBookStartElementEvent(event);
			} else if(event.isEndElement()) {
				handlePrintBookEndElementEvent(event);
			} else if(event.isCharacters()) {
				handlePrintBookCharactersElementEvent(event);
			} 
		}
		printBooks(bookList);
	}

	private void handlePrintBookStartElementEvent(XMLEvent event) {
		StartElement element = event.asStartElement();
		currentElementName = element.getName().getLocalPart();
		if(currentElementName.equals("book")) {
			book = new Book();
		}
		handlePrintBookAttributeEvent(element);
	}
	
	private void handlePrintBookAttributeEvent(StartElement element) {
		if(element.getName().getLocalPart().equals("book")) {			
			Attribute attributeID = element.getAttributeByName(new QName("id"));
			String id = attributeID.getValue();
			Attribute attributeType = element.getAttributeByName(new QName("type"));
			String type = attributeType.getValue();
			book.setId(id);
			book.setType(type);
		}
	}

	private void handlePrintBookEndElementEvent(XMLEvent event) {
		currentElementName = "";
		EndElement element = event.asEndElement();
		endElementName = element.getName().getLocalPart();
		if(endElementName.equals("book")) {
			book.setAuthors(new ArrayList<>(authorList));
			bookList.add(book);
			authorList.clear();
		}
	}

	private void handlePrintBookCharactersElementEvent(XMLEvent event) {
		Characters characters = event.asCharacters();
		if(currentElementName.equals("title")) {
			String titleBook = characters.getData();
			book.setTitle(titleBook);
		} else if(currentElementName.equals("author")) {
			String author = characters.getData();
			authorList.add(author);
		}
	}
	
	@Override
	protected void addBook(File source, File destination, Book book) {
		try {
			if(!isBookExist(source, book)) {
				addBook0(source, destination, book);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private boolean isBookExist(File source, Book book) throws XMLStreamException, FileNotFoundException {
		if(!source.exists()) {
			throw new FileNotFoundException();
		}
		XMLEventReader eventReader = getEventReader(source);
		while(eventReader.hasNext()) {
			XMLEvent event = eventReader.nextEvent();
			if(event.isStartElement()) {
				StartElement element = event.asStartElement();
				if(element.getName().equals(new QName("book"))) {					
					Attribute idAttribute = element.getAttributeByName(new QName("id"));
					String idBook = idAttribute.getValue();
					if(idBook.equals(book.getId())) {
						eventReader.close();
						return true;
					}
				}
			}
		}
		eventReader.close();
		return false;
	}
	
	private void addBook0(File source, File destination, Book book) throws IOException, XMLStreamException, TransformerException {
		XMLEventReader eventReader = getEventReader(source);
		XMLStreamWriter streamWriter = getStreamWriter();
		while(eventReader.hasNext()) {
			XMLEvent event = eventReader.nextEvent();
			if(event.isStartElement()) {
				handleWriteStartElement(event, streamWriter, book);
			} else if(event.isCharacters()) {
				handleWriteCharacters(event, streamWriter);
			} else if(event.isEndElement()) {
				handleWriteEndElement(streamWriter);
			}
		}
		eventReader.close();
		streamWriter.close();
		String xml = new String(baos.toByteArray());
		baos.close();
		formatWrite(xml, destination);
	}

	private void handleWriteStartElement(XMLEvent event, XMLStreamWriter streamWriter, Book book) throws XMLStreamException {
		StartElement element = event.asStartElement();
		streamWriter.writeStartElement(element.getName().getLocalPart());
		handleWriteAttributesElement(element, streamWriter, book);
	}
	
	private void handleWriteAttributesElement(StartElement element, XMLStreamWriter streamWriter, Book book) throws XMLStreamException {
		if(element.getName().getLocalPart().equals("library")) {			
			streamWriter.writeAttribute("lib_name", element.getAttributeByName(new QName("lib_name")).getValue());
			streamWriter.writeNamespace("xsi", "http://www.w3.org/2001/XMLSchema-instance");
			streamWriter.writeAttribute("http://www.w3.org/2001/XMLSchema-instance", "noNamespaceSchemaLocation", "LibraryXSD.xsd");
			streamWriter.writeStartElement("book");
			streamWriter.writeAttribute("id", book.getId());
			streamWriter.writeAttribute("type", book.getType());
			streamWriter.writeStartElement("title");
			streamWriter.writeCharacters(book.getTitle());
			streamWriter.writeEndElement();
			for(String author : book.getAuthors()) {
				streamWriter.writeStartElement("author");
				streamWriter.writeCharacters(author);
				streamWriter.writeEndElement();
			}
			streamWriter.writeEndElement();
		} else {			
			Iterator<Attribute> attributes = element.getAttributes();
			attributes.forEachRemaining(attribute -> {
				try {
					streamWriter.writeAttribute(attribute.getName().getLocalPart(), attribute.getValue());
				} catch (XMLStreamException e) {
					e.printStackTrace();
				}
			});
		}
	}

	private void handleWriteEndElement(XMLStreamWriter streamWriter) throws XMLStreamException {
		streamWriter.writeEndElement();
	}
	
	private void handleWriteCharacters(XMLEvent event, XMLStreamWriter streamWriter) throws XMLStreamException {
		Characters characters = event.asCharacters();
		streamWriter.writeCharacters(characters.getData());
	}

	@Override
	protected void editBook(File source, File destination, String id, String typeEdit, String titleEdit,
			List<String> authorsEdit) {
		try {
			editBook0(source, destination, id, typeEdit, titleEdit, authorsEdit);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private boolean isProcessEditBook = false;
	private String startElementName = null;
	
	private void editBook0(File source, File destination, String id, String typeEdit, String titleEdit,
			List<String> authorsEdit) throws XMLStreamException, IOException, TransformerException {
		XMLEventReader eventReader = getEventReader(source);
		XMLEventWriter eventWriter = getEventWriter();
		XMLEventFactory eventFactory = XMLEventFactory.newInstance();
		while(eventReader.hasNext()) {
			XMLEvent event = eventReader.nextEvent();
			if(event.isStartElement()) {
				StartElement element = event.asStartElement();
				startElementName = element.getName().getLocalPart();
				if(element.getName().getLocalPart().equals("book")) {
					String incommingId = element.getAttributeByName(new QName("id")).getValue();
					if(incommingId.equals(id)) {
						isProcessEditBook = true;
						eventWriter.add(eventFactory.createStartElement("", "", "book"));
						eventWriter.add(eventFactory.createAttribute(new QName("id").getLocalPart(), incommingId));
						eventWriter.add(eventFactory.createAttribute(new QName("type"), typeEdit != null ? typeEdit : element.getAttributeByName(new QName("type")).getValue()));
						eventWriter.add(eventFactory.createEndElement("", "", "book"));
					} else {
						eventWriter.add(event);
					}				
				} else {
					eventWriter.add(event);
				}
			} else if(event.isCharacters()) {
				Characters characters = event.asCharacters();
				if(startElementName.equals("title")) {
					eventWriter.add(eventFactory.createCharacters(titleEdit != null ? titleEdit : characters.getData()));
				} else if(startElementName.equals("author")) {
					if(authorsEdit != null && authorsEdit.size() != 0) {
						String author = authorsEdit.get(0);
						eventWriter.add(eventFactory.createCharacters(author != null ? author : characters.getData()));
						authorsEdit.remove(0);
					}
				}
				eventWriter.add(event);
			} else if(event.isEndElement()) {
				EndElement element = event.asEndElement();
				if(element.getName().getLocalPart().equals("book")) {
					if(isProcessEditBook)  {
						isProcessEditBook = false;
						eventWriter.add(eventFactory.createEndElement("", "", "book"));
					}
				} else {					
					eventWriter.add(event);
				}
			}
		}
		eventReader.close();
		eventWriter.close();
		String xml = new String(baos.toByteArray());
		formatWrite(xml, destination);
		baos.close();
	}
	
	@Override
	protected void removeBook(File source, File destination, String id) {
		
	}

	@Override
	protected void makeLibrary(File destination, List<Book> books) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected Element searchBookElement(File source, String id) throws XPathException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected List<Book> searchBookByAuthor(File source, String author) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected List<Book> searchBookByTitle(File source, String title) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void transformDoument(File documentXML, File documentXSLT, File destination) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected boolean isValidate(File document, File schema) {
		// TODO Auto-generated method stub
		return false;
	}
}
