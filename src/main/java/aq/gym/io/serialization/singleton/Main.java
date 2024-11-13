package aq.gym.io.serialization.singleton;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Main {

	public static void main(String[] args) {
		File singletonFile = new File("src/main/java/aq/gym/io/serialization/singleton/singleton.dat");
		Singletone singletone1 = Singletone.getInstance();
		Singletone singletone2 = null;
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(singletonFile));
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(singletonFile))) {
			oos.writeObject(singletone1);
			singletone2 = (Singletone) ois.readObject();
		} catch(ClassNotFoundException | IOException e) {
			e.printStackTrace();
		} 
		System.out.println(singletone1 == singletone2);
	}

}
