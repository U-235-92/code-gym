package aq.gym.work_with_objects.serialization;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

@SuppressWarnings("unused")
public class SerializationTestDrive {

	public static void main(String[] args) throws FileNotFoundException, ClassNotFoundException, IOException {
		File file = new File("src/main/java/aq/gym/work_with_objects/serialization/no_version_person.dat");
		Person alice = new Person(18, "Alice", List.of("dancing", "music", "arts", "sport", "reading"));
//		Person alice = new Person(18, "Alice");
		writePerson(alice, file);
		System.out.println(readPerson(file));
	}
	
	private static void writePerson(Person person, File file) throws FileNotFoundException, IOException {
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(new FileOutputStream(file));			
			oos.writeObject(person);
		} finally {			
			oos.close();
		}
	}
	
	private static Person readPerson(File file) throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectInputStream ois = null;
		try {			
			ois = new ObjectInputStream(new FileInputStream(file));
			Person person = (Person) ois.readObject();
			return person;
		} finally {
			ois.close();
		}
	}
}
