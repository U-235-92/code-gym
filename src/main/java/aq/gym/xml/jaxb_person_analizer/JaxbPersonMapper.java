package aq.gym.xml.jaxb_person_analizer;

import java.io.File;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

public class JaxbPersonMapper {

	public void marshal(Persons persons, File output) throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(Persons.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		jaxbMarshaller.marshal(persons, output);
	}
	
	public Persons unmarshal(File source) throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(Persons.class);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		Persons persons = (Persons) unmarshaller.unmarshal(source);
		return persons;
	}
}
