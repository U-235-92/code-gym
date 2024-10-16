package aq.gym.inheritence;

public class Point1D {

	private int x;
	
	public Point1D getPoint() {
		x = (int) Math.random() * 10;
		return new Point1D();
	}
	
	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	@Override
	public String toString() {
		return "Point1D [x=" + x + "]";
	}
}
