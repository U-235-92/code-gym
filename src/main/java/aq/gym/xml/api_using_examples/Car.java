package aq.gym.xml.api_using_examples;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Car {

	private String manufacturer;
	private String model;
	@XmlAttribute
	private CarType type;
	@XmlAttribute
	private int price;
	
	public Car() {
		super();
	}

	public Car(String manufacturer, String model, CarType type,  int price) {
		super();
		this.manufacturer = manufacturer;
		this.model = model;
		this.type = type;
		this.price = price;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public CarType getType() {
		return type;
	}

	public void setType(CarType type) {
		this.type = type;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(manufacturer);
		sb.append(" ");
		sb.append(model);
		sb.append(" ");
		sb.append("type=");
		sb.append(type);
		sb.append(" ");
		sb.append("price=");
		sb.append(price);
		return sb.toString();
	}
}
