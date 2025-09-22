package aq.gym.work_with_objects.inner_class;

public class Person {

	private String name;
	private Address address;
	
	public Person(String name, String city) {
		this.name = name;
		address = new Address(city);
	}
	
	public String getDescription() {
		return "Name: " + name + ", City: " + address.city; 
	}
	
	private class Address {
		
		private String city;
		
		private Address(String city) {
			this.city = city;
		}
	}
}
