package aq.gym.patterns.builder;

public class Car {

	private String mark;
	private String model;
	private int topSpeed;
	private int numSeats;
	private boolean isLeftSteeringWheel;
	private String color;
	
	private Car(Builder builder) {
		this.mark = builder.mark;
		this.model = builder.model;
		this.topSpeed = builder.topSpeed;
		this.numSeats = builder.numSeats;
		this.isLeftSteeringWheel = builder.isLeftSteeringWheel;
		this.color = builder.color;
	}
	
	@Override
	public String toString() {
		return "Car [mark=" + mark + ", model=" + model + ", topSpeed=" + topSpeed + " km/h, numSeats=" + numSeats
				+ ", isLeftSteeringWheel=" + isLeftSteeringWheel + ", color=" + color + "]";
	}
	
	public static Builder builder() {
		return new Builder();
	}
	
	public static class Builder {
		
		private String mark;
		private String model;
		private int topSpeed;
		private int numSeats;
		private boolean isLeftSteeringWheel;
		private String color;
		
		public Builder mark(String mark) {
			this.mark = mark;
			return this;
		}
		
		public Builder model(String model) {
			this.model = model;
			return this;
		}
		
		public Builder topSeed(int topSpeed) {
			this.topSpeed = topSpeed;
			return this;
		}
		
		public Builder numSeats(int numSeats) {
			this.numSeats = numSeats;
			return this;
		}
		
		public Builder leftSteeringWheel(boolean isLeftSteeringWheel) {
			this.isLeftSteeringWheel = isLeftSteeringWheel;
			return this;
		}
		
		public Builder color(String color) {
			this.color = color;
			return this;
		}
		
		public Car build() {
			return new Car(this);
		}
	}
}
