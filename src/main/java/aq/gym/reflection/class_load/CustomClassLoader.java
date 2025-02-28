package aq.gym.reflection.class_load;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class CustomClassLoader extends ClassLoader {

	private String rootPath;
	
	public CustomClassLoader(String rootPath) {
		super();
		if(!rootPath.endsWith(File.separator)) {
			rootPath = rootPath + File.separator;
		}
		this.rootPath = rootPath;
	}
	
	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		byte[] classBytes;
		try {
			classBytes = loadClassBytes(name);
		} catch (IOException e) {
			NullPointerException nullClassBytesException = new NullPointerException("Class bytes are null");
			nullClassBytesException.initCause(e);
			throw nullClassBytesException;
		}
		Class<?> clazz = defineClass(name, classBytes, 0, classBytes.length);
		return clazz;
	}
	
	private byte[] loadClassBytes(String name) throws IOException {
		String path = rootPath + name.replace('.', File.separatorChar) + ".class";
		byte[] classBytes = Files.readAllBytes(Path.of(path));
		return classBytes;
	}
}
