package aq.gym.io.serialization.version;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;

public class ThingTestDrive {

//	NOTE: If you change the serial version [from 1 to N for example] of Thing class you will get 
//	[java.io.InvalidClassException]
//	Remember that if you change fields of a class without changing serial version all will be fine
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		File fileThingV1 = new File("src/main/java/aq/gym/io/serialization/version/thing_ser_v-1.0.0.dat");
		File fileThingV1Modified = new File("src/main/java/aq/gym/io/serialization/version/modified_thing_ser_v-1.0.0.dat");
		@SuppressWarnings("unused")
		Thing thingToSaveV1 = new Thing("Table", "Black");
		Thing thingToSaveV1Modified = new Thing("Table", "Black", "ACME Inc.");
		createFile(fileThingV1);
		saveThing(thingToSaveV1Modified, fileThingV1Modified);
//		saveThing(thingToSaveV1, fileThingV1);
		Thing thingReadV1 = readThing(fileThingV1);
		Thing thingReadV1Modified = readThing(fileThingV1Modified);
		System.out.println(thingReadV1);
		System.out.println(thingReadV1Modified);
	}

	private static void createFile(File file) throws IOException {
		if(Files.notExists(file.toPath())) {				
			Files.createFile(file.toPath());
		} 
	}
	
	private static void saveThing(Thing thing, File file) throws IOException {
		ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
		oos.writeObject(thing);
		oos.close();
	}
	
	private static Thing readThing(File file) throws ClassNotFoundException, IOException {
		ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)));
		Thing thing = (Thing) ois.readObject();
		ois.close();
		return thing;
	}
}
