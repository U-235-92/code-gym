package aq.gym.io.serialization.inheritance;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Main {

	public static void main(String[] args) {
		File cFile = new File("src/main/java/aq/gym/io/serialization/inheritance/c.dat");
		C origObj = new C();
		origObj.a = 1;
		origObj.b = 2;
		origObj.c = 3;
		origObj.cc = 4;
		C copyObj = null;
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(cFile));
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(cFile))) {
			System.out.println("Serialize...");
			oos.writeObject(origObj);
			System.out.println("Deserialize...");
			copyObj = (C) ois.readObject();
			C.stat = 888;
		} catch(ClassNotFoundException | IOException e) {
			e.printStackTrace();
		} 
		System.out.println("Orig: " + origObj.finalB);
		System.out.println("Copy: " + copyObj.finalB);
	}
}
