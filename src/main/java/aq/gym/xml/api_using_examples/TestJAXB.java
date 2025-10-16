package aq.gym.xml.api_using_examples;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

public class TestJAXB {

	public void testMarshalCarShop(CarShop carShop, File destination) {
		try {
			testMarshalCarShop0(carShop, destination);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void testMarshalCarShop0(CarShop carShop, File destination) throws JAXBException, IOException {
		FileOutputStream fos = new FileOutputStream(destination);
		JAXBContext context = JAXBContext.newInstance(Car.class, CarShop.class, CarType.class);
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marshaller.marshal(carShop, fos);
		fos.close();
	}
	
	public void testMarshalCar(Car car, File destination) {
		try {
			testMarshalCar0(car, destination);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void testMarshalCar0(Car car, File destination) throws JAXBException, IOException {
		FileOutputStream fos = new FileOutputStream(destination);
		JAXBContext context = JAXBContext.newInstance(Car.class);
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marshaller.marshal(car, fos);
		fos.close();
	}
	
	public void testUnmarshalCar(File source) {
		try {
			testUnmarshalCar0(source);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
	
	private void testUnmarshalCar0(File source) throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(Car.class);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		Car car = (Car) unmarshaller.unmarshal(source);
		System.out.println(car);
	}
	
	public void testUnmarshalCarShop(File source) {
		try {
			testUnmarshalCarShop0(source);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void testUnmarshalCarShop0(File source) throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(CarShop.class, CarType.class, Car.class);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		CarShop carShop = (CarShop) unmarshaller.unmarshal(source);
		System.out.println(carShop);
	}
}
