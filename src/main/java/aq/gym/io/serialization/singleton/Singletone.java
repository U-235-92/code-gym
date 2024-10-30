package aq.gym.io.serialization.singleton;

import java.io.ObjectStreamException;
import java.io.Serializable;

public class Singletone implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String name;
	private static Singletone singletone;

	private Singletone() {
		name = "I'm singletone";
	}

	public static Singletone getInstance() {
		if (singletone == null)
			singletone = new Singletone();
		return singletone;
	}

	public String getName() {
		return name;
	}
	
	protected Object readResolve() throws ObjectStreamException {
		if(singletone == null)
			singletone = new Singletone();
		return singletone;
	}

}
