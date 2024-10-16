package aq.gym.inheritence;

public class Point2D extends Point1D {

	private int y;

	@Override
	public Point2D getPoint() {
		super.setX((int) Math.random() * 10);
		y = (int) Math.random() * 10;
		return new Point2D();
	}

	@Override
	public String toString() {
		return "Point2D [x=" + super.getX() + " y=" + y + "]";
	}
	
}
