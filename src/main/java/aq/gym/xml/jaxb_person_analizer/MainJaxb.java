package aq.gym.xml.jaxb_person_analizer;

import java.io.File;
import java.io.IOException;

import jakarta.xml.bind.JAXBException;

public class MainJaxb {
	
	private static final String JAXB_PERSONS_FILE_NAME = "src/main/java/aq/gym/xml/person_analizer/personsJAXB.xml";

	public static void main(String[] args) {
		JaxbPersonMapper jaxbPersonMapper = new JaxbPersonMapper();
		File personsFile = createPersonsFileIfDoesntExist(JAXB_PERSONS_FILE_NAME);
		writePersonsUsingJAXB(jaxbPersonMapper, personsFile);
		readPersonsUsingJAXB(jaxbPersonMapper, personsFile);
	}

	private static File createPersonsFileIfDoesntExist(String fileName) {
		File personsFile = new File(fileName);
		if(!personsFile.exists()) {			
			try {
				personsFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return personsFile;
	}
	
	private static void writePersonsUsingJAXB(JaxbPersonMapper jaxbPersonMapper, File personsFile) {
		if(personsFile.exists()) {			
			Persons persons = new Persons();
			persons.addPerson(new Person("Ivan", 30, Country.RUSSIA));
			persons.addPerson(new Person("Bob", 20, Country.USA));
			persons.addPerson(new Person("Erica", 25, Country.GERMANY));
			persons.addPerson(new Person("Vasco", 44, Country.ITALY));
			persons.addPerson(new Person("Mariko", 22, Country.JAPAN));
			try {
				jaxbPersonMapper.marshal(persons, personsFile);
			} catch (JAXBException e) {
				if(personsFile.exists())
					personsFile.delete();
				e.printStackTrace();
			}
		} 
	}
	
	private static void readPersonsUsingJAXB(JaxbPersonMapper jaxbPersonMapper, File personsFile) {
		try {
			if(personsFile.exists()) {				
				Persons persons = jaxbPersonMapper.unmarshal(personsFile);
				persons.printPersons();
			}
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
}
