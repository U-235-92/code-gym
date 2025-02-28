package aq.gym.reflection.class_load;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {

	public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, 
						SecurityException, InstantiationException, 
						IllegalAccessException, IllegalArgumentException, 
						InvocationTargetException {
		String rootPath = args[0];
		String className = args[1];
		String methodName = args[2];
		CustomClassLoader customClassLoader = new CustomClassLoader(rootPath);
		Class<?> clazz = customClassLoader.loadClass(className);
		Constructor<?> constructor = clazz.getDeclaredConstructor();
		constructor.setAccessible(true);
		Object object = constructor.newInstance();
		Method method = object.getClass().getDeclaredMethod(methodName);
		method.setAccessible(true);
		method.invoke(object);
	}
}
