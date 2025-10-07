package aq.gym.io.serialization.version;

import java.io.Serializable;

public class Thing implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String name;
	private String color;
	private String manufacturer;
	
	public Thing(String name, String color) {
		super();
		this.name = name;
		this.color = color;
	}
	
	public Thing(String name, String color, String manufacturer) {
		super();
		this.name = name;
		this.color = color;
		this.manufacturer = manufacturer;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getName() {
		return name;
	}

	public String getColor() {
		return color;
	}

	@Override
	public String toString() {
		return "Thing [name=" + name + ", color=" + color + ", manufacturer=" + manufacturer + "]";
	}
}
