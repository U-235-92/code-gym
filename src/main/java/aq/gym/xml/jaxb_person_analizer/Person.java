package aq.gym.xml.jaxb_person_analizer;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Person {

	@XmlTransient
	private static int genID;
	@XmlAttribute(name = "id", required = true)
	private int id;
	private String name;
	private int age;
	private Country country;
	
	public Person(String name, int age, Country country) {
		genID++;
		id = genID;
		this.name = name;
		this.age = age;
		this.country = country;
	}
}
