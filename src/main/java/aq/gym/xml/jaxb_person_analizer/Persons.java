package aq.gym.xml.jaxb_person_analizer;

import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "persons")
@XmlAccessorType(XmlAccessType.FIELD)
public class Persons {

	@XmlElement(name = "person")
	private List<Person> persons;
	
	public Persons() {
		persons = new ArrayList<Person>();
	}
	
	public boolean addPerson(Person person) {
		return persons.add(person);
	}
	
	public boolean removePerson(Person person) {
		return persons.remove(person);
	}
	
	public void printPersons() {
		persons.forEach(System.out::println);
	}
}
